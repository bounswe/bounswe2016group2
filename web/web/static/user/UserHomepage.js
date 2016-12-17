'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _MyDiets = require('diet/MyDiets.js');

var _MyDiets2 = _interopRequireDefault(_MyDiets);

var _FoodRow = require('food/FoodRow.js');

var _FoodRow2 = _interopRequireDefault(_FoodRow);

var _ConsumptionHistory = require('user/ConsumptionHistory.js');

var _ConsumptionHistory2 = _interopRequireDefault(_ConsumptionHistory);

var _MyFoods = require('user/MyFoods.js');

var _MyFoods2 = _interopRequireDefault(_MyFoods);

var _MyRestaurants = require('user/MyRestaurants.js');

var _MyRestaurants2 = _interopRequireDefault(_MyRestaurants);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var UserHomepage = function (_React$Component) {
  _inherits(UserHomepage, _React$Component);

  function UserHomepage(props) {
    _classCallCheck(this, UserHomepage);

    var _this = _possibleConstructorReturn(this, (UserHomepage.__proto__ || Object.getPrototypeOf(UserHomepage)).call(this, props));

    _this.state = {
      user: {}
    };
    return _this;
  }

  _createClass(UserHomepage, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      $('#userHomepage .item').tab();
    }
  }, {
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch();
    }
  }, {
    key: 'fetch',
    value: function fetch() {
      var _this2 = this;

      Api.me().then(function (data) {
        console.log(data);
        _this2.setState({ user: data });
        $('#userHomepage .item').tab();
      }).catch(function (error) {
        console.log(error);
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { id: 'userHomepage', className: 'ui-container' },
        React.createElement(
          'div',
          { className: 'ui pointing menu' },
          React.createElement(
            'a',
            { className: 'item active', 'data-tab': 'consumptionHistory' },
            'Consumption History'
          ),
          React.createElement(
            'a',
            { className: 'item', 'data-tab': 'myFoods' },
            'My Foods'
          ),
          this.state.user.isServer && React.createElement(
            'a',
            { className: 'item', 'data-tab': 'myRestaurants' },
            'My Restaurants'
          ),
          React.createElement(
            'a',
            { className: 'item', 'data-tab': 'myDiets' },
            'My Diets'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui tab active', 'data-tab': 'consumptionHistory' },
          React.createElement(_ConsumptionHistory2.default, null)
        ),
        this.state.user.foods && React.createElement(
          'div',
          { className: 'ui tab', 'data-tab': 'myFoods' },
          React.createElement(_MyFoods2.default, { foods: this.state.user.foods || [] })
        ),
        this.state.user.isServer && React.createElement(
          'div',
          { className: 'ui tab', 'data-tab': 'myRestaurants' },
          React.createElement(_MyRestaurants2.default, { restaurants: this.state.user.restaurants })
        ),
        React.createElement(
          'div',
          { className: 'ui tab', 'data-tab': 'myDiets' },
          React.createElement(_MyDiets2.default, null)
        )
      );
    }
  }]);

  return UserHomepage;
}(React.Component);

exports.default = UserHomepage;