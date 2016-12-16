"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var MyFoods = function (_React$Component) {
  _inherits(MyFoods, _React$Component);

  function MyFoods(props) {
    _classCallCheck(this, MyFoods);

    var _this = _possibleConstructorReturn(this, (MyFoods.__proto__ || Object.getPrototypeOf(MyFoods)).call(this, props));

    _this.state = {
      foods: props.foods
    };
    return _this;
  }

  _createClass(MyFoods, [{
    key: "componentDidMount",
    value: function componentDidMount() {
      var self = this;
      $('#foodSelect.ui.dropdown').dropdown({
        onChange: function onChange(index) {
          console.log(self.state.foods[index]);
        }
      });
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "ui segment" },
        React.createElement(
          "div",
          { id: "foodSelect", className: "ui search selection dropdown" },
          React.createElement("i", { className: "dropdown icon" }),
          React.createElement(
            "div",
            { className: "default text" },
            "Food"
          ),
          React.createElement(
            "div",
            { className: "menu" },
            this.state.foods.map(function (food, index) {
              return React.createElement(
                "div",
                { className: "item", "data-value": index, key: index },
                food.name
              );
            })
          )
        )
      );
    }
  }]);

  return MyFoods;
}(React.Component);

exports.default = MyFoods;