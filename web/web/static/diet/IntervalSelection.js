"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var IntervalSelection = function (_React$Component) {
  _inherits(IntervalSelection, _React$Component);

  function IntervalSelection(props) {
    _classCallCheck(this, IntervalSelection);

    var _this = _possibleConstructorReturn(this, (IntervalSelection.__proto__ || Object.getPrototypeOf(IntervalSelection)).call(this, props));

    _this.variable = props.variable;
    _this.state = {};

    _this.min = 0;
    if (props.unit) {
      _this.max = 9999;
    } else {
      _this.max = 1.0;
    }
    return _this;
  }

  _createClass(IntervalSelection, [{
    key: "minValueChanged",
    value: function minValueChanged(event) {
      var value = event.target.value;
      if (value > this.max) value = this.max;else if (value < this.min) value = this.min;

      this.setState({ minValue: value });
      this.variable.min = Number(value);
    }
  }, {
    key: "maxValueChanged",
    value: function maxValueChanged(event) {
      var value = event.target.value;
      if (value > this.max) value = this.max;else if (value < this.min) value = this.min;

      this.setState({ maxValue: value });
      this.variable.max = Number(value);
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "ui form" },
        React.createElement(
          "div",
          { className: "inline fields" },
          React.createElement(
            "div",
            { className: "three wide field" },
            React.createElement(
              "label",
              null,
              this.props.label
            )
          ),
          React.createElement(
            "div",
            { className: "three wide field" },
            React.createElement(
              "div",
              { className: "ui right labeled input", style: { marginLeft: 20 } },
              React.createElement("input", { type: "number", placeholder: "min", value: this.state.minValue, onChange: this.minValueChanged.bind(this), style: { width: 75 } }),
              this.props.unit && React.createElement(
                "div",
                { className: "ui basic label" },
                this.props.unit
              )
            )
          ),
          React.createElement(
            "div",
            { className: "three wide field" },
            React.createElement(
              "div",
              { className: "ui left labeled input", style: { marginLeft: 20 } },
              React.createElement("input", { type: "number", placeholder: "max", value: this.state.maxValue, onChange: this.maxValueChanged.bind(this), style: { width: 75 } }),
              this.props.unit && React.createElement(
                "div",
                { className: "ui basic label" },
                this.props.unit
              )
            )
          )
        )
      );
    }
  }]);

  return IntervalSelection;
}(React.Component);

exports.default = IntervalSelection;