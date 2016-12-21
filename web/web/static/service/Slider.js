'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

// onChange: function to be called on option selected or removed
var Slider = function (_React$Component) {
  _inherits(Slider, _React$Component);

  function Slider(props) {
    _classCallCheck(this, Slider);

    var _this = _possibleConstructorReturn(this, (Slider.__proto__ || Object.getPrototypeOf(Slider)).call(this, props));

    _this.data = props.data;
    _this.state = {
      id: props.id,
      min: props.min || 0,
      max: props.max || 999
    };
    _this.onChange = props.onChange;
    return _this;
  }

  _createClass(Slider, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _this2 = this;

      var self = this;
      var slider = document.getElementById(this.state.id);

      noUiSlider.create(slider, {
        connect: true,
        tooltips: true,
        step: 1,
        start: [this.state.min, this.state.max],
        range: {
          min: this.state.min,
          max: this.state.max
        }
      });

      slider.noUiSlider.on('set', function () {
        var data = slider.noUiSlider.get();
        _this2.onChange(data[0], data[1]);
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement('div', { style: { margin: '10px 30px' }, id: this.state.id });
    }
  }]);

  return Slider;
}(React.Component);

exports.default = Slider;