import FoodRow from '../food/FoodRow.js'
import Comments from 'comment/Comments.js'
import Rate from 'rate/Rate.js'

export default class RestaurantPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      restaurant: {
        foods: []
      }
    }
    this.fetch = this.fetch.bind(this)
    this.comment = this.comment.bind(this);
    this.restaurantRated = this.restaurantRated.bind(this);
  }

  componentWillMount(){
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getRestaurant(id)
      .then((data) => {
        console.log(data);
        this.setState({restaurant: data});
      }).catch((err) => {
        this.setState({errors: err});
      })

      Api.me()
        .then((data) => {
          let restaurantRates = data.restaurantRates;
          let userRate = 0;
          for (var i = 0; i < restaurantRates.length; i++) {
            if(restaurantRates[i].restaurant == this.state.id){
              userRate = restaurantRates[i].score;
            }
          }
          this.setState({userRate: userRate});
        })
  }

  getComments(id){
    const self = this;
    Api.getRestaurant(id)
      .then((data) => {
        self.setState({comments: data.comments});
      }).catch((err) => {
        this.setState({errors: err.data});
      })
  }

  comment(data){
    Api.commentOnRestaurant(this.state.id, data)
      .then((data) => {
        this.fetch(this.state.id); // get updated comments list
      }).catch((error) => {
        this.setState({errors: error.data})
      })
  }
  restaurantRated(rate){
    let postData = {
      score: rate
    }
    Api.rateOnRestaurant(this.state.id, postData).then((data) => {
      console.log(data);
    }).catch((error) => {
      this.setState({errors: error.data})
    })
  }

  getRating(id){
    Api.getRestaurant(id)
      .then((data) => {
        this.setState({rating: data.rate});
      })
  }
  render() {
    return (
      <div className="ui segments">
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}> {this.state.restaurant.name || "Restaurant not found"} </h1>
        </div>
        <div className="ui segment" style={{display:'flex',justifyContent:'space-between', alignItems:'center'}}>
          {token && (this.state.restaurant.rate !== undefined) && (this.state.userRate !== undefined) &&
            <Rate id={this.state.id} label="Restaurant Rating" onChange={this.restaurantRated} getRating={this.getRating} initialRating={this.state.restaurant.rate} initialUserRating={this.state.userRate} name={'restaurants'+this.state.id}/>
          }
        </div>
        <div className="ui segment" style={{padding:0,overflow:'hidden',maxHeight:400,textAlign:'center',width:'100%'}}>
          <img src={this.state.restaurant.photo} className='img-responsive'/>
        </div>
        { this.state.restaurant.description &&
          <div className="ui segment" style={{textAlign:'center'}}>
            <h2 className="ui header">Description</h2>
            <p>{this.state.restaurant.description}</p>
          </div>
        }
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>Foods</h1>
        </div>
        <div className="ui segment">
          { this.state.restaurant.foods.map((food) => {
            return <FoodRow key={food.id} data={food}/>
          })}
        </div>

        {/* comments section */}
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>Comments</h1>
        </div>
        {this.state.restaurant.comments &&
          <div className="ui segment">
            <Comments getComments={this.getComments} id={this.state.id} comment={this.comment}/>
          </div>
        }
      </div>
    )
  }
}
