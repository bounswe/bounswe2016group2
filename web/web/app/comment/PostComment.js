export default class PostComment extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};

    // TODO: pass needed api call,
    // it can be different for replying and commenting

    // this.apiCall = props.apiCall;

    this.commentChanged = this.commentChanged.bind(this);
    this.submit = this.submit.bind(this);
  }

  submit(){
    let postData = {
      comment: this.state.comment
    }

    // TODO: add api call
    /*
    this.apiCall(postData).then((data) => {
        console.log(data);
      }
    ).catch((error) => {
        console.log(error.data);
      }
    );
    */
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
