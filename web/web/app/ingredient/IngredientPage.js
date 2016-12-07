export default class IngredientPage extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      id: props.id,
      servingSize: 1,
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
    Api.getIngredient(id)
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
      if(propValue){
        result.push(
          <div key={prop}>
            <label style={{"fontWeight": "bold"}}> {prop} </label>
            <label> {propValue} </label>
          </div>
        );
      }
    }
    this.setState({ingrProps: result});
  }

  // TODO: currently nor working
  ateThis(e) {
    e.preventDefault();
    var query = {
      value: this.state.servingSize,
      unit: this.state.data.measureUnit
    }
    Api.ingredientAte(this.state.id, query)
      .then((data) => {
        console.log(data);
        $('#ateFoodSuccModal').modal('show')
      }).catch((err) => {
        console.log(err);
      })
  }

  openAteFoodModal() {
      $('#ateFoodModal').modal('show')
  }

  openSuccessModal() {
    $('#ateFoodSuccModal').modal('show')
  }

  servingSizeChanged(e) {this.setState({servingSize: e.target.value}) }


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
          {/* i ate this functionality here */}
          { token &&
            <div>
              <button className="ui button" type="button" onClick={this.openAteFoodModal}>
                I ate this!
              </button>
              <div id='ateFoodSuccModal' className="ui small modal">
                <div className="ui message success">
                  <i className="close icon"></i>
                  <div className="header">
                    Success!
                  </div>
                  <p>The food has been saved to your nutrition history</p>
                </div>
              </div>
              <div id='ateFoodModal' className="ui small modal">
                <i className="close icon"></i>
                <div className="header">
                  You ate this?
                </div>
                <div className="content">
                  <form className="ui form">
                    <div className="field">
                      <label>Serving size</label>
                      <input type="number" min="0" max="100" name="servingSize" value={this.state.servingSize} onChange={this.servingSizeChanged}/>
                    </div>
                    <button className="ui button" type="submit" style={{width:'100%'}} onClick={this.ateThis}>
                      Submit
                    </button>
                  </form>
                </div>
              </div>
            </div>
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
