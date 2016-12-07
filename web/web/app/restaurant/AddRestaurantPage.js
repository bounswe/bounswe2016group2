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

  submit(e) {
    e.preventDefault()
    this.setState({errors: null})
    const postData = {
      name: this.state.name,
      photo: this.state.photo
    }
    Api.addRestaurant(postData)
      .then((data) => {

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

  descriptionChanged(event) {
    this.setState({
      description: event.target.value
    })
  }

  photoChanged(event) {
    this.setState({
      photo: event.target.value
    })
  }

  foodsChanged(event) {
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
          Add New Restaurant
        </div>
        {/* food name and photo */}
        <div className="ui segment">
          <form className={formClassName}>
            <div className="field">
              <label>Name</label>
              <input type="text" name="name" placeholder="name" value={this.state.name} onChange={this.nameChanged.bind(this)}/>
              { this.state.errors && this.state.errors.name &&
                <div className="ui error message">
                  <p>{this.state.errors.name[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label>Description</label>
              <input type="text" name="description" placeholder="description" value={this.state.description} onChange={this.descriptionChanged.bind(this)}/>
              { this.state.errors && this.state.errors.description &&
                <div className="ui error message">
                  <p>{this.state.errors.description[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label>Photo</label>
              <input type="url" name="photo" placeholder="image url" value={this.state.photo} onChange={this.photoChanged.bind(this)}/>
            </div>
            { this.state.errors && this.state.errors.photo &&
              <div className="ui error message">
                <p>{this.state.errors.photo[0]}</p>
              </div>
            }
            <div className="field">
              <label>Foods</label>
              <input type="url" name="foods" placeholder="image url" value={this.state.foods} onChange={this.foodsChanged.bind(this)}/>
            </div>
            { this.state.errors && this.state.errors.photo &&
              <div className="ui error message">
                <p>{this.state.errors.foods[0]}</p>
              </div>
            }
          </form>
        </div>
        {/* foods */}
        <div className="ui segment" style={{textAlign:'center'}}>
          <button className="ui button" type='submit' onClick={this.submit.bind(this)}>Submit</button>
        </div>
      </div>
    )
  }
}
