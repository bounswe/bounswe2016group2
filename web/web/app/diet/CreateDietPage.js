import IntervalSelection from 'diet/IntervalSelection.js'
import MultipleIngredientInput from 'diet/MultipleIngredientInput.js'
import Slider from 'service/Slider.js'

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

  proteinChanged(min, max) {
    this.setState({
      minProteinVal: min,
      maxProteinVal: max
    })
  }

  carbChanged(min, max) {
    this.setState({
      minCarbVal: min,
      maxCarbVal: max
    })
  }

  fatChanged(min, max) {
    this.setState({
      minFatVal: min,
      maxFatVal: max
    })
  }

  energyChanged(min, max) {
    this.setState({
      minEnergy: min,
      maxEnergy: max
    })
  }

  submit(event) {
    event.preventDefault()
    this.setState({errors: null})

    const postData = {
      name: this.state.name,
      description: this.state.description,
      minEnergy: this.state.minEnergy,
      maxEnergy: this.state.maxEnergy,
      minProteinVal: this.state.minProteinVal,
      maxProteinVal: this.state.maxProteinVal,
      minCarbVal: this.state.minCarbVal,
      maxCarbVal: this.state.maxCarbVal,
      minFatVal: this.state.minFatVal,
      maxFatVal: this.state.maxFatVal,
      minProteinRate: this.proteinRate.min,
      maxProteinRate: this.proteinRate.max,
      minCarbRate: this.carbRate.min,
      maxCarbRate: this.carbRate.max,
      minFatRate: this.fatRate.min,
      maxFatRate: this.fatRate.max,
      ingredients: this.ingredients.list
    }
    if(!this.ingredients.list)
      postData.ingredients = [];
    Api.createDiet(postData)
      .then((data) => {
        router.navigate('../diets/' + data.id);
      }).catch((err) => {
        console.log("diet cannot be created");
        this.setState({errors: err.data})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div className="ui segments">
        <div className="ui segment" style={{textAlign:'center'}}>
          <h2 className="ui header">Create a New Diet</h2>
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
        <div className="ui segment" style={{textAlign:'center'}}>
          <h2 className="ui header">Minimum and Maximum Values</h2>
        </div>
        <div className="ui segment">
          <div style={{display:'inline-block',width:'50%'}}>
            <h4 style={{marginBottom:50}}>Protein Weight (g)</h4>
            <Slider id="proteinSlider" onChange={this.proteinChanged.bind(this)}/>
          </div>
          <div style={{display:'inline-block',width:'50%'}}>
            <h4 style={{marginBottom:50}}>Carbonhydrate Weight (g)</h4>
            <Slider id="carbSlider" onChange={this.carbChanged.bind(this)}/>
          </div>
        </div>
        <div>
          <div style={{display:'inline-block',width:'50%'}}>
            <h4 style={{marginBottom:50}}>Fat Weight (g)</h4>
            <Slider id="fatSlider" onChange={this.fatChanged.bind(this)}/>
          </div>
          <div style={{display:'inline-block',width:'50%'}}>
            <h4 style={{marginBottom:50}}>Energy (kcal)</h4>
            <Slider id="energySlider" onChange={this.energyChanged.bind(this)}/>
          </div>
        </div>

        <div className="ui segment" style={{textAlign:'center'}}>
          <h2 className="ui header">Minimum and Maximum Rates</h2>
        </div>
        <div className="ui segment">
          <IntervalSelection label="Protein" unit="" variable={this.proteinRate}/>
          <IntervalSelection label="Carbohydrate" unit="" variable={this.carbRate}/>
          <IntervalSelection label="Fat" unit="" variable={this.fatRate}/>
        </div>
        {/* ingredients */}
        <div className="ui segment" style={{textAlign:'center'}}>
          <h2 className="ui header">Excluded Ingredients</h2>
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
