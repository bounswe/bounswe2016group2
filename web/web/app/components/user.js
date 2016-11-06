class SignInModal extends React.Component {

  constructor (props) {
    super(props)
    this.data = {}
    this.emailChanged = this.emailChanged.bind(this)
    this.passwordChanged = this.passwordChanged.bind(this)
    this.submit = this.submit.bind(this)
  }

  emailChanged(e) {this.setState({email: e.target.value}) }
  passwordChanged(e) {this.setState({password: e.target.value}) }

  submit(e) {
    e.preventDefault()
    Api.signin(this.data)
      .then((data) => {

      }).catch((err) => {

      })
  }

  render() {
    return (
      <div id='signInModal' className="ui small modal">
        <i className="close icon"></i>
        <div className="header">
          Sign In
        </div>
        <div className="content">
          <form className='ui form'>
            <div className="field">
              <label>E-mail</label>
              <input type="text" name="email" placeholder="E-mail" value={this.data.email} onChange={this.changed}/>
            </div>
            <div className="field">
              <label>Password</label>
              <input type="password" name="password" placeholder="Password" value={this.data.password} onChange={this.changed}/>
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
    this.data = {}
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
    Api.signup(this.data)
      .then((data) => {

      }).catch((err) => {

      })
  }

  render() {
    return (
      <div id='signUpModal' className="ui small modal">
        <i className="close icon"></i>
        <div className="header">
          Sign Up
        </div>
        <div className="content">
          <form className='ui form'>
            <div className="field">
              <label>First name</label>
              <input type="text" name="firstName" placeholder="First name" value={this.data.first_name} onChange={this.first_nameChanged}/>
            </div>
            <div className="field">
              <label>Last name</label>
              <input type="text" name="lastName" placeholder="Last name" value={this.data.last_name} onChange={this.last_nameChanged}/>
            </div>
            <div className="field">
              <label>E-mail</label>
              <input type="text" name="email" placeholder="E-mail" value={this.data.email} onChange={this.emailChanged}/>
            </div>
            <div className="field">
              <label>Password</label>
              <input type="password" name="password" placeholder="Password" value={this.data.password} onChange={this.passwordChanged}/>
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
    this.state = {user: props.user}
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
  }

  render () {
    if (this.state.user) {
      return(
        <div className='right menu'>
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
