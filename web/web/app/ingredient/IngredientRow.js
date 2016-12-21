export default class IngredientRow extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      name: props.data.name,
      id: props.data.id
    }
  }

  render() {
    return (
      <div className="item" style={{marginBottom:10}}>
        <div className="content">
          <a className="header" style={{fontSize:16}} href={"/ingredients/" + this.state.id}>{this.state.name}</a>
        </div>
      </div>
    )
  }
}
