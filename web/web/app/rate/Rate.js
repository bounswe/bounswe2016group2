export default class Rate extends React.Component {
  constructor(props) {
    super(props);
    this.onChange = props.onChange;
    this.getRating = props.getRating.bind(this);
    this.id = props.id;
    this.state = {
      maxRating: props.maxRating || 5,
      label: props.label || '',
      initialRating: props.initialRating || 0,
      initialUserRating: props.initialUserRating || 0,
      name: props.name || ''
    };
  }

  componentDidMount() {
    $('#rating'+this.state.name+' .ui.rating').rating(
      {
        maxRating: this.state.maxRating,
        initialRating: this.state.initialRating,
        interactive: false
      }
    );
    $('#rating'+this.state.name+'user .ui.rating').rating(
      {
        maxRating: this.state.maxRating,
        initialRating: this.state.initialUserRating,
        onRate: (value) => {
          this.onChange(value);
          this.getRating(this.id)

        }
      }
    );
  }

  render() {
    $('#rating'+this.state.name+' .ui.rating')
      .rating('set rating', this.state.rating);
    ;
    return (
      <div>
        <div id={'rating'+this.state.name}>
          <label> {this.state.label} </label>
          <div className="ui star rating"></div>
        </div>
        <div id={'rating'+this.state.name+'user'}>
          <label> Your Rating </label>
          <div className="ui star rating"></div>
        </div>
      </div>
    );
  }
}
