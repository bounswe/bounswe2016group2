export default class IntervalSelection extends React.Component {
  constructor(props) {
    super(props);
    this.variable = props.variable;
    this.state = {};

    this.min = 0;
    if(props.unit){
      this.max = 9999;
    }
    else{
      this.max = 1.0;
    }
  }
  minValueChanged(event) {
    let value = event.target.value;
    if(value > this.max)
      value = this.max;
    else if(value < this.min)
      value = this.min;

    this.setState({minValue: value})
    this.variable.min = Number(value);
  }
  maxValueChanged(event) {
    let value = event.target.value;
    if(value > this.max)
      value = this.max;
    else if(value < this.min)
      value = this.min;

    this.setState({maxValue: value})
    this.variable.max = Number(value);
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
              <input type="number" placeholder="min" value={this.state.minValue} onChange={this.minValueChanged.bind(this)} style={{width:75}}/>
              {this.props.unit &&
                <div className="ui basic label">
                  {this.props.unit}
                </div>
              }
            </div>
          </div>
          <div className="three wide field">
            <div className="ui left labeled input" style={{marginLeft:20}}>
              <input type="number" placeholder="max" value={this.state.maxValue} onChange={this.maxValueChanged.bind(this)} style={{width:75}}/>
              {this.props.unit &&
                <div className="ui basic label">
                  {this.props.unit}
                </div>
              }
            </div>
          </div>
        </div>
      </div>
    );
  }
}
