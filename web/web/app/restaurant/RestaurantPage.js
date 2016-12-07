export default class FoodPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      restaurant: {
      }
    }
    this.fetch = this.fetch.bind(this)
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
  }

  render() {
    return (
      <div className="ui segments">
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}> {this.state.restaurant.name || "Restaurant not found"} </h1>
        </div>
        <div className="ui segment" style={{padding:0,overflow:'hidden',maxHeight:400,textAlign:'center',width:'100%'}}>
          <img src={this.state.restaurant.photo} className='img-responsive'/>
        </div>
      </div>
    )
  }
}
