const root = document.getElementById('root')
const token = reactCookie.load('token')
const userEmail = reactCookie.load('email')

console.log(token, userEmail);
class Layout extends React.Component {
  render() {
    return (
      <div>
        <div className='ui secondary pointing menu' style={{paddingTop: 10}}>
          <a className='item active'>
            Home
          </a>
          <a className='item'>
            Other stuff
          </a>
          <NavbarUser userEmail={userEmail}/>
        </div>
        <div className='ui container'>
          <FoodSearch/>
        </div>
      </div>
    )
  }
}

ReactDOM.render(<Layout/>, root)
