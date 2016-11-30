class IngredientInput extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      ingredients: [{id:12,name:'asd'}]
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
      $('#ingredientInput1 .ui.dropdown').dropdown({
        onChange(index) {
          self.setState({
            ingredient: self.state.ingredients[index]
          })
        }
      })
    }).catch((err) => {
      this.setState({errors: err});
    })
  }

  render() {
    // console.log(this.state.ingredients);
    return (
      <div id='ingredientInput1'>
        <div className="ui search selection dropdown">
          <input type="hidden" name="gender"/>
          <i className="dropdown icon"></i>
          <div className="default text">Gender</div>
          <div className="menu">
            {this.state.ingredients.map(function(ingredient, index){
              return <div className="item" data-value={index} key={index}>{ingredient.name}</div>
            })}
          </div>
        </div>
        <div className="ui right labeled input">
          <input type="text" placeholder="Find domain"/>
          <div className="ui dropdown label">
            <div className="text">g</div>
            <i className="dropdown icon"></i>
            <div className="menu">
              <div className="item">mg</div>
              <div className="item">kg</div>
              <div className="item">l</div>
              <div className="item">teaspoon</div>
              <div className="item">dessertspoon</div>
              <div className="item">tablespoon</div>
              <div className="item">cup</div>
              <div className="item">pint</div>
              <div className="item">quart</div>
              <div className="item">gallon</div>
            </div>
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
      ingredientInputs: [{}]
    }
  }


  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div className="ui segments">
        <div className="ui segment">
          Add New Food
        </div>
        {/* food name and image */}
        <div className="ui segment">
          <form className={formClassName}>
            <div className="field">
              <label> Name </label>
              <input type="text" name="name" placeholder="name" value={this.state.name} onChange={this.nameChanged}/>
              { this.state.errors && this.state.errors.name &&
                <div className="ui error message">
                  <p>{this.state.errors.name[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label> Image </label>
              <input type="url" name="image" placeholder="image url" value={this.state.url} onChange={this.urlChanged}/>
            </div>
          </form>
        </div>
        {/* ingredients */}
        <div className="ui segment">
          Ingredients
        </div>

        <div className="ui segment">
          <IngredientInput key={0}/>
        </div>
      </div>
    )
  }
}

class AddFood extends React.Component {

  constructor (props) {
  super(props)
  this.state = {
    data: null,
    errors: null,
    ingredients: [{name: '', weight: '', list: '', id: '', errors: ''}]
  }
  this.nameChanged = this.nameChanged.bind(this)
  this.urlChanged = this.urlChanged.bind(this)
  this.submit = this.submit.bind(this)
  this.ingredientNameChanged = this.ingredientNameChanged.bind(this)
  this.ingredientWeightChanged = this.ingredientWeightChanged.bind(this)
  this.appendIngredient = this.appendIngredient.bind(this)
  this.removeIngredient = this.removeIngredient.bind(this)
}

    nameChanged(e) {this.setState({name: e.target.value})}
    urlChanged(e) {this.setState({url: e.target.value})}
    ingredientNameChanged(index, e) {
      var ingArray = this.state.ingredients;
  var currentIng = ingArray[index];
  currentIng.name = e.target.value;
  ingArray[index] = currentIng
  this.setState({ingredients: ingArray});
  this.ingredientSearch(currentIng.name, index);
}
  ingredientWeightChanged(index, e) {
  var ingArray = this.state.ingredients;
  var currentIng = ingArray[index];
  currentIng.weight = e.target.value;
  ingArray[index] = currentIng
  this.setState({ingredients: ingArray});
}

