'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _DatePicker = require('service/DatePicker.js');

var _DatePicker2 = _interopRequireDefault(_DatePicker);

var _MyDiets = require('diet/MyDiets.js');

var _MyDiets2 = _interopRequireDefault(_MyDiets);

var _FoodRow = require('food/FoodRow.js');

var _FoodRow2 = _interopRequireDefault(_FoodRow);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ConsumptionHistory = function (_React$Component) {
  _inherits(ConsumptionHistory, _React$Component);

  function ConsumptionHistory(props) {
    _classCallCheck(this, ConsumptionHistory);

    var _this = _possibleConstructorReturn(this, (ConsumptionHistory.__proto__ || Object.getPrototypeOf(ConsumptionHistory)).call(this, props));

    _this.state = {
      data: {
        total: {},
        daily: []
      }
    };

    _this.fetch = _this.fetch.bind(_this);
    return _this;
  }

  _createClass(ConsumptionHistory, [{
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch();
    }
  }, {
    key: 'fetch',
    value: function fetch() {
      var _this2 = this;

      Api.consumptionHistory().then(function (data) {
        console.log(data);
        _this2.setState({ data: data });
      }).catch(function (error) {
        console.log(error);
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var _this3 = this;

      return React.createElement(
        'div',
        { className: 'ui segment' },
        React.createElement(
          'div',
          { style: { display: 'flex', alignItems: 'center' } },
          React.createElement(
            'span',
            { style: { marginLeft: 10, marginRight: 10 } },
            'From'
          ),
          React.createElement(_DatePicker2.default, { name: 'consumptionStartDate', placeholder: 'Start Date', 'default': moment().subtract(1, 'month').toDate() }),
          React.createElement(
            'span',
            { style: { marginLeft: 10, marginRight: 10 } },
            'to'
          ),
          React.createElement(_DatePicker2.default, { name: 'consumptionEndDate', placeholder: 'End Date', 'default': moment().toDate() })
        ),
        React.createElement(
          'div',
          { style: { marginTop: 20 } },
          Object.keys(this.state.data.daily).map(function (key) {
            return React.createElement(
              'div',
              null,
              React.createElement(
                'h3',
                { key: key },
                key
              ),
              _this3.state.data.daily[key].ateFoods.map(function (ateFood) {
                return React.createElement(_FoodRow2.default, { key: ateFood.created, data: ateFood.food });
              })
            );
          })
        )
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
    key: 'componentDidMount',
    value: function componentDidMount() {
      $('#userHomepage .item').tab();
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
            { className: 'item', 'data-tab': 'myDiets' },
            'My Diets'
          ),
          React.createElement(
            'a',
            { className: 'item', 'data-tab': 'favFoods' },
            'Favorite Foods'
          ),
          React.createElement(
            'a',
            { className: 'item', 'data-tab': 'favRestaurants' },
            'Favorite Restaurants'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui tab active', 'data-tab': 'consumptionHistory' },
          React.createElement(ConsumptionHistory, null)
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