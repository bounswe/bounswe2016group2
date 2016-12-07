"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _FoodRow = require("../food/FoodRow.js");

var _FoodRow2 = _interopRequireDefault(_FoodRow);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var FoodPage = function (_React$Component) {
  _inherits(FoodPage, _React$Component);

  function FoodPage(props) {
    _classCallCheck(this, FoodPage);

    var _this = _possibleConstructorReturn(this, (FoodPage.__proto__ || Object.getPrototypeOf(FoodPage)).call(this, props));

    _this.state = {
      id: props.id,
      restaurant: {
        foods: []
      }
    };
    _this.fetch = _this.fetch.bind(_this);
    return _this;
  }

  _createClass(FoodPage, [{
    key: "componentWillMount",
    value: function componentWillMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: "fetch",
    value: function fetch(id) {
      var _this2 = this;

      Api.getRestaurant(id).then(function (data) {
        console.log(data);
        _this2.setState({ restaurant: data });
      }).catch(function (err) {
        _this2.setState({ errors: err });
      });
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "ui segments" },
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h1",
            { className: "ui header", style: { textAlign: 'center' } },
            " ",
            this.state.restaurant.name || "Restaurant not found",
            " "
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment", style: { padding: 0, overflow: 'hidden', maxHeight: 400, textAlign: 'center', width: '100%' } },
          React.createElement("img", { src: this.state.restaurant.photo, className: "img-responsive" })
        ),
        this.state.restaurant.description && React.createElement(
          "div",
          { className: "ui segment", style: { textAlign: 'center' } },
          React.createElement(
            "h2",
            { className: "ui header" },
            "Description"
          ),
          React.createElement(
            "p",
            null,
            this.state.restaurant.description
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h1",
            { className: "ui header", style: { textAlign: 'center' } },
            "Foods"
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          this.state.restaurant.foods.map(function (food) {
            return React.createElement(_FoodRow2.default, { key: food.id, data: food });
          })
        )
      );
    }
  }]);

  return FoodPage;
}(React.Component);

exports.default = FoodPage;