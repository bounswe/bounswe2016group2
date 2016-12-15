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

    this.input = {
      fromDate: moment().subtract(1, 'month').format('DD-MM-YYYY'),
      toDate: moment().format('DD-MM-YYYY')
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

  fromDateChanged(e) {
    this.input.fromDate = moment(e).format('DD-MM-YYYY');
  }

  toDateChanged(e) {
    this.input.toDate = moment(e).format('DD-MM-YYYY');
  }

  render() {
    return (
      <div className="ui segment">
        <div style={{display:'flex', alignItems: 'center'}}>
          <span style={{marginLeft:10, marginRight:10}}>From</span>
          <DatePicker name="consumptionStartDate" placeholder="Start Date" default={this.input.fromDate} onChange={this.fromDateChanged.bind(this)}/>
          <span style={{marginLeft:10, marginRight:10}}>to</span>
          <DatePicker name="consumptionEndDate" placeholder="End Date" default={this.input.toDate} onChange={this.toDateChanged.bind(this)}/>
          <button className="ui button" style={{marginLeft:10, marginRight:10}}>Refresh</button>
        </div>
        <div style={{marginTop:20}}>
          {this.state.data.daily.map((dailyData) => {
            return (
              <div key={dailyData.date}>
                <h3>{dailyData.date}</h3>
                {dailyData.ateFoods.map((ateFood) => {
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
