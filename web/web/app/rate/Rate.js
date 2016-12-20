export default class Rate extends React.Component {
  constructor(props) {
    super(props);
    this.onChange = props.onChange;
    this.state = {
      maxRating: props.maxRating || 5,
      label: props.label || '',
      initialRating: props.initialRating || 0,
      name: props.name || ''
    };
  }

  componentDidMount() {
    $('#rating'+this.state.name+' .ui.rating').rating(
      {
        maxRating: this.state.maxRating,
        initialRating: this.state.initialRating,
        onRate: (value) => {
          this.onChange(value);
        }
      }
    );
  }

  render() {
    return (
      <div id={'rating'+this.state.name}>
        <label> {this.state.label} </label>
        <div className="ui star rating"></div>
      </div>
    );
  }
}
