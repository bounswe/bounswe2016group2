import NavbarUser from 'user/NavbarUser.js'

export default class Layout extends React.Component {
  render() {
    return (
      <div>
        <div className='ui secondary pointing menu' style={{paddingTop: 10}}>
          <a href="/" className='item'>
            Home
          </a>
          <a href="/addFood" className='item'>
            Add Food
          </a>
          <a href="/addRestaurant" className='item'>
            Become a server
          </a>
          <NavbarUser userEmail={userEmail}/>
        </div>
        <div className='ui container'>
          {this.props.children}
        </div>
      </div>
    )
  }
}
