import IntervalSelection from 'diet/IntervalSelection.js'
import MultipleIngredientInput from 'diet/MultipleIngredientInput.js'

export default class CreateDietPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      errors: null
    };

    this.energy = {};
    this.proteinVal = {};
    this.carbVal = {};
    this.fatVal = {};
    this.proteinRate = {};
    this.carbRate = {};
    this.fatRate = {};
    this.ingredients = {};
  }

  nameChanged(event) {
    this.setState({
      name: event.target.value
    })
  }

  descriptionChanged(event) {
    this.setState({
      description: event.target.value
    })
  }

  submit(event) {
    event.preventDefault()
    this.setState({errors: null})

    const postData = {
      name: this.state.name,
      description: this.state.description,
      minEnergy: this.energy.min,
      maxEnergy: this.energy.max,
      minProteinVal: this.proteinVal.min,
      maxProteinVal: this.proteinVal.max,
      minCarbVal: this.carbVal.min,
      maxCarbVal: this.carbVal.max,
      minFatVal: this.fatVal.min,
      maxFatVal: this.fatVal.max,
      minProteinRate: this.proteinRate.min,
      maxProteinRate: this.proteinRate.max,
      minCarbRate: this.carbRate.min,
      maxCarbRate: this.carbRate.max,
      minFatRate: this.fatRate.min,
      maxFatRate: this.fatRate.max,
      ingredients: this.ingredients.list
    }
    console.log(postData);
    Api.createDiet(postData)
      .then((data) => {
      }).catch((err) => {
        this.setState({errors: err.data})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div className="ui segments">
        <div className="ui segment">
          Create a New Diet
        </div>
        {/* diet name and description */}
        <div className="ui segment">
          <form className={formClassName}>
            <div className="field">
              <label> Name </label>
              <input type="text" name="name" placeholder="name" value={this.state.name} onChange={this.nameChanged.bind(this)}/>
              { this.state.errors && this.state.errors.name &&
                <div className="ui error message">
                  <p>{this.state.errors.name[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label> Description </label>
              <input type="text" name="description" placeholder="description" value={this.state.description} onChange={this.descriptionChanged.bind(this)}/>
            </div>
          </form>
        </div>
        {/* Intervals and Ratios */}
        <div className="ui segment">
          Minimum and Maximum Values
        </div>
        <div className="ui segment">
          <IntervalSelection label="Energy" unit="kcal" variable={this.energy}/>
          <IntervalSelection label="Protein" unit="g" variable={this.proteinVal}/>
          <IntervalSelection label="Carbohydrate" unit="g" variable={this.carbVal}/>
          <IntervalSelection label="Fat" unit="g" variable={this.fatVal}/>
        </div>
        <div className="ui segment">
          Minimum and Maximum Rates
        </div>
        <div className="ui segment">
          <IntervalSelection label="Protein" unit="" variable={this.proteinRate}/>
          <IntervalSelection label="Carbohydrate" unit="" variable={this.carbRate}/>
          <IntervalSelection label="Fat" unit="" variable={this.fatRate}/>
        </div>
        {/* ingredients */}
        <div className="ui segment">
          Selected Ingredients
        </div>
        <div className="ui segment">
          <MultipleIngredientInput ingredients={this.ingredients}/>
        </div>
        <div className="ui segment" style={{textAlign:'center'}}>
          <button className="ui button" type='submit' onClick={this.submit.bind(this)}>Submit</button>
        </div>
      </div>
    )
  }
}
