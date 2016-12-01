class IngredientInput extends React.Component {
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

class AddFoodPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      data: null,
      errors: null,
      ingredients: [],
      inclusions: [{'ingredientId':null,'unit':'g'}]
    }
  }

  addMoreIngredient() {
    this.setState({
      inclusions: this.state.inclusions.concat([{'ingredientId':null,'unit':'g'}])
    })
  }

  submit(e) {
    e.preventDefault()
    this.setState({errors: null})
    const postData = {
      name: this.state.name,
      photo: this.state.photo
    }
    Api.addFood(postData)
      .then((data) => {
        console.log(data);
        let inclusionProms = []
        let foodId = data.id
        this.state.inclusions.forEach((inclusion,index) => {
          let ingredientId = inclusion.ingredientId
          let newData = {
            'value': inclusion.value,
            'unit': inclusion.unit
          }
          console.log(data.id, ingredientId, newData);
          if (ingredientId) {
            inclusionProms.push(Api.addIngredientToFood(data.id, ingredientId, newData))
          }
          // console.log(this.state);
        });

        // Evenly, it's possible to use .catch
        Promise.all(inclusionProms).then(values => {
          router.navigate('../foods/' + data.id)
        }).catch(reason => {
          console.log('error', reason)
          router.navigate('../foods/' + data.id)
        })

        // this.state.ingredients.forEach((ingredient,index) {
        //   const currentIng = {
        //     weight: parseInt(ingredient.weight),
        //     ingredient: ingredient.id,
        //     food: data.id
        //   }
        //   Api.addIngredientToFood(currentIng.food, currentIng.ingredient, currentIng)
        //     .then((data) => {
        //     }).catch((err) => {
        //       var ingArray = this.state.ingredients;
        //       var currentIng = ingArray[index];
        //       currentIng.errors = err.data.weight[0];
        //       ingArray[index] = currentIng;
        //       this.setState({ingredients: ingArray, errors: err.data});
        //
        //       Api.deleteFood(data.id).then().catch();
        //       return;
        //   })}, this);
      }).catch((err) => {
        console.log(err);
        this.setState({errors: err.data})
      })
  }

  nameChanged(event) {
    this.setState({
      name: event.target.value
    })
  }

  photoChanged(event) {
    this.setState({
      photo: event.target.value
    })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div className="ui segments">
        <div className="ui segment">
          Add New Food
        </div>
        {/* food name and photo */}
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
              <label> Photo </label>
              <input type="url" name="photo" placeholder="image url" value={this.state.photo} onChange={this.photoChanged.bind(this)}/>
            </div>
            { this.state.errors && this.state.errors.photo &&
              <div className="ui error message">
                <p>{this.state.errors.photo[0]}</p>
              </div>
            }
          </form>
        </div>
        {/* ingredients */}
        <div className="ui segment">
          Ingredients
        </div>

        <div className="ui segment">
          {this.state.inclusions.map(function(inclusion, index){
            return <IngredientInput id={index} key={index} inclusion={inclusion}/>
          })}
          <button className="ui button" onClick={this.addMoreIngredient.bind(this)}>Add more</button>
        </div>
        <div className="ui segment" style={{textAlign:'center'}}>
          <button className="ui button" type='submit' onClick={this.submit.bind(this)}>Submit</button>
        </div>
      </div>
    )
  }
}

class AddFood extends React.Component {

  removeIngredient(index, e){
    e.preventDefault();
    var ingArray = this.state.ingredients;
    ingArray.splice(index, 1);
    this.setState({ingredients: ingArray});
  }
}
