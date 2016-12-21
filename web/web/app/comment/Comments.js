import Comment from 'comment/Comment.js'
import PostComment from 'comment/PostComment.js'

export default class Comments extends React.Component {
  constructor(props) {
    super(props);
    this.id = props.id;
    this.state = {
      comments: []
    };
    if(props.getComments){
      this.getComments = props.getComments.bind(this);
      this.getComments(this.id);
    }

    // api functions
    this.comment = props.comment.bind(this);
    this.commentFunction = this.commentFunction.bind(this);
  }

  commentFunction(e){
    this.comment(e);

    // a quick solution for updating comments
    window.location.href = window.location.pathname;
  }

  render() {
    return (
      <div className="ui comments">
        {this.state.comments.map(function(comment, index){
          return(
            <Comment key={index} comment={comment}/>
        )}, this)}
        <PostComment addComment={this.commentFunction}/>
      </div>
    );
  }
}
