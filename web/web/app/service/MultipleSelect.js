// onChange: function to be called on option selected or removed
// options: [{id,name}]
// setOptions: function to set options of this compoenent
// name: form name
// placeholder: placeholder
export default class MultipleSelect extends React.Component {

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
  }

  componentDidMount() {
    const self = this
    $(`.ui.dropdown[name="${this.state.name}"]`).dropdown({
      maxSelections: 20,
      onChange: (value) => {
        let data = []
        if (value != '') {
          data = value.split(',')
        }
        self.onChange(data)
      }
    })
  }

  render() {
    return (
      <div className="ui fluid multiple search normal selection dropdown" name={this.state.name}>
        <input type="hidden" name={this.state.name}/>
        <i className="dropdown icon"></i>
        <div className="default text">{this.state.placeholder}</div>
        <div className="menu">
          { this.state.options.map((option) => {
            return <div key={option.id} className="item" data-value={option.id}>{option.name}</div>
          })}
        </div>
      </div>
    )
  }

}
