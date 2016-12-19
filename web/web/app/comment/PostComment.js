export default class PostComment extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.addComment = props.addComment;

    this.commentChanged = this.commentChanged.bind(this);
    this.submit = this.submit.bind(this);
  }

  submit(){
    let postData = {comment: this.state.comment};
    this.addComment(postData);
  }

  commentChanged(event){
    this.setState({comment: event.target.value});
  }

  render() {
    return (
      <div>
        <form className="ui reply form">
          <div className="field">
            <textarea value={this.state.comment} onChange={this.commentChanged}></textarea>
          </div>
          <div className="ui blue labeled submit icon button" onClick={this.submit}>
            <i className="icon edit"></i> Add Comment
          </div>
        </form>
      </div>
    );
  }
}
