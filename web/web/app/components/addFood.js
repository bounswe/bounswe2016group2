class AddFood extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      data: null,
      errors: null,
      ingredient: '',
      ingredientId: '',
      ingredients: [{name: '', weight: '', list: '', id: '', errors: ''}]
    }
    this.nameChanged = this.nameChanged.bind(this)
    this.slugChanged = this.slugChanged.bind(this)
    this.submit = this.submit.bind(this)
    this.ingredientNameChanged = this.ingredientNameChanged.bind(this)
    this.ingredientWeightChanged = this.ingredientWeightChanged.bind(this)
    this.appendIngredient = this.appendIngredient.bind(this)
  }

  nameChanged(e) {this.setState({name: e.target.value})}
  slugChanged(e) {this.setState({slug: e.target.value})}
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
      slug: this.state.slug
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
      }).catch((err) => {
        this.setState({errors: err.data})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div id='addFoodForm'>
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
              <label> Slug </label>
              <input type="text" name="slug" placeholder="slug" value={this.state.slug} onChange={this.slugChanged}/>
              { this.state.errors && this.state.errors.slug &&
                <div className="ui error message">
                  <p>{this.state.errors.slug[0]}</p>
                </div>
              }
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

