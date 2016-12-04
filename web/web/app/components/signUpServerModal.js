class SignUpServerModal extends React.Component
{
  constructor (props)
  {
    super(props)
    this.state =
    {
      data: null,
      errors: null
    }
    this.server_nameChanged = this.server_nameChanged.bind(this)
    this.server_phoneNumberChanged = this.server_phoneNumberChanged.bind(this)
    this.emailChanged = this.emailChanged.bind(this)
    this.passwordChanged = this.passwordChanged.bind(this)
    this.submit = this.submit.bind(this)
  }

  server_nameChanged(e)
  {
    this.setState({server_name: e.target.value})
  }

  server_phoneNumberChanged(e)
  {
    this.setState({server_phoneNumber: e.target.value})
  }

  emailChanged(e)
  {
    this.setState({email: e.target.value})
  }

  passwordChanged(e)
  {
    this.setState({password: e.target.value})
  }

  submit(e)
  {
    e.preventDefault()
    this.setState({errors: null})
    const postData =
    {
      email: this.state.email,
      password: this.state.password,
      server_name: this.state.server_name,
      server_phoneNumber: this.state.server_phoneNumber
    }

    Api.signup(postData)
      .then((data) =>
      {
        Api.signin(postData)
        .then((data) =>
        {
            reactCookie.save('token', data.token)
            reactCookie.save('email', postData.email)
            window.location.href = '/'
        })
      }).catch((err) =>
      {
        this.setState({errors: err.data})
      })
  }

  render()
  {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div id='signUpServerModal' className="ui small modal">
        <i className="close icon"></i>
        <div className="header">
          Sign Up
        </div>
        <div className="content">
          <form className={formClassName}>
            <div className="field">
              <label>Server name</label>
              <input type="text" name="serverName" placeholder="Server name" value={this.state.server_name} onChange={this.server_nameChanged}/>
            </div>
            <div className="field">
              <label>Phone Number</label>
              <input type="text" name="phoneNumber" placeholder="Phone Number" value={this.state.server_phoneNumber} onChange={this.server_phoneNumberChanged}/>
            </div>
            <div className="field">
              <label>E-mail</label>
              <input type="text" name="email" placeholder="E-mail" value={this.state.email} onChange={this.emailChanged}/>
              { this.state.errors && this.state.errors.email &&
                <div className="ui error message">
                  <p>{this.state.errors.email[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label>Password</label>
              <input type="password" name="password" placeholder="Password" value={this.state.password} onChange={this.passwordChanged}/>
              { this.state.errors && this.state.errors.password &&
                <div className="ui error message">
                  <p>{this.state.errors.password[0]}</p>
                </div>
              }
            </div>
            <div className="field">
              <label>Repeat password</label>
              <input type="password" name="repeatPassword" placeholder="Password"/>
            </div>
            <button className="ui button" type="submit" style={{width:'100%'}} onClick={this.submit}>
              Submit
            </button>
          </form>
        </div>
      </div>
    )
  }
}