  appendIngredient(e){
    e.preventDefault();
    var newIng = {name: '', weight: '', id: ''};
    this.setState({ingredients: this.state.ingredients.concat(newIng)});
  }
  removeIngredient(index, e){
    e.preventDefault();
    var ingArray = this.state.ingredients;
    ingArray.splice(index, 1);
    this.setState({ingredients: ingArray});
  }

ingredientSearch(query, index) {
  Api.searchIngredient(query)
    .then((ingres) => {
      const list = ingres.map((ing) => {
        this.setState({ingredientId: ing.id});
        return (
					<div className="item">
						{ing.name}
					</div>
				)
      })
      var ingArray = this.state.ingredients;
      var currentIng = ingArray[index];
      currentIng.list = list;
      currentIng.id = ingres[ingres.length-1].id;
      ingArray[index] = currentIng
      this.setState({ingredients: ingArray})
    })
	}

  submit(e) {
    e.preventDefault()
    this.setState({errors: null})
    const postData = {
      name: this.state.name,
      photo: this.state.url
    }

    Api.addFood(postData)
      .then((data) => {
        this.state.ingredients.forEach(function(ingredient,index) {
          const currentIng = {
            weight: parseInt(ingredient.weight),
            ingredient: ingredient.id,
            food: data.id
          }
          Api.addIngredientToFood(currentIng.food, currentIng.ingredient, currentIng)
            .then((data) => {
            }).catch((err) => {
              var ingArray = this.state.ingredients;
              var currentIng = ingArray[index];
              currentIng.errors = err.data.weight[0];
              ingArray[index] = currentIng;
              this.setState({ingredients: ingArray, errors: err.data});

              Api.deleteFood(data.id).then().catch();
              return;
            })}, this);

        // TODO: redirect to the page of newly added food
        window.location.href='/food/' + data.id;
      }).catch((err) => {
        this.setState({errors: err.data})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div id='addFoodForm'>
        <div className="arda" style={{width: "100%", height: 150, paddingBottom: "15%"}}>
          <img src={'data:image/jpeg;base64,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'}
            style={{width: 170, height: 100, position: 'absolute', left: 0, backgroundColor:'white'}}/>
          <div className="ui six item menu" style={{width: "100%", height: 20, position: 'absolute', left: 0, top: "18%", backgroundColor:'white'}}>
            <a className="item" href="/">Home</a>
            <a className="item">Favorites</a>
            <a className="item active">Add Food</a>
            <a className="item">Daily Analysis</a>
            <a className="item">Search</a>
            <a className="item">Advanced Search</a>
          </div>
        </div>
        <div className="header">
          Add New Food
        </div>
        <div className="content">
          <form className={formClassName}>
            <div className="field">
              <label> Name </label>
              <input type="text" name="name" placeholder="name" value={this.state.name} onChange={this.nameChanged}/>
              { this.state.errors && this.state.errors.name &&
                <div className="ui error message">
                  <p>{this.state.errors.name[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label> Image </label>
              <input type="url" name="image" placeholder="image url" value={this.state.url} onChange={this.urlChanged}/>
            </div>
            {this.state.ingredients.map(function(ingredient, index){
              return (
                <div className="fields">
                  <div className="field">
                    <label> Ingredient </label>
                    <input type="text" placeholder="ingredient" value={ingredient.name} onChange={this.ingredientNameChanged.bind(this, index)}/>
                    <ul className='ui list'>{ingredient.list}</ul>
                  </div>
                  <div className="field">
                    <label> Weight </label>
                    <input type="text" placeholder="weight" value={ingredient.weight} onChange={this.ingredientWeightChanged.bind(this, index)}/>
                    { ingredient.errors != '' &&
                      <div className="ui error message">
                        <p>{ingredient.errors}</p>
                      </div>
                    }
                  </div>
                  {index !== 0 &&
                    <a href="javascript:void(0);" onClick={this.removeIngredient.bind(this, index)}>remove</a>
                  }
                </div>
              )
            }, this)}
            <a href="javascript:void(0);" onClick={this.appendIngredient}>Append ingredient</a>

            <button className="ui button" type="submit" style={{width:'100%'}} onClick={this.submit}>
              Add Food
            </button>
          </form>
        </div>
      </div>
    )
  }
}
