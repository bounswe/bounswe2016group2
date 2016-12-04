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

  openDropdown()
  {
    $('.ui.dropdown').dropdown('show')
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
          <a className='item' href='/profile'>
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
          {/**<SignInModal/>
          <a className='item' onClick={this.openSigninModal}>
            Sign In
          </a>
          <SignUpModal/>
          <a className='item' onClick={this.openSignupModal}>
            Sign Up
          </a>*/}
          <a className='item' onClick={this.openSigninModal}>
            Sign In
          </a>
          <a className='item'>
          <div className='ui dropdown'>
            <div className='text' onClick={this.openDropdown}>Sign Up</div>
            <i className="dropdown icon"></i>
            <div className='menu'>
              <SignInModal/>
              <a className='item' onClick={this.openSignupModal}>
                User
              </a>
              <SignUpModal/>
              <a className='item' onClick={this.openSignupServerModal}>
                Server
              </a>
            </div>
          </div>
        </a>
        </div>
      )
    }
  }
}
