import DatePicker from 'service/DatePicker.js'
import MyDiets from 'diet/MyDiets.js'
import FoodRow from 'food/FoodRow.js'

class ConsumptionHistory extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      data: {
        total: {},
        daily: []
      }
    }

    this.fetch = this.fetch.bind(this);
  }

  componentWillMount() {
    this.fetch();
  }

  fetch(){
    Api.consumptionHistory().then(
      (data) => {
        console.log(data);
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
        <div style={{display:'flex', alignItems: 'center'}}>
          <span style={{marginLeft:10, marginRight:10}}>From</span>
          <DatePicker name="consumptionStartDate" placeholder="Start Date" default={moment().subtract(1, 'month').toDate()}/>
          <span style={{marginLeft:10, marginRight:10}}>to</span>
          <DatePicker name="consumptionEndDate" placeholder="End Date" default={moment().toDate()}/>
        </div>
        <div style={{marginTop:20}}>
          {Object.keys(this.state.data.daily).map((key) => {
            return (
              <div>
                <h3 key={key}>{key}</h3>
                {this.state.data.daily[key].ateFoods.map((ateFood) => {
                  return <FoodRow key={ateFood.created} data={ateFood.food}/>
                })}
              </div>
            )
          })}
        </div>
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
