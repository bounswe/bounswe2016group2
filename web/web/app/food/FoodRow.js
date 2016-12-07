export default class FoodRow extends React.Component {
  constructor(props) {
    super(props)
    let ingredientStr = ''
    props.data.ingredients.forEach((ingredient, i) => {
      if (i != 0) ingredientStr += ', '
      ingredientStr += ingredient.name
    })
    this.state = {
      name: props.data.name,
      id: props.data.id,
      ingredientStr: ingredientStr
    }
  }

  render() {
    return (
      <div className="item" style={{marginBottom:10}}>
        <div className="content">
          <a className="header" style={{fontSize:16}} href={"/foods/" + this.state.id}>{this.state.name}</a>
          <div className="description">{this.state.ingredientStr}</div>
        </div>
      </div>
    )
  }
}
