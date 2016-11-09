class AddFood extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      data: null,
      errors: null
    }
    this.nameChanged = this.nameChanged.bind(this)
    this.slugChanged = this.slugChanged.bind(this)
    this.submit = this.submit.bind(this)
  }

  nameChanged(e) {this.setState({name: e.target.value})}
  slugChanged(e) {this.setState({slug: e.target.value})}

  submit(e) {
    e.preventDefault()
    this.setState({errors: null})
    const postData = {
      name: this.state.name,
      slug: this.state.slug
    }
    Api.addFood(postData)
      .then((data) => {

      }).catch((err) => {
        this.setState({errors: err.data})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div id='addFoodForm'>
        <div className="header">
          Add New Food
        </div>
        <div className="content">
          <form className={formClassName}>
            <div className="field">
              <label> Name </label>
              <input type="text" name="name" placeholder="name" value={this.state.name} onChange={this.nameChanged}/>
              { this.state.errors && this.state.errors.name &&
                <div className="ui error message">
                  <p>{this.state.errors.name[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label> Slug </label>
              <input type="text" name="slug" placeholder="slug" value={this.state.slug} onChange={this.slugChanged}/>
              { this.state.errors && this.state.errors.slug &&
                <div className="ui error message">
                  <p>{this.state.errors.slug[0]}</p>
                </div>
              }
            </div>
            <button className="ui button" type="submit" style={{width:'100%'}} onClick={this.submit}>
              Add Food
            </button>
          </form>
        </div>
      </div>
    )
  }
}

