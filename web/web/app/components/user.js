class NavbarUser extends React.Component {

  constructor(props) {
    super(props)
    this.state = {user: props.user}
    this.signin = this.signin.bind(this);
    this.signout = this.signout.bind(this);
  }

  signin() {
    this.setState({user: user})
  }

  signout() {
    this.setState({user: false})
  }

  render () {
    return (
      <div className='right menu'>
        <a className='item' onClick={this.state.user ? this.signout : this.signin}>
          {this.state.user ? 'Sign Out': 'Sign In'}
        </a>
      </div>
    )
  }
}
