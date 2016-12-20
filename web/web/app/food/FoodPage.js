import Comments from 'comment/Comments.js'
import Rate from 'rate/Rate.js'

export default class FoodPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      servingSize: 1,
      food: {
        ingredients: [],
        inclusions: [],
        details: {}
      }
    }
    this.fetch = this.fetch.bind(this)
    this.ateThis = this.ateThis.bind(this)
    this.servingSizeChanged = this.servingSizeChanged.bind(this)
    this.comment = this.comment.bind(this);
    this.foodRated = this.foodRated.bind(this);
  }

  componentWillMount(){
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getFood(id)
      .then((data) => {
        this.setState({food: data});
      }).catch((err) => {
        this.setState({errors: err});
      })

      Api.me()
        .then((data) => {
          let foodRates = data.foodRates;
          let userRate = 0;
          for (var i = 0; i < foodRates.length; i++) {
            if(foodRates[i].food == this.state.id){
              userRate = foodRates[i].score;
            }
          }
          this.setState({userRate: userRate});
        })
  }

  openAteFoodModal() {
      $('#ateFoodModal').modal('show')
  }

  openSuccessModal() {
    $('#ateFoodSuccModal').modal('show')
  }

  servingSizeChanged(e) {this.setState({servingSize: e.target.value}) }

  ateThis(e) {
    e.preventDefault()
    var data = {
      value: this.state.servingSize
    }
    Api.foodAte(this.state.id, data)
      .then((data) => {
        $('#ateFoodSuccModal').modal('show')
      }).catch((err) => {
        this.setState({errors: err.data})
      })
  }

  comment(data){
    Api.commentOnFood(this.state.id, data)
      .then((data) => {
        this.fetch(this.state.id); // get updated comments list
      }).catch((error) => {
        this.setState({errors: error.data})
      })
  }

  getComments(foodId){
    const self = this;
    Api.getFood(foodId)
      .then((data) => {
        self.setState({comments: data.comments});
      }).catch((err) => {
        this.setState({errors: err.data});
      })
  }

  foodRated(rate){
    let postData = {
      score: rate
    }
    Api.rateOnFood(this.state.id, postData).catch((error) => {
      this.setState({errors: error.data})
    })
  }

  getRating(id){
    Api.getFood(id)
      .then((data) => { 
        this.setState({rating: data.rate});
      })
  }

  render() {
    return (
      <div className="ui segments">
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}> {this.state.food.name || "Food not found"} </h1>
        </div>
        <div className="ui segment" style={{padding:0,overflow:'hidden',maxHeight:400,textAlign:'center',width:'100%'}}>
          <img src={this.state.food.photo} className='img-responsive'/>
        </div>
        <div className="ui segment" style={{display:'flex',justifyContent:'space-between', alignItems:'center'}}>
          { this.state.food.restaurant &&
            <div>
              <span style={{marginRight: 5}}>
                Restaurant:
              </span>
              <a href={"/restaurants/" + this.state.food.restaurant.id}>
                {this.state.food.restaurant.name}
              </a>
            </div>
          }
          {/* i ate this functionality here */}
          { token && (this.state.food.rate !== undefined) &&
            <div>
              <button className="ui button" type="button" onClick={this.openAteFoodModal}>
                I ate this!
              </button>
              <div id='ateFoodSuccModal' className="ui small modal">
                <div className="ui message success">
                  <i className="close icon"></i>
                  <div className="header">
                    Success!
                  </div>
                  <p>The food has been saved to your nutrition history</p>
                </div>
              </div>
              <div id='ateFoodModal' className="ui small modal">
                <i className="close icon"></i>
                <div className="header">
                  You ate this?
                </div>
                <div className="content">
                  <form className="ui form">
                    <div className="field">
                      <label>Serving size</label>
                      <input type="number" min="0" max="100" name="servingSize" value={this.state.servingSize} onChange={this.servingSizeChanged}/>
                    </div>
                    <button className="ui button" type="submit" style={{width:'100%'}} onClick={this.ateThis}>
                      Submit
                    </button>
                  </form>
                </div>
              </div>
            </div>
          }
          {/* Food rating */}
          {token && (this.state.food.rate !== undefined) && (this.state.userRate !== undefined) &&
            <Rate id={this.state.id} label="Food Rating" onChange={this.foodRated} getRating={this.getRating} initialRating={this.state.food.rate} initialUserRating={this.state.userRate} name={'foods'+this.state.id}/>
          }
        </div>
        {/* general info   */}
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>General Information</h1>
        </div>
        <table className="ui segment celled table" style={{width:'100%'}}>
          <thead>
            <tr>
              <th></th>
              <th>Total</th>
              <th>{Constants.macro.protein.name}</th>
              <th>{Constants.macro.carb.name}</th>
              <th>{Constants.macro.fat.name}</th>
              <th>Other</th>
              <th>Energy</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Weight</td>
              <td>{Number(this.state.food.details.weight).toFixed(2)} g</td>
              <td>{this.state.food.details.protein && Number(this.state.food.details.protein.weight).toFixed(2)} g</td>
              <td>{this.state.food.details.carb && Number(this.state.food.details.carb.weight).toFixed(2)} g</td>
              <td>{this.state.food.details.fat && Number(this.state.food.details.fat.weight).toFixed(2)} g</td>
              <td>{this.state.food.details.other && Number(this.state.food.details.other.weight).toFixed(2)} g</td>
              <td>{Number(this.state.food.details.energy).toFixed(2)} kcal</td>
            </tr>
            <tr>
              <td>Rate</td>
              <td>100 %</td>
              <td>{this.state.food.details.protein && Math.round((this.state.food.details.protein.weight/this.state.food.details.weight)*100)} %</td>
              <td>{this.state.food.details.carb && Math.round((this.state.food.details.carb.weight/this.state.food.details.weight)*100)} %</td>
              <td>{this.state.food.details.fat && Math.round((this.state.food.details.fat.weight/this.state.food.details.weight)*100)} %</td>
              <td>{this.state.food.details.other && Math.round((this.state.food.details.other.weight/this.state.food.details.weight)*100)} %</td>
              <td></td>
            </tr>
          </tbody>
        </table>
        {/* ingredients */}
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>Ingredients</h1>
        </div>
        <div className="ui segment">
          <table className="ui segment celled table" style={{width:'100%'}}>
            <thead>
              <tr>
                <th>Name</th>
                <th>{Constants.value.weight.name}</th>
                <th>Measure</th>
                <th>{Constants.value.energy.name}</th>
                <th>{Constants.macro.protein.name}</th>
                <th>{Constants.macro.carb.name}</th>
                <th>{Constants.macro.fat.name}</th>
              </tr>
            </thead>
            <tbody>
              {this.state.food.inclusions.map((inclusion, index) => {
                return (
                  <tr key={index}>
                    <td><a href={'/ingredient/' + inclusion.ingredient.id}>{inclusion.name}</a></td>
                    <td>{Number(inclusion.value).toFixed(2)} {inclusion.unit}</td>
                    <td>{Number(inclusion.ingredient.measureValue).toFixed(2)} {inclusion.ingredient.measureUnit}</td>
                    <td>{Number(inclusion.ingredient.energy).toFixed(2)} kcal</td>
                    <td>{Number(inclusion.ingredient.protein).toFixed(2)} g</td>
                    <td>{Number(inclusion.ingredient.carb).toFixed(2)} g</td>
                    <td>{Number(inclusion.ingredient.fat).toFixed(2)} g</td>
                  </tr>
                )
              })}
            </tbody>
          </table>
        </div>
        {/* comments section */}
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>Comments</h1>
        </div>
        {this.state.food.comments &&
          <div className="ui segment">
            <Comments getComments={this.getComments} id={this.state.id} comment={this.comment}/>
          </div>
        }
      </div>
    )
  }
}
