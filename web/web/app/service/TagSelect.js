// onChange: function to be called on option selected or removed
// name: form name
// placeholder: placeholder
export default class TagSelect extends React.Component {

  constructor(props) {
    super(props)
    this.data = props.data
    this.state = {
      name: props.name,
      placeholder: props.placeholder,
      tags: [],
      selectedTags: []

    }
    this.onSelectedTagsChange = props.onChange
  }

  queryChanged(e) {
    this.setState({query: e.target.value})
    if (e.target.value == '') {
      this.setState({
        list: []
      })
    }
  }

  search(e) {
    e.preventDefault();
    if (this.state.query == '') return
    let self = this
    Api.searchTag(this.state.query)
    .then((data) => {
      let list = data.map((tag) => {
       return (
         <div className="item" key={tag.name}>
           <div className="content">
             <a className="header" onClick={this.addTag.bind({context:self, tag:tag})}>{tag.name}</a>
             <div className="description">{tag.description}</div>
           </div>
         </div>
       )
     })
     self.setState({
       list: list
     })
    })
  }

  addTag() {
    let self = this.context
    let tag = this.tag
    let selectedTags = self.state.selectedTags
    if (selectedTags.indexOf(tag) == -1) {
      selectedTags.push(tag)
      self.setState({
        selectedTags: selectedTags
      })
      self.onSelectedTagsChange(selectedTags)
    }
  }

  removeTag() {
    let self = this.context
    let tag = this.tag
    let selectedTags = self.state.selectedTags
    selectedTags.forEach((nextTag, i) => {
      if (nextTag.name == tag.name) {
        selectedTags.splice(i, 1)
      }
    })
    self.setState({
      selectedTags: selectedTags
    })
    self.onSelectedTagsChange(selectedTags)
  }

  render() {
    const self = this
    return (
      <div>
        <div className="ui action input">
          <input type="text" placeholder="Search tag" value={this.state.query} onChange={this.queryChanged.bind(this)}/>
          <button className="ui button" onClick={this.search.bind(this)}>Search</button>
        </div>
        <div style={{marginTop: 20,marginBottom: 20}}>
          { this.state.selectedTags.map((tag) => {
            return (
              <a key={tag.name} className="ui label">
                {tag.name}
                <i className="delete icon" onClick={this.removeTag.bind({context:self,tag:tag})}></i>
              </a>
            )
          })}
        </div>
        <div className="ui relaxed divided list">
          {this.state.list}
        </div>
      </div>
    )
  }

}
