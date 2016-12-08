// onChange: function to be called on option selected or removed
// options: [{id,name}]
// name: form name
// placeholder: placeholder
export default class DatePicker extends React.Component {

  constructor(props) {
    super(props)
    this.data = props.data
    this.state = {
      name: props.name,
      placeholder: props.placeholder,
      options: props.options || []
    }
    if (props.setOptions) {
      this.setOptions = props.setOptions.bind(this)
      this.setOptions()
    }
    this.onChange = props.onChange
    this.default = props.default
  }

  componentDidMount() {
    const self = this
    $(`.ui.calendar[name="${this.state.name}"]`).calendar({
      type: 'date',
      date: this.default,
      onChange: self.onChange
    })
    $(`.ui.calendar[name="${this.state.name}"]`).calendar('set date', this.default)
  }

  render() {
    return (
      <div className="ui calendar" name={this.state.name}>
        <div className="ui input left icon">
          <i className="calendar icon"></i>
          <input type="text" placeholder={this.state.placeholder}/>
        </div>
      </div>
    )
  }

}
