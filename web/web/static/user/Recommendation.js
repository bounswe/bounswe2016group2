"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _FoodRow = require("food/FoodRow.js");

var _FoodRow2 = _interopRequireDefault(_FoodRow);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Recommendation = function (_React$Component) {
  _inherits(Recommendation, _React$Component);

  function Recommendation(props) {
    _classCallCheck(this, Recommendation);

    var _this = _possibleConstructorReturn(this, (Recommendation.__proto__ || Object.getPrototypeOf(Recommendation)).call(this, props));

    _this.state = {
      recommendation: React.createElement(
        "div",
        null,
        " No Recommendation "
      )
    };
    _this.fetch = _this.fetch.bind(_this);
    _this.fetch();
    return _this;
  }

  _createClass(Recommendation, [{
    key: "fetch",
    value: function fetch() {
      var _this2 = this;

      var ateFoods = [];
      Api.consumptionHistory().then(function (data) {
        ateFoods = data.total.ateFoods;
        // get last 5 consumed food
        if (ateFoods > 5) {
          ateFoods = ateFoods.slice(0, 5);
        }
        var tagList = [];
        // get semantic tags of these foods
        for (var food in ateFoods) {
          for (var tag in ateFoods[food].food.tags) {
            tagList.push(ateFoods[food].food.tags[tag].name);
          }
        }
        var filter = {};
        filter.tags = tagList;
        Api.advancedSearch(filter).then(function (data) {
          if (data.length > 5) {
            data = data.slice(0, 5);
          }
          var foodList = data.map(function (food) {
            return React.createElement(_FoodRow2.default, { key: food.id, data: food });
          });
          if (data.length > 0) {
            _this2.setState({ recommendation: foodList });
          }
        }).catch(function (error) {
          _this2.setState({ errors: error });
        });
      }).catch(function (error) {
        _this2.setState({ errors: error });
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
            "h2",
            { className: "ui header", style: { textAlign: 'center' } },
            "Recommended Foods"
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          this.state.recommendation
        )
      );
    }
  }]);

  return Recommendation;
}(React.Component);

exports.default = Recommendation;