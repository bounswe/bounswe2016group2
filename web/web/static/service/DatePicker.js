'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

// onChange: function to be called on option selected or removed
// options: [{id,name}]
// name: form name
// placeholder: placeholder
var DatePicker = function (_React$Component) {
  _inherits(DatePicker, _React$Component);

  function DatePicker(props) {
    _classCallCheck(this, DatePicker);

    var _this = _possibleConstructorReturn(this, (DatePicker.__proto__ || Object.getPrototypeOf(DatePicker)).call(this, props));

    _this.data = props.data;
    _this.state = {
      name: props.name,
      placeholder: props.placeholder,
      options: props.options || []
    };
    if (props.setOptions) {
      _this.setOptions = props.setOptions.bind(_this);
      _this.setOptions();
    }
    _this.onChange = props.onChange;
    _this.default = props.default;
    return _this;
  }

  _createClass(DatePicker, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var self = this;
      $('.ui.calendar[name="' + this.state.name + '"]').calendar({
        type: 'date',
        date: this.default,
        onChange: self.onChange
      });
      $('.ui.calendar[name="' + this.state.name + '"]').calendar('set date', this.default);
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'ui calendar', name: this.state.name },
        React.createElement(
          'div',
          { className: 'ui input left icon' },
          React.createElement('i', { className: 'calendar icon' }),
          React.createElement('input', { type: 'text', placeholder: this.state.placeholder })
        )
      );
    }
  }]);

  return DatePicker;
}(React.Component);

exports.default = DatePicker;