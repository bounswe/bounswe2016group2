class IngrPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      data: [{}],
      errors: null
    }
    this.fetch = this.fetch.bind(this)
    this.ingrProps = this.ingrProps.bind(this)
  }

  componentDidMount() {
    this.fetch(this.state.id);
  }

  fetch(id) {
    Api.getIngr(id)
      .then((data) => {
        console.log(data);
        this.setState(
          {
            name: data.name,
            url: data.photo,
            data: data
          }
        );
        this.ingrProps();
      }).catch((err) => {
        this.setState({errors: err.data})
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

  render() {
    return (
      <div id='IngredientPage'>
        <div className="header">
          <h1 className="ui header"> {this.state.name} </h1>
        </div>
        <div className="content">
          <div>
            {
              this.state.url &&
              <img src={this.state.url} style={{width: 400, height: 400}}/>
            }
          </div>
          <div>
            <h2 className="header"> Properties</h2>
            {this.state.ingrProps}
          </div>
        </div>
      </div>
    )
  }
}
