class FoodPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      data: null,
      errors: null,
      ingredients: [{name: '', defaultUnit: '', defaultValue: ''}]
    }
    this.fetch = this.fetch.bind(this)
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getFood(id)
      .then((data) => {
        this.setState(
          {
            name: data.name,
            url: data.photo,
            ingredients: data.ingredients
          }
        );
      }).catch((err) => {
        this.setState({errors: err.data})
        // TODO: print error message
      })
  }

  render() {
    return (
      <div id='FoodPage'>
        <div className="header">
          <h1 className="ui header"> {this.state.name} </h1>
        </div>
        <div className="content">
          <div>
            <img src={this.state.url} style={{width: 400, height: 400}}/>
          </div>
          <div>
            <h2 className="header"> Ingredients</h2>
            {this.state.ingredients.map(function(ingredient, index){
              return (
                <div>
                  <h3 className="header"> {ingredient.name} </h3>
                  <div> {ingredient.defaultValue || 0} {ingredient.defaultUnit} </div>
                </div>
            )}, this)}
          </div>
        </div>
      </div>
    )
  }
}
