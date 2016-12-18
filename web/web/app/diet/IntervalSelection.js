export default class IntervalSelection extends React.Component {
  constructor(props) {
    super(props);
    this.variable = props.variable;
    this.state = {};
    this.min = 0;
  }
  minValueChanged(event) {
    let value = event.target.value;
    if(value < this.min)
      value = this.min;

    this.setState({minValue: value})
    let ratio = "0." + value;
    console.log(ratio);
    this.variable.min = Number(ratio);
  }
  maxValueChanged(event) {
    let value = event.target.value;
    if(value < this.min)
      value = this.min;
    this.setState({maxValue: value})
    let ratio = "0." + value;
    console.log(ratio);
    this.variable.max = Number(ratio);
  }

  render() {
    return (
      <div className="ui form">
        <div className="inline fields">
          <div className="three wide field">
            <label>{this.props.label}</label>
          </div>
          <div className="three wide field">
            <div className="ui right labeled input" style={{marginLeft:20}}>
              <div className="ui basic label">
                0.
              </div>
              <input type="number" placeholder="min" value={this.state.minValue} onChange={this.minValueChanged.bind(this)} style={{width:75}}/>
            </div>
          </div>
          <div className="three wide field">
            <div className="ui left labeled input" style={{marginLeft:20}}>
              <div className="ui basic label">
                0.
              </div>
              <input type="number" placeholder="max" value={this.state.maxValue} onChange={this.maxValueChanged.bind(this)} style={{width:75}}/>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
