import FoodRow from 'food/FoodRow.js'
import RestaurantRow from 'restaurant/RestaurantRow.js'
import IngredientRow from 'ingredient/IngredientRow.js'
import MultipleSelect from 'service/MultipleSelect.js'
import TagSelect from 'service/TagSelect.js'

export default class Homepage extends React.Component {

  constructor(props) {
    super(props)
    this.state = {}
    this.change = this.change.bind(this)
    this.search = this.search.bind(this)
  }

  componentDidMount() {
    $('.menu .item').tab()
  }

  // when input is changed
  change(e) {
    this.setState({query: e.target.value})
    this.search(e.target.value)
  }

  // send search api call
  search(query) {
    Api.searchFood(query)
    .then((data) => {
      const foodList = data.foods.map((food) => {
        return <FoodRow key={food.id} data={food}/>
      })
      const restaurantList = data.restaurants.map((restaurant) => {
        return <RestaurantRow key={restaurant.id} data={restaurant}/>
      })
      const ingredientList = data.ingredients.map((ingredient) => {
        return <IngredientRow key={ingredient.id} data={ingredient}/>
      })
      this.setState({
        foodList: foodList,
        restaurantList: restaurantList,
        ingredientList: ingredientList
      })
    })
  }

  setIngredientOptions() {
    const self = this
    Api.getIngredients()
    .then((data) => {
      self.setState({
        options: data
      })
    })
  }

  ingredientsChanged(ingredientIds) {
    console.log('excluded ingredient ids', ingredientIds);
    this.setState({
      filter: {
        ingredients: ingredientIds
      }
    })
  }

  setDietOptions() {
    const self = this
    Api.getDiets()
    .then((data) => {
      self.setState({
        options: data
      })
    })
  }

  dietsChanged(dietIds) {
    console.log('diets', dietIds);
    this.setState({
      filter: {
        diets: dietIds
      }
    })
  }

  tagsChanged(tags) {
    console.log('tags changed', tags);
    this.setState({
      filter: {
        tags: tags
      }
    })
  }

  render() {
    return (
      <div>
        <div className="ui top attached tabular menu">
          <a className="item" data-tab="search">
            Search
          </a>
          <a className="item active" data-tab="advancedSearch">
            Advanced Search
          </a>
        </div>
        {/* search tab content */}
        <div className="ui bottom attached tab segment" data-tab="search">
          {/* search form  */}
          <div>
            <form className="ui form">
              <div className="field">
                <input type="text" name="food" placeholder="Search food, ingredient or server"
                  value={this.state.query} onChange={this.change}
                />
              </div>
            </form>
            <form className="ui form"></form>
          </div>
          {/* food list */}
          { this.state.foodList && this.state.foodList.length > 0 && <h4>Foods</h4>}
          <div className="ui relaxed divided list">
            {this.state.foodList}
          </div>
          { this.state.restaurantList && this.state.restaurantList.length > 0 && <h4>Restaurants</h4>}
          <div className="ui relaxed divided list">
            {this.state.restaurantList}
          </div>
          { this.state.ingredientList && this.state.ingredientList.length > 0 && <h4>Ingredients</h4>}
          <div className="ui relaxed divided list">
            {this.state.ingredientList}
          </div>
        </div>
        <div className="ui bottom attached tab segment active" data-tab="advancedSearch">
          {/* search form  */}
          <div>
            <form className="ui form">
              <div className="field">
                <input type="text" name="food" placeholder="Search food"
                  value={this.state.query} onChange={this.change}
                />
                <div>
                  <div style={{display:'inline-block',width:'50%',padding:15}}>
                    <h5>Excluded ingredients</h5>
                    <MultipleSelect onChange={this.ingredientsChanged.bind(this)} setOptions={this.setIngredientOptions} name="foods" placeholder="Select ingredients"/>
                  </div>
                  <div style={{display:'inline-block',width:'50%',padding:15}}>
                    <h5>Diets</h5>
                    <MultipleSelect onChange={this.dietsChanged.bind(this)} setOptions={this.setDietOptions} name="diets" placeholder="Select diet"/>
                  </div>
                  <TagSelect onChange={this.tagsChanged.bind(this)} name="tags" placeholder="Search tag"/>
                </div>
              </div>
            </form>
            <form className="ui form"></form>
          </div>
        </div>
      </div>
    )
  }
}
