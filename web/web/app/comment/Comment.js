import PostComment from 'comment/PostComment.js'

export default class Comment extends React.Component {
  constructor(props) {
    super(props);

    this.userId = props.comment.user;
    this.comment = props.comment.comment;
    this.state = {};

    this.fetchUserName = this.fetchUserName.bind(this);
    this.fetchUserName();
  }

  fetchUserName(){
    Api.getUser(this.userId).then((data) => {
      let screenName = "";
      if(data.first_name || data.last_name){
        screenName = data.first_name + " " + data.last_name;
      }
      else{
        screenName = data.username;
      }
      this.setState({user: screenName})
    }).catch((error) => {
      this.setState({errors: error.data})
    })
  }

  render() {
    return (
      <div className="comment">
        <div className="content">
          <div className="author">{this.state.user}</div>
          <div className="text">
            {this.comment}
          </div>
        </div>
      </div>
    );
  }
}
