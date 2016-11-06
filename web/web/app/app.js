const root = document.getElementById('root')
let user = {}

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
          <NavbarUser user={user}/>
        </div>
        <div className='ui container'>
          <div className='ui center aligned segment'>
          </div>
        </div>
      </div>
    )
  }
}

ReactDOM.render(<Layout/>, root)
