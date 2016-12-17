import FoodRow from 'food/FoodRow.js'
import RestaurantRow from 'restaurant/RestaurantRow.js'
import IngredientRow from 'ingredient/IngredientRow.js'
import MultipleSelect from 'service/MultipleSelect.js'
import TagSelect from 'service/TagSelect.js'
import Slider from 'service/Slider.js'

export default class Homepage extends React.Component {

  constructor(props) {
    super(props)
    this.state = {}
    this.change = this.change.bind(this)
    this.search = this.search.bind(this)
    this.state = {
      filter: {}
    }
  }

  componentDidMount() {
    $('.menu .item').tab()
  }

  // when input is changed
  change(e) {
    this.setState({query: e.target.value})
    this.search(e.target.value)
  }

  // when advanced search input is changed
  advancedChange(e) {
    this.setState({advancedQuery: e.target.value})
  }

  // send search api call
  search(query) {
    Api.search(query)
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
      selectedIngredients: ingredientIds
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
      selectedDiets: dietIds
    })
  }

  tagsChanged(tags) {
    console.log('tags changed', tags);
    this.setState({
      selectedTags: tags
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

  advancedSearch(e) {
    e.preventDefault()
    let self = this
    let filter = {}

    if (this.state.minProteinVal) filter.minProteinVal = this.state.minProteinVal
    if (this.state.maxProteinVal) filter.maxProteinVal = this.state.maxProteinVal
    if (this.state.minCarbVal) filter.minCarbVal = this.state.minCarbVal
    if (this.state.maxCarbVal) filter.maxCarbVal = this.state.maxCarbVal
    if (this.state.minFatVal) filter.minFatVal = this.state.minFatVal
    if (this.state.maxFatVal) filter.maxFatVal = this.state.maxFatVal
    if (this.state.minEnergy) filter.minEnergy = this.state.minEnergy
    if (this.state.maxEnergy) filter.maxEnergy = this.state.maxEnergy

    if (this.state.advancedQuery) filter.advancedQuery = this.state.advancedQuery
    if (this.state.selectedIngredients) filter.ingredients = this.state.selectedIngredients
    if (this.state.selectedDiets) filter.diets = this.state.selectedDiets
    if (this.state.selectedTags) {
      filter.tags = []
      this.state.selectedTags.forEach((tag) => {
        filter.tags.push(tag.name)
      })
    }
    console.log('filter', filter);
    Api.advancedSearch(filter)
    .then((data) => {
      console.log('advanced search result', data)
      const foodList = data.map((food) => {
        return <FoodRow key={food.id} data={food}/>
      })
      self.setState({advancedFoodList: foodList})
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
                  value={this.state.advanceQuery} onChange={this.advancedChange.bind(this)}
                />
                <div style={{textAlign: 'center', marginTop: 20}}>
                  <button type="submit" className="ui button" onClick={this.advancedSearch.bind(this)}>SEARCH FOOD</button>
                  <h3>Options</h3>
                </div>
                <div>
                  <div style={{display:'inline-block',width:'50%',padding:15}}>
                    <h5>Excluded ingredients</h5>
                    <MultipleSelect onChange={this.ingredientsChanged.bind(this)} setOptions={this.setIngredientOptions} name="foods" placeholder="Select ingredients"/>
                  </div>
                  <div style={{display:'inline-block',width:'50%',padding:15}}>
                    <h5>Diets</h5>
                    <MultipleSelect onChange={this.dietsChanged.bind(this)} setOptions={this.setDietOptions} name="diets" placeholder="Select diet"/>
                  </div>
                  <h5>Semantic tags</h5>
                  <TagSelect onChange={this.tagsChanged.bind(this)} name="tags" placeholder="Search tag"/>
                  <div>
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
                </div>
              </div>
            </form>
          </div>
          <div className="ui relaxed divided list">
            {this.state.advancedFoodList}
          </div>
        </div>
      </div>
    )
  }
}
