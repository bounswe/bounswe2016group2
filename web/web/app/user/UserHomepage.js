import DatePicker from 'service/DatePicker.js'
import MyDiets from 'diet/MyDiets.js'

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
      <div className="ui segment" style={{display:'flex'}}>
        <DatePicker name="consumptionStartDate" placeholder="Start Date" default={moment().subtract(1, 'month').toDate()}/>
        <DatePicker name="consumptionEndDate" placeholder="End Date" default={moment().toDate()}/>
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
          <a className="item active" data-tab="consumptionHistory">
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
        <div className="ui tab active" data-tab="consumptionHistory">
          <ConsumptionHistory/>
        </div>
        <div className="ui tab" data-tab="myDiets">
          <MyDiets/>
        </div>
      </div>
    )
  }
}
