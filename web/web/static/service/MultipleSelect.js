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
// setOptions: function to set options of this compoenent
// name: form name
// placeholder: placeholder
var MultipleSelect = function (_React$Component) {
  _inherits(MultipleSelect, _React$Component);

  function MultipleSelect(props) {
    _classCallCheck(this, MultipleSelect);

    var _this = _possibleConstructorReturn(this, (MultipleSelect.__proto__ || Object.getPrototypeOf(MultipleSelect)).call(this, props));

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
    return _this;
  }

  _createClass(MultipleSelect, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var self = this;
      $('.ui.dropdown[name="' + this.state.name + '"]').dropdown({
        maxSelections: 20,
        onChange: function onChange(value) {
          var data = [];
          if (value != '') {
            data = value.split(',');
          }
          self.onChange(data);
        }
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'ui fluid multiple search normal selection dropdown', name: this.state.name },
        React.createElement('input', { type: 'hidden', name: this.state.name }),
        React.createElement('i', { className: 'dropdown icon' }),
        React.createElement(
          'div',
          { className: 'default text' },
          this.state.placeholder
        ),
        React.createElement(
          'div',
          { className: 'menu' },
          this.state.options.map(function (option) {
            return React.createElement(
              'div',
              { key: option.id, className: 'item', 'data-value': option.id },
              option.name
            );
          })
        )
      );
    }
  }]);

  return MultipleSelect;
}(React.Component);

exports.default = MultipleSelect;