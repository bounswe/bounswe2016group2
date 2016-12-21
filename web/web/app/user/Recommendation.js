import FoodRow from 'food/FoodRow.js'
export default class Recommendation extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      recommendation: (<div> No Recommendation </div>)
    };
    this.fetch = this.fetch.bind(this);
    this.fetch();
  }
  fetch(){
    let ateFoods = [];
    Api.consumptionHistory().then(
      (data) => {
        ateFoods = data.total.ateFoods;
        // get last 5 consumed food
        if(ateFoods > 5){
          ateFoods = ateFoods.slice(0, 5);
        }
        var tagList = [];
        // get semantic tags of these foods
        for (var food in ateFoods) {
            for (var tag in ateFoods[food].food.tags) {
              tagList.push(ateFoods[food].food.tags[tag].name);
            }
        }
        let filter = {}
        filter.tags = tagList;
        Api.advancedSearch(filter).then((data) => {
          if(data.length > 5){
            data = data.slice(0, 5);
          }
          const foodList = data.map((food) => {
            return <FoodRow key={food.id} data={food}/>
          })
          if(data.length > 0){
            this.setState({recommendation: foodList})
          }
        }).catch((error) => {
          this.setState({errors: error})
        })
      }
    ).catch(
      (error) => {
        this.setState({errors: error});
      }
    )
  }
  render() {
    return (
      <div className="ui segments">
        {/* Recommendation List */}
        <div className="ui segment">
          <h2 className="ui header" style={{textAlign:'center'}}>Recommended Foods</h2>
        </div>
        <div className="ui segment">
          {this.state.recommendation}
        </div>
      </div>
    );
  }
}
