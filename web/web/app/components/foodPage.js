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
      name: null,
      data: null,
      errors: null,
      ingredients: []
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
        this.setState(
          {
            name: data.name,
            url: data.photo,
            ingredients: data.ingredients
          }
        );
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
      <div id='FoodPage'>
        <div className="header">
          <h1 className="ui header"> {this.state.name || "Food not found"} </h1>
        </div>
        <div className="content">
          <div>
            <img src={this.state.url} style={{width: 400, height: 400}}/>
          </div>
          {
            userEmail && this.state.name &&
            <button className="ui button" type="button" style={{width:'10%'}} onClick={this.ateThis}>
              I ate this!
            </button>
          }
          <div>
            <h2 className="header"> Ingredients</h2>
            {this.state.ingredients.map(function(ingredient, index){
              return <Ingredient data={ingredient} key={index}/>
            }, this)}
          </div>
        </div>
      </div>
    )
  }
}
