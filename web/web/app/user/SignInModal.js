export default class SignInModal extends React.Component {

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
