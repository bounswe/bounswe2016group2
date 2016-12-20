'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Rate = function (_React$Component) {
  _inherits(Rate, _React$Component);

  function Rate(props) {
    _classCallCheck(this, Rate);

    var _this = _possibleConstructorReturn(this, (Rate.__proto__ || Object.getPrototypeOf(Rate)).call(this, props));

    _this.onChange = props.onChange;
    _this.state = {
      maxRating: props.maxRating || 5,
      label: props.label || '',
      initialRating: props.initialRating || 0,
      name: props.name || ''
    };
    return _this;
  }

  _createClass(Rate, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _this2 = this;

      $('#rating' + this.state.name + ' .ui.rating').rating({
        maxRating: this.state.maxRating,
        initialRating: this.state.initialRating,
        onRate: function onRate(value) {
          _this2.onChange(value);
        }
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { id: 'rating' + this.state.name },
        React.createElement(
          'label',
          null,
          ' ',
          this.state.label,
          ' '
        ),
        React.createElement('div', { className: 'ui star rating' })
      );
    }
  }]);

  return Rate;
}(React.Component);

exports.default = Rate;