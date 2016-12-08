import PostComment from 'comment/PostComment.js'

export default class Comment extends React.Component {
  constructor(props) {
    super(props);

    this.id = props.comment.id;
    this.username = props.comment.username;
    this.commentTime = props.comment.time;
    this.commentText = props.comment.text;

    this.state = {
      showReplySection: false
    };

    this.reply = this.reply.bind(this);
  }

  reply(){
    this.setState({showReplySection: true})
  }

  render() {
    return (
      <div className="comment">
        <div className="content">
          <a className="author">{this.username}</a>
          <div className="metadata">
            <span className="date">{this.commentTime}</span>
          </div>
          <div className="text">
            {this.commentText}
          </div>
          <div className="actions">
            <a className="reply" onClick={this.reply}>Reply</a>
          </div>
        </div>
        {this.state.showReplySection &&
          // TODO: Only restaurants can reply to comments
          <PostComment/>
        }
      </div>
    );
  }
}
