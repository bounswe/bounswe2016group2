// onChange: function to be called on option selected or removed
export default class Slider extends React.Component {

  constructor(props) {
    super(props)
    this.data = props.data
    this.state = {
      id: props.id,
      min: props.min || 0,
      max: props.max || 999
    }
    this.onChange = props.onChange
  }

  componentDidMount() {
    const self = this
    let slider = document.getElementById(this.state.id)

    noUiSlider.create(slider, {
      connect: true,
      tooltips: true,
      step: 1,
      start: [this.state.min, this.state.max],
      range: {
        min: this.state.min,
        max: this.state.max
      }
    })

    slider.noUiSlider.on('set', () => {
      const data = slider.noUiSlider.get();
      this.onChange(data[0], data[1])
    })
  }

  render() {
    return (
      <div style={{margin:'10px 30px'}} id={this.state.id}></div>
    )
  }

}
