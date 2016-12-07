class ConsumptionHistory extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
    }
  }
  render() {
    return (
      <div className="ui segment">
        consumption history
      </div>
    )
  }
}

export default class UserHomepage extends React.Component
{
  constructor(props)
  {
    super(props)
  }

  componentDidMount(){
    $('#userHomepage .item').tab();
  }

  render() {
    return (
      <div id="userHomepage" className="ui-container">
        <div className="ui pointing menu">
          <a className="item" data-tab="consumptionHistory">
            Consumption History
          </a>
          <a className="item" data-tab="favFoods">
            Favorite Foods
          </a>
          <a className="item" data-tab="favRestaurants">
            Favorite Restaurants
          </a>
        </div>
        <div className="ui tab" data-tab="consumptionHistory">
          <ConsumptionHistory/>
        </div>
      </div>
    )
  }
}
