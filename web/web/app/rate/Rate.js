export default class Rate extends React.Component {
  constructor(props) {
    super(props);
    this.onChange = props.onChange;
    this.state = {
      maxRating: props.maxRating || 5,
      label: props.label || '',
      initialRating: props.initialRating || 0
    };
  }

  componentDidMount() {
    $('#foodRating .ui.rating').rating(
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
      <div id="foodRating">
        <label> {this.state.label} </label>
        <div className="ui star rating"></div>
      </div>
    );
  }
}
