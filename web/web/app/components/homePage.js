class HomePage extends React.Component {

  constructor(props) {
    super(props)
    this.state = {}
  }

  componentDidMount() {
    $('.menu .item').tab()
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
        <div className="ui bottom attached tab segment active" data-tab="search">
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
        </div>
        <div className="ui bottom attached tab segment" data-tab="advancedSearch">
        </div>
      </div>
    )
  }
}
