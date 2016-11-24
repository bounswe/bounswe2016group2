class FoodInput extends React.Component {
  constructor(props) {
    super(props)
    this.state = {}
    this.search = props.search
    this.change = this.change.bind(this)
  }
  change(e) {
    this.setState({query: e.target.value})
    this.search(e.target.value)
  }
  render() {
    return (
      <div>
        <form className="ui form">
          <div className="field">
            <input type="text" name="food" placeholder="Search food"
              value={this.state.query} onChange={this.change}
            />
          </div>
        </form>
        <form className="ui form"></form>
    </div>
    )
  }
}

class Food extends React.Component {
  constructor(props) {
    super(props)
    let ingredientStr = ''
    props.data.ingredients.forEach((ingredient) => {
      ingredientStr += ' ' + ingredient.name
    })
    this.state = {
      name: props.data.name,
      ingredientStr: ingredientStr
    }
  }
  render() {
    return (
      <div className="item">
        <div className="header">{this.state.name}</div>
        {this.state.ingredientStr}
      </div>
    )
  }
}

class FoodSearch extends React.Component {
  constructor(props) {
    super(props)
    this.search = this.search.bind(this)
    this.state = {foods: []}
  }
  search(query) {
    Api.searchFood(query)
    .then((foods) => {
      const list = foods.map((food) => {
        return <Food key={food.id} data={food}/>
      })
      this.setState({list: list})
    })
  }
  render() {
    return (
      <div className='ui center aligned segment'>
        <FoodInput search={this.search}/>
        <ul className='ui list'>{this.state.list}</ul>
      </div>
    )
  }
}
