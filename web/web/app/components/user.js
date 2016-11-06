class SignInModal extends React.Component {

  constructor (props) {
    super(props)
    this.state = {}
    this.emailChanged = this.emailChanged.bind(this)
    this.passwordChanged = this.passwordChanged.bind(this)
    this.submit = this.submit.bind(this)
  }

  emailChanged(e) {this.setState({email: e.target.value}) }
  passwordChanged(e) {this.setState({password: e.target.value}) }

  submit(e) {
    e.preventDefault()
    this.setState({error: false})
    const postData = {
      email: this.state.email,
      password: this.state.password
    }
    Api.signin(postData)
      .then((data) => {
        reactCookie.save('token', data.token)
        reactCookie.save('email', postData.email)
        window.location.href = '/'
      }).catch((err) => {
        this.setState({error: true})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.error) formClassName += ' error'
    return (
      <div id='signInModal' className="ui small modal">
        <i className="close icon"></i>
        <div className="header">
          Sign In
        </div>
        <div className="content">
          <form className={formClassName}>
            <div className="field">
              <label>E-mail</label>
              <input type="text" name="email" placeholder="E-mail" value={this.state.email} onChange={this.emailChanged}/>
            </div>
            <div className="field">
              <label>Password</label>
              <input type="password" name="password" placeholder="Password" value={this.state.password} onChange={this.passwordChanged}/>
            </div>
            <div className="ui error message">
              <p>Invalid email or password</p>
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

class SignUpModal extends React.Component {

  constructor (props) {
    super(props)
    this.state = {
      data: null,
      errors: null
    }
    this.first_nameChanged = this.first_nameChanged.bind(this)
    this.last_nameChanged = this.last_nameChanged.bind(this)
    this.emailChanged = this.emailChanged.bind(this)
    this.passwordChanged = this.passwordChanged.bind(this)
    this.submit = this.submit.bind(this)
  }

  first_nameChanged(e) {this.setState({first_name: e.target.value}) }
  last_nameChanged(e) {this.setState({last_name: e.target.value}) }
  emailChanged(e) {this.setState({email: e.target.value}) }
  passwordChanged(e) {this.setState({password: e.target.value}) }

  submit(e) {
    e.preventDefault()
    this.setState({errors: null})
    const postData = {
      email: this.state.email,
      password: this.state.password,
      first_name: this.state.first_name,
      last_name: this.state.last_name
    }
    Api.signup(postData)
      .then((data) => {
        Api.signin(postData)
        .then((data) => {
            reactCookie.save('token', data.token)
            reactCookie.save('email', postData.email)
            window.location.href = '/'
        })
      }).catch((err) => {
        this.setState({errors: err.data})
      })
  }

  render() {
    let formClassName = 'ui form'
    if (this.state.errors) formClassName += ' error'
    return (
      <div id='signUpModal' className="ui small modal">
        <i className="close icon"></i>
        <div className="header">
          Sign Up
        </div>
        <div className="content">
          <form className={formClassName}>
            <div className="field">
              <label>First name</label>
              <input type="text" name="firstName" placeholder="First name" value={this.state.first_name} onChange={this.first_nameChanged}/>
            </div>
            <div className="field">
              <label>Last name</label>
              <input type="text" name="lastName" placeholder="Last name" value={this.state.last_name} onChange={this.last_nameChanged}/>
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

class NavbarUser extends React.Component {

  constructor(props) {
    super(props)
    this.state = {userEmail: props.userEmail}
    this.openSigninModal = this.openSigninModal.bind(this);
    this.openSignupModal = this.openSignupModal.bind(this);
    this.signout = this.signout.bind(this);
  }

  openSigninModal() {
    $('#signInModal.ui.modal').modal('show')
  }

  openSignupModal() {
    $('#signUpModal.ui.modal').modal('show')
  }

  signout() {
    reactCookie.remove('email')
    reactCookie.remove('token')
    window.location.href = '/'
  }

  render () {
    if (this.state.userEmail) {
      return(
        <div className='right menu'>
          <a className='item'>
            {userEmail}
          </a>
          <a className='item' onClick={this.signout}>
            Sign Out
          </a>
        </div>
      )
    } else {
      return (
        <div className='right menu'>
          <SignInModal/>
          <a className='item' onClick={this.openSigninModal}>
            Sign In
          </a>
          <SignUpModal/>
          <a className='item' onClick={this.openSignupModal}>
            Sign Up
          </a>
        </div>
      )
    }
  }
}
