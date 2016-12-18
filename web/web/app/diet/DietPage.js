export default class DietPage extends React.Component {
  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      diet: {
        ingredients: []
      }
    }
    this.fetch = this.fetch.bind(this)
  }

  componentWillMount(){
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getDiet(id)
      .then((data) => {
        this.setState({diet: data});
      }).catch((err) => {
        this.setState({errors: err});
      })
  }

  render() {
    return (
      <div className="ui segments">
        <div className="ui segment">
          <h1 className="ui header" style={{textAlign:'center'}}> {this.state.diet.name || "Diet not found"} </h1>
        </div>
        { this.state.diet.description &&
          <div className="ui segment" style={{textAlign:'center'}}>
            <h2 className="ui header">Description</h2>
            <p>{this.state.diet.description}</p>
          </div>
        }
        <div className="ui segment">
          <h2 className="ui header" style={{textAlign:'center'}}>Minimum and Maximum Values</h2>
        </div>
        <div className="ui segment">
          <table className="ui segment celled table" style={{width:'100%'}}>
            <thead>
              <tr>
                <th></th>
                <th>{Constants.value.energy.name}</th>
                <th>{Constants.macro.protein.name}</th>
                <th>{Constants.macro.carb.name}</th>
                <th>{Constants.macro.fat.name}</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Minimum Value</td>
                <td>{Number(this.state.diet.minEnergy)} {Constants.value.energy.unit} </td>
                <td>{Number(this.state.diet.minProteinVal)} {Constants.macro.protein.unit} </td>
                <td>{Number(this.state.diet.minCarbVal)} {Constants.macro.carb.unit} </td>
                <td>{Number(this.state.diet.minFatVal)} {Constants.macro.fat.unit} </td>
              </tr>
              <tr>
                <td>Maximum Value</td>
                <td>{Number(this.state.diet.maxEnergy)} {Constants.value.energy.unit} </td>
                <td>{Number(this.state.diet.maxProteinVal)} {Constants.macro.protein.unit} </td>
                <td>{Number(this.state.diet.maxCarbVal)} {Constants.macro.carb.unit} </td>
                <td>{Number(this.state.diet.maxFatVal)} {Constants.macro.fat.unit} </td>
              </tr>
              <tr>
                <td>Minimum Rate</td>
                <td></td>
                <td>{Number(this.state.diet.minProteinRate)}</td>
                <td>{Number(this.state.diet.minCarbRate)}</td>
                <td>{Number(this.state.diet.minFatRate)}</td>
              </tr>
              <tr>
                <td>Maximum Rate</td>
                <td></td>
                <td>{Number(this.state.diet.maxProteinRate)}</td>
                <td>{Number(this.state.diet.maxCarbRate)}</td>
                <td>{Number(this.state.diet.maxFatRate)}</td>
              </tr>
            </tbody>
          </table>
        </div>

        {/* Excluded Ingredients */}
        <div className="ui segment">
          <h2 className="ui header" style={{textAlign:'center'}}>Excluded Ingredients</h2>
        </div>
        <div className="ui segment">
          <div className="ui relaxed divided list">
            {this.state.diet.ingredients.map((ingredient, index) => {
              return (
                <div className="item" key={index} style={{textAlign: 'center'}}>
                  <h3><a href={'/ingredient/' + ingredient.id}>{ingredient.name}</a></h3>
                </div>
            )})}
          </div>
        </div>
      </div>
    )
  }
}
