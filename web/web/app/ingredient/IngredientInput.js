export default class IngredientInput extends React.Component {
  constructor(props) {
    super(props)
    this.inclusion = props.inclusion
    this.id = props.id
    this.state = {
      ingredients: [],
      ingredient: {
        measureValue: 0,
        measureUnit: ''
      }
    }
  }

  componentDidMount() {
    var self = this;
    // fetch ingredeints
    Api.getIngredients()
    .then((data) => {
      this.setState(
        {
          ingredients: data
        }
      );
      // searchable semantic dropdown for ingredient select
      $('#ingredientInput'+this.id+' .ui.dropdown').dropdown({
        onChange(index) {
          self.setState({
            ingredient: self.state.ingredients[index],
            value: self.state.ingredients[index].defaultValue,
            unit: self.state.ingredients[index].defaultUnit,
            measureValue: self.state.ingredients[index].measureValue
          })
          self.inclusion.ingredientId = self.state.ingredients[index].id
          self.inclusion.value = self.state.value;
        }
      })
    }).catch((err) => {
      this.setState({errors: err});
    })
  }

  valueChanged(event) {
    const newValue = event.target.value
    const defValue = this.state.ingredient.defaultValue
    const measureValue = this.state.ingredient.measureValue * (newValue/defValue)
    this.setState({
      value: newValue,
      measureValue: measureValue.toFixed(2)
    });
    this.inclusion.value = newValue;
  }

  measureValueChanged(event) {
    const newMeasureValue = event.target.value
    const defMeasureValue = this.state.ingredient.measureValue
    const value = this.state.ingredient.defaultValue * (newMeasureValue/defMeasureValue)
    this.setState({
      measureValue: newMeasureValue,
      value: value.toFixed(2)
    });
    this.inclusion.value = value
  }

  render() {
    return (
      <div id={'ingredientInput'+this.id} style={{marginBottom:20}}>
        <div className="ui search selection dropdown">
          <input type="hidden" name="gender"/>
          <i className="dropdown icon"></i>
          <div className="default text">Ingredient</div>
          <div className="menu">
            {this.state.ingredients.map(function(ingredient, index){
              return <div className="item" data-value={index} key={index}>{ingredient.name}</div>
            })}
          </div>
        </div>
        {/* measure */}
        <div className="ui right labeled input" style={{marginLeft:20}}>
          <input type="text" placeholder="Value" value={this.state.measureValue} onChange={this.measureValueChanged.bind(this)} style={{width:75}}/>
          <div className="ui basic label">
            {this.state.ingredient.measureUnit}
          </div>
        </div>
        {/* value */}
        <div className="ui right labeled input" style={{marginLeft:20}}>
          <input type="text" placeholder="Value" value={this.state.value} onChange={this.valueChanged.bind(this)} style={{width:75}}/>
          <div className="ui basic label">
            {this.state.ingredient.defaultUnit}
          </div>
        </div>
      </div>
    )
  }
}
