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
        ingredients: [],
        inclusions: [],
        details: {}
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
        </table>
        {/* ingredients */}
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>Ingredients</h1>
        </div>
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
        </table>
        {/* micronutrients */}
        {/* <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}>micronutrients</h1>
          </div>
          <table className="ui segment celled table" style={{width:'100%'}}>
          // TODO: micronutrients
        </table> */}
      </div>
        )
  }
}
