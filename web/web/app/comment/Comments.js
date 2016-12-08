import Comment from 'comment/Comment.js'
import PostComment from 'comment/PostComment.js'

export default class Comments extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      comments: [{}]
    };

    this.fetch = this.fetch.bind(this);
  }

  componentDidMount() {
    this.fetch();
  }

  // TODO: implement this function after api call is added
  fetch(){
      // TODO: update this.state.comments variable

      let mockComment = {
        id: 3,
        username: 'Ford Prefect',
        time: Date.now(),
        text: 'So long, and thanks for all the fish'
      };
      let mockComment2 = {
        id: 6,
        username: 'Marvin',
        time: Date.now(),
        text: "Don't panic"
      };
      let comments = [mockComment2, mockComment];
      this.setState({comments: comments});
  }

  render() {
    return (
      <div className="ui comments">
        {this.state.comments.map(function(comment, index){
          return(
            <Comment key={index} comment={comment}/>
        )})}
        <PostComment/>
      </div>
    );
  }
}
