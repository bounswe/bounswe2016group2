export default class MultipleIngredientInput extends React.Component {
  constructor(props) {
    super(props)
    this.ingredients = props.ingredients;
    this.state = {
      ingredients: [],
      selectedIngredients: []
    }
  }

  componentDidMount() {
    var self = this;
    // fetch ingredeints
    Api.getIngredients()
    .then((data) => {
      this.setState(
        {
          ingredients: data
        }
      );
      // searchable semantic dropdown for ingredient select
      $('#MultipleIngredientInput .ui.dropdown').dropdown({
        onChange(index) {
          let indices = index.split(',');
          let ingredientIds = [];
          indices.forEach(function(index){
            ingredientIds.push(self.state.ingredients[index].id);
          })
          self.ingredients.list = ingredientIds;
        }
      })
    }).catch((err) => {
      this.setState({errors: err});
    })
  }

  render() {
    return (
      <div id="MultipleIngredientInput">
        <div className="ui search selection dropdown multiple">
          <i className="dropdown icon"></i>
          <div className="default text">Select ingredients...</div>
          <div className="menu">
            {this.state.ingredients.map(function(ingredient, index){
              return <div className="item" data-value={index} key={index}>{ingredient.name}</div>
            })}
          </div>
        </div>
      </div>
    )
  }
}
