class FoodRow extends React.Component {
  constructor(props) {
    super(props)
    let ingredientStr = ''
    props.data.ingredients.forEach((ingredient) => {
      ingredientStr += ' ' + ingredient.name
    })
    this.state = {
      name: props.data.name,
      id: props.data.id,
      ingredientStr: ingredientStr
    }
    this.click = this.click.bind(this);
  }

  click(){
    var link = '/food/' + this.state.id;
    return(
      function(){window.location.href = link}
    );
  }
  render() {
    return (
      <a className="item" onClick={this.click()}>
        <div className="header">{this.state.name}</div>
        {this.state.ingredientStr}
      </a>
    )
  }
}
