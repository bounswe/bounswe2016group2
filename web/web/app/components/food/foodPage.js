class Ingredient extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <h3 className="header"> <a href={'/ingredient/' + this.props.data.id}>{this.props.data.name} </a></h3>
        <div> {this.props.data.defaultValue || 0} {this.props.data.defaultUnit} </div>
      </div>
    );
  }
}

class FoodPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      food: {
        ingredients: []
      }
    }
    this.fetch = this.fetch.bind(this)
    this.ateThis = this.ateThis.bind(this)
  }

  componentWillMount(){
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getFood(id)
      .then((data) => {
        console.log(data);
        this.setState({food: data});
      }).catch((err) => {
        this.setState({errors: err});
      })
  }

  // TODO: currently not working
  ateThis() {
    var query = {
      value: 1
    }
    Api.foodAte(this.state.id, query)
      .then((data) => {
        console.log(data);
      }).catch((err) => {
        console.log(err);
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
        <div className="ui segment" style={{textAlign:'right'}}>
          { token &&
            <button className="ui button" type="button" onClick={this.ateThis}>
              I ate this!
            </button>
          }
        </div>
        <div className="ui segment">
          <div>
            <h2 className="header"> Ingredients</h2>
            {this.state.food.ingredients.map(function(ingredient, index){
              return <Ingredient data={ingredient} key={index}/>
            }, this)}
          </div>
        </div>
      </div>
    )
  }
}
