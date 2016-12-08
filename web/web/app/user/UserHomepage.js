class ConsumptionHistory extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
    }

    this.fetch = this.fetch.bind(this);
  }

  componentWillMount() {
    this.fetch();
  }

  fetch(){
    Api.consumptionHistory().then(
      (data) => {
        this.setState({data: data})
      }
    ).catch(
      (error) => {
        console.log(error);
      }
    )
  }

  render() {
    return (
      <div className="ui segment">
        consumption history
      </div>
    )
  }
}

import MyDiets from 'diet/MyDiets.js'

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
          <a className="item" data-tab="myDiets">
            My Diets
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
        <div className="ui tab" data-tab="myDiets">
          <MyDiets/>
        </div>
      </div>
    )
  }
}
