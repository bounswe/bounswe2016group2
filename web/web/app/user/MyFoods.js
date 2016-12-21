export default class MyFoods extends React.Component {

  constructor(props) {
    super(props)
    this.state = {
      foods: props.foods
    }
  }

  componentDidMount() {
    var self = this;
    $('#foodSelect.ui.dropdown').dropdown({
      onChange(index) {
        router.navigate('../foods/' + self.state.foods[index].id);
      }
    })
  }

  render() {
    return (
      <div className="ui segment">
        <div id="foodSelect" className="ui search selection dropdown">
          <i className="dropdown icon"></i>
          <div className="default text">Food</div>
          <div className="menu">
            {this.state.foods.map(function(food, index){
              return <div className="item" data-value={index} key={index}>{food.name}</div>
            })}
          </div>
        </div>
      </div>
    )
  }
}
