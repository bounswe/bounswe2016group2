const root = document.getElementById('root')
const token = reactCookie.load('token')
const userEmail = reactCookie.load('email')

class Layout extends React.Component {
  render() {
    return (
      <div>
        <div className='ui secondary pointing menu' style={{paddingTop: 10}}>
          <a href="/" className='item active'>
            Home
          </a>
          <a href="addFood/" className='item'>
            Add Food
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
