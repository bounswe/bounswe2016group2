class IngrPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      data: [{}],
      errors: null
    }
    this.fetch = this.fetch.bind(this)
    this.ateThis = this.ateThis.bind(this)
    this.ingrProps = this.ingrProps.bind(this)
  }

  componentDidMount() {
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getIngr(id)
      .then((data) => {
        this.setState(
          {
            name: data.name,
            url: data.photo,
            data: data
          }
        );
        this.ingrProps();
      }).catch((err) => {
        this.setState({errors: err})
        // TODO: print error message
      })
  }

  ingrProps() {
    var result = [];
    for(var prop in this.state.data){
      var propValue = this.state.data[prop];
      result.push(
        <div>
          <label style={{"font-weight": "bold"}}> {prop} </label>
          <label> {propValue} </label>
        </div>
      );
    }
    this.setState({ingrProps: result});
  }

// TODO: currently nor working
  ateThis() {
    var query = {
      value: 1
    }
    Api.ingredientAte(this.state.id, query)
      .then((data) => {
        console.log(data);
      }).catch((err) => {
        console.log(err);
      })
  }

  render() {
    return (
      <div id='IngredientPage'>
        <div className="header">
          <h1 className="ui header"> {this.state.name || "Ingredient not found"} </h1>
        </div>
        <div className="content">
          <div>
            {
              this.state.url &&
              <img src={this.state.url} style={{width: 400, height: 400}}/>
            }
          </div>
          {
            userEmail && this.state.name &&
            <button className="ui button" type="button" style={{width:'10%'}} onClick={this.ateThis}>
              I ate this!
            </button>
          }
          <div>
            <h2 className="header"> Properties</h2>
            {this.state.ingrProps}
          </div>
        </div>
      </div>
    )
  }
}
