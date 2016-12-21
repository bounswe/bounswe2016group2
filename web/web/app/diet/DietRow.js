export default class DietRow extends React.Component {
  constructor(props) {
    super(props)
    let excludedIngredientStr = ''
    props.data.ingredients.forEach((ingredient, i) => {
      if (i != 0) excludedIngredientStr += ', '
      excludedIngredientStr += ingredient.name
    })
    if(excludedIngredientStr)
      excludedIngredientStr += " excluded";
      
    this.state = {
      name: props.data.name,
      id: props.data.id,
      excludedIngredientStr: excludedIngredientStr
    }
  }

  render() {
    return (
      <div className="item" style={{marginBottom:10, textAlign: 'center'}}>
        <div className="content">
          <a className="header" style={{fontSize:16}} href={"/diets/" + this.state.id}>{this.state.name}</a>
          <div className="description">{this.state.excludedIngredientStr}</div>
        </div>
      </div>
    )
  }
}
