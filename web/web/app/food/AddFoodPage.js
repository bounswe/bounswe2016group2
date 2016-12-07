import IngredientInput from 'ingredient/IngredientInput.js'

export default class AddFoodPage extends React.Component {

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
