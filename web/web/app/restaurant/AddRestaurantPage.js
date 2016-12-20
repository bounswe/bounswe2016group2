import MultipleSelect from '../service/MultipleSelect.js'

export default class AddFoodPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      errors: null,
      foods: []
    }
  }

  setFoodSelectOptions() {
    const self = this
    Api.me()
      .then((data) => {
        self.setState({
          options: data.foods
        })
      })
  }

  submit(e) {
    e.preventDefault()
    const postData = {
      name: this.state.name,
      photo: this.state.photo,
      description: this.state.description
    }
    Api.addRestaurant(postData)
      .then((data) => {
        console.log(data);
        let foodPromises = []
        let restaurantId = data.id
        this.state.foods.forEach((food,index) => {
          foodPromises.push(Api.addFoodToRestaurant(restaurantId, food))
        });

        // Evenly, it's possible to use .catch
        Promise.all(foodPromises).then(() => {
          router.navigate('../restaurants/' + data.id)
        }).catch(reason => {
          console.log('error', reason)
          router.navigate('../restaurants/' + data.id)
        })

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

  foodsChanged(foods) {
    this.setState({
      foods: foods
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
              <MultipleSelect onChange={this.foodsChanged.bind(this)} setOptions={this.setFoodSelectOptions} name="foods" placeholder="Select foods"/>
            </div>
            { this.state.errors && this.state.errors.foods &&
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
