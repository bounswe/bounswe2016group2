"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ConsumptionHistory = function (_React$Component) {
  _inherits(ConsumptionHistory, _React$Component);

  function ConsumptionHistory(props) {
    _classCallCheck(this, ConsumptionHistory);

    var _this = _possibleConstructorReturn(this, (ConsumptionHistory.__proto__ || Object.getPrototypeOf(ConsumptionHistory)).call(this, props));

    _this.state = {};
    return _this;
  }

  _createClass(ConsumptionHistory, [{
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "ui segment" },
        "consumption history"
      );
    }
  }]);

  return ConsumptionHistory;
}(React.Component);

var UserHomepage = function (_React$Component2) {
  _inherits(UserHomepage, _React$Component2);

  function UserHomepage(props) {
    _classCallCheck(this, UserHomepage);

    return _possibleConstructorReturn(this, (UserHomepage.__proto__ || Object.getPrototypeOf(UserHomepage)).call(this, props));
  }

  _createClass(UserHomepage, [{
    key: "componentDidMount",
    value: function componentDidMount() {
      $('#userHomepage .item').tab();
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { id: "userHomepage", className: "ui-container" },
        React.createElement(
          "div",
          { className: "ui pointing menu" },
          React.createElement(
            "a",
            { className: "item", "data-tab": "consumptionHistory" },
            "Consumption History"
          ),
          React.createElement(
            "a",
            { className: "item", "data-tab": "favFoods" },
            "Favorite Foods"
          ),
          React.createElement(
            "a",
            { className: "item", "data-tab": "favRestaurants" },
            "Favorite Restaurants"
          )
        ),
        React.createElement(
          "div",
          { className: "ui tab", "data-tab": "consumptionHistory" },
          React.createElement(ConsumptionHistory, null)
        )
      );
    }
  }]);

  return UserHomepage;
}(React.Component);

exports.default = UserHomepage;