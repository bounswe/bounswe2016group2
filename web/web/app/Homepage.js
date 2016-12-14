import FoodRow from 'food/FoodRow.js'

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
      const list = data.foods.map((food) => {
        return <FoodRow key={food.id} data={food}/>
      })
      this.setState({list: list})
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
          {/* search list */}
          <div className="ui relaxed divided list">
            {this.state.list}
          </div>
        </div>
        <div className="ui bottom attached tab segment" data-tab="advancedSearch">
        </div>
      </div>
    )
  }
}
