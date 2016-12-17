'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _FoodRow = require('food/FoodRow.js');

var _FoodRow2 = _interopRequireDefault(_FoodRow);

var _RestaurantRow = require('restaurant/RestaurantRow.js');

var _RestaurantRow2 = _interopRequireDefault(_RestaurantRow);

var _IngredientRow = require('ingredient/IngredientRow.js');

var _IngredientRow2 = _interopRequireDefault(_IngredientRow);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Homepage = function (_React$Component) {
  _inherits(Homepage, _React$Component);

  function Homepage(props) {
    _classCallCheck(this, Homepage);

    var _this = _possibleConstructorReturn(this, (Homepage.__proto__ || Object.getPrototypeOf(Homepage)).call(this, props));

    _this.state = {};
    _this.change = _this.change.bind(_this);
    _this.search = _this.search.bind(_this);
    return _this;
  }

  _createClass(Homepage, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      $('.menu .item').tab();
    }

    // when input is changed

  }, {
    key: 'change',
    value: function change(e) {
      this.setState({ query: e.target.value });
      this.search(e.target.value);
    }

    // send search api call

  }, {
    key: 'search',
    value: function search(query) {
      var _this2 = this;

      Api.searchFood(query).then(function (data) {
        var foodList = data.foods.map(function (food) {
          return React.createElement(_FoodRow2.default, { key: food.id, data: food });
        });
        var restaurantList = data.restaurants.map(function (restaurant) {
          return React.createElement(_RestaurantRow2.default, { key: restaurant.id, data: restaurant });
        });
        var ingredientList = data.ingredients.map(function (ingredient) {
          return React.createElement(_IngredientRow2.default, { key: ingredient.id, data: ingredient });
        });
        _this2.setState({
          foodList: foodList,
          restaurantList: restaurantList,
          ingredientList: ingredientList
        });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        null,
        React.createElement(
          'div',
          { className: 'ui top attached tabular menu' },
          React.createElement(
            'a',
            { className: 'item active', 'data-tab': 'search' },
            'Search'
          ),
          React.createElement(
            'a',
            { className: 'item', 'data-tab': 'advancedSearch' },
            'Advanced Search'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui bottom attached tab segment active', 'data-tab': 'search' },
          React.createElement(
            'div',
            null,
            React.createElement(
              'form',
              { className: 'ui form' },
              React.createElement(
                'div',
                { className: 'field' },
                React.createElement('input', { type: 'text', name: 'food', placeholder: 'Search food, ingredient or server',
                  value: this.state.query, onChange: this.change
                })
              )
            ),
            React.createElement('form', { className: 'ui form' })
          ),
          this.state.foodList && this.state.foodList.length > 0 && React.createElement(
            'h4',
            null,
            'Foods'
          ),
          React.createElement(
            'div',
            { className: 'ui relaxed divided list' },
            this.state.foodList
          ),
          this.state.restaurantList && this.state.restaurantList.length > 0 && React.createElement(
            'h4',
            null,
            'Restaurants'
          ),
          React.createElement(
            'div',
            { className: 'ui relaxed divided list' },
            this.state.restaurantList
          ),
          this.state.ingredientList && this.state.ingredientList.length > 0 && React.createElement(
            'h4',
            null,
            'Ingredients'
          ),
          React.createElement(
            'div',
            { className: 'ui relaxed divided list' },
            this.state.ingredientList
          )
        ),
        React.createElement(
          'div',
          { className: 'ui bottom attached tab segment', 'data-tab': 'advancedSearch' },
          React.createElement(
            'div',
            null,
            React.createElement(
              'form',
              { className: 'ui form' },
              React.createElement(
                'div',
                { className: 'field' },
                React.createElement('input', { type: 'text', name: 'food', placeholder: 'Search food',
                  value: this.state.query, onChange: this.change
                })
              )
            ),
            React.createElement('form', { className: 'ui form' })
          )
        )
      );
    }
  }]);

  return Homepage;
}(React.Component);

exports.default = Homepage;