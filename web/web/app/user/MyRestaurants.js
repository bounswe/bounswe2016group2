export default class MyRestaurants extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      restaurants: props.restaurants
    }
  }

  componentDidMount() {
    var self = this;
    $('#restaurantSelect.ui.dropdown').dropdown({
      onChange(index) {
        console.log(self.state.restaurants[index]);
      }
    })
  }

  render() {
    return (
      <div className="ui segment">
        <div id="restaurantSelect" className="ui search selection dropdown">
          <i className="dropdown icon"></i>
          <div className="default text">Restaurant</div>
          <div className="menu">
            {this.state.restaurants.map(function(restaurant, index){
              return <div className="item" data-value={index} key={index}>{restaurant.name}</div>
            })}
          </div>
        </div>
      </div>
    )
  }
}
