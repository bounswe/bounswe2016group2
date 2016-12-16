import MyDiets from 'diet/MyDiets.js'
import FoodRow from 'food/FoodRow.js'
import ConsumptionHistory from 'user/ConsumptionHistory.js'
import MyFoods from 'user/MyFoods.js'
import MyRestaurants from 'user/MyRestaurants.js'

export default class UserHomepage extends React.Component
{
  constructor(props)
  {
    super(props)
    this.state = {
      user: {}
    }
  }

  componentDidMount(){
    $('#userHomepage .item').tab();
  }

  componentWillMount() {
    this.fetch();
  }

  fetch(){
    Api.me().then(
      (data) => {
        console.log(data);
        this.setState({user: data})
        $('#userHomepage .item').tab();
      }
    ).catch(
      (error) => {
        console.log(error);
      }
    )
  }

  render() {
    return (
      <div id="userHomepage" className="ui-container">
        <div className="ui pointing menu">
          <a className="item active" data-tab="consumptionHistory">
            Consumption History
          </a>
          <a className="item" data-tab="myFoods">
            My Foods
          </a>
          { this.state.user.isServer &&
            <a className="item" data-tab="myRestaurants">
              My Restaurants
            </a>
          }
          <a className="item" data-tab="myDiets">
            My Diets
          </a>
        </div>
        <div className="ui tab active" data-tab="consumptionHistory">
          <ConsumptionHistory/>
        </div>
        <div className="ui tab" data-tab="myFoods">
          <MyFoods/>
        </div>
        { this.state.user.isServer &&
          <div className="ui tab" data-tab="myRestaurants">
            <MyRestaurants restaurants={this.state.user.restaurants}/>
          </div>
        }
        <div className="ui tab" data-tab="myDiets">
          <MyDiets/>
        </div>
      </div>
    )
  }
}
