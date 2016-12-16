import FoodRow from 'food/FoodRow.js'
import RestaurantRow from 'restaurant/RestaurantRow.js'
import IngredientRow from 'ingredient/IngredientRow.js'

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

  render() {
    return (
      <div>
        <div className="ui top attached tabular menu">
          <a className="item active" data-tab="search">
            Search
          </a>
          <a className="item" data-tab="advancedSearch">
            Advanced Search
          </a>
        </div>
        {/* search tab content */}
        <div className="ui bottom attached tab segment active" data-tab="search">
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
        <div className="ui bottom attached tab segment" data-tab="advancedSearch">
        </div>
      </div>
    )
  }
}
