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

var _MultipleSelect = require('service/MultipleSelect.js');

var _MultipleSelect2 = _interopRequireDefault(_MultipleSelect);

var _TagSelect = require('service/TagSelect.js');

var _TagSelect2 = _interopRequireDefault(_TagSelect);

var _Slider = require('service/Slider.js');

var _Slider2 = _interopRequireDefault(_Slider);

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
    _this.state = {
      filter: {}
    };
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

    // when advanced search input is changed

  }, {
    key: 'advancedChange',
    value: function advancedChange(e) {
      this.setState({ advancedQuery: e.target.value });
    }

    // send search api call

  }, {
    key: 'search',
    value: function search(query) {
      var _this2 = this;

      Api.search(query).then(function (data) {
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
    key: 'setIngredientOptions',
    value: function setIngredientOptions() {
      var self = this;
      Api.getIngredients().then(function (data) {
        self.setState({
          options: data
        });
      });
    }
  }, {
    key: 'ingredientsChanged',
    value: function ingredientsChanged(ingredientIds) {
      console.log('excluded ingredient ids', ingredientIds);
      this.setState({
        selectedIngredients: ingredientIds
      });
    }
  }, {
    key: 'setDietOptions',
    value: function setDietOptions() {
      var self = this;
      Api.getDiets().then(function (data) {
        self.setState({
          options: data
        });
      });
    }
  }, {
    key: 'dietsChanged',
    value: function dietsChanged(dietIds) {
      console.log('diets', dietIds);
      this.setState({
        selectedDiets: dietIds
      });
    }
  }, {
    key: 'tagsChanged',
    value: function tagsChanged(tags) {
      console.log('tags changed', tags);
      this.setState({
        selectedTags: tags
      });
    }
  }, {
    key: 'proteinChanged',
    value: function proteinChanged(min, max) {
      this.setState({
        minProteinVal: min,
        maxProteinVal: max
      });
    }
  }, {
    key: 'carbChanged',
    value: function carbChanged(min, max) {
      this.setState({
        minCarbVal: min,
        maxCarbVal: max
      });
    }
  }, {
    key: 'fatChanged',
    value: function fatChanged(min, max) {
      this.setState({
        minFatVal: min,
        maxFatVal: max
      });
    }
  }, {
    key: 'energyChanged',
    value: function energyChanged(min, max) {
      this.setState({
        minEnergy: min,
        maxEnergy: max
      });
    }
  }, {
    key: 'advancedSearch',
    value: function advancedSearch(e) {
      e.preventDefault();
      var self = this;
      var filter = {};

      if (this.state.minProteinVal) filter.minProteinVal = this.state.minProteinVal;
      if (this.state.maxProteinVal) filter.maxProteinVal = this.state.maxProteinVal;
      if (this.state.minCarbVal) filter.minCarbVal = this.state.minCarbVal;
      if (this.state.maxCarbVal) filter.maxCarbVal = this.state.maxCarbVal;
      if (this.state.minFatVal) filter.minFatVal = this.state.minFatVal;
      if (this.state.maxFatVal) filter.maxFatVal = this.state.maxFatVal;
      if (this.state.minEnergy) filter.minEnergy = this.state.minEnergy;
      if (this.state.maxEnergy) filter.maxEnergy = this.state.maxEnergy;

      if (this.state.advancedQuery) filter.advancedQuery = this.state.advancedQuery;
      if (this.state.selectedIngredients) filter.ingredients = this.state.selectedIngredients;
      if (this.state.selectedDiets) filter.diets = this.state.selectedDiets;
      if (this.state.selectedTags) {
        filter.tags = [];
        this.state.selectedTags.forEach(function (tag) {
          filter.tags.push(tag.name);
        });
      }
      console.log('filter', filter);
      Api.advancedSearch(filter).then(function (data) {
        console.log('advanced search result', data);
        var foodList = data.map(function (food) {
          return React.createElement(_FoodRow2.default, { key: food.id, data: food });
        });
        self.setState({ advancedFoodList: foodList });
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
            { className: 'item', 'data-tab': 'search' },
            'Search'
          ),
          React.createElement(
            'a',
            { className: 'item active', 'data-tab': 'advancedSearch' },
            'Advanced Search'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui bottom attached tab segment', 'data-tab': 'search' },
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
          { className: 'ui bottom attached tab segment active', 'data-tab': 'advancedSearch' },
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
                  value: this.state.advanceQuery, onChange: this.advancedChange.bind(this)
                }),
                React.createElement(
                  'div',
                  { style: { textAlign: 'center', marginTop: 20 } },
                  React.createElement(
                    'button',
                    { type: 'submit', className: 'ui button', onClick: this.advancedSearch.bind(this) },
                    'SEARCH FOOD'
                  ),
                  React.createElement(
                    'h3',
                    null,
                    'Options'
                  )
                ),
                React.createElement(
                  'div',
                  null,
                  React.createElement(
                    'div',
                    { style: { display: 'inline-block', width: '50%', padding: 15 } },
                    React.createElement(
                      'h5',
                      null,
                      'Excluded ingredients'
                    ),
                    React.createElement(_MultipleSelect2.default, { onChange: this.ingredientsChanged.bind(this), setOptions: this.setIngredientOptions, name: 'foods', placeholder: 'Select ingredients' })
                  ),
                  React.createElement(
                    'div',
                    { style: { display: 'inline-block', width: '50%', padding: 15 } },
                    React.createElement(
                      'h5',
                      null,
                      'Diets'
                    ),
                    React.createElement(_MultipleSelect2.default, { onChange: this.dietsChanged.bind(this), setOptions: this.setDietOptions, name: 'diets', placeholder: 'Select diet' })
                  ),
                  React.createElement(
                    'h5',
                    null,
                    'Semantic tags'
                  ),
                  React.createElement(_TagSelect2.default, { onChange: this.tagsChanged.bind(this), name: 'tags', placeholder: 'Search tag' }),
                  React.createElement(
                    'div',
                    null,
                    React.createElement(
                      'div',
                      { style: { display: 'inline-block', width: '50%' } },
                      React.createElement(
                        'h4',
                        { style: { marginBottom: 50 } },
                        'Protein Weight (g)'
                      ),
                      React.createElement(_Slider2.default, { id: 'proteinSlider', onChange: this.proteinChanged.bind(this) })
                    ),
                    React.createElement(
                      'div',
                      { style: { display: 'inline-block', width: '50%' } },
                      React.createElement(
                        'h4',
                        { style: { marginBottom: 50 } },
                        'Carbonhydrate Weight (g)'
                      ),
                      React.createElement(_Slider2.default, { id: 'carbSlider', onChange: this.carbChanged.bind(this) })
                    )
                  ),
                  React.createElement(
                    'div',
                    null,
                    React.createElement(
                      'div',
                      { style: { display: 'inline-block', width: '50%' } },
                      React.createElement(
                        'h4',
                        { style: { marginBottom: 50 } },
                        'Fat Weight (g)'
                      ),
                      React.createElement(_Slider2.default, { id: 'fatSlider', onChange: this.fatChanged.bind(this) })
                    ),
                    React.createElement(
                      'div',
                      { style: { display: 'inline-block', width: '50%' } },
                      React.createElement(
                        'h4',
                        { style: { marginBottom: 50 } },
                        'Energy (kcal)'
                      ),
                      React.createElement(_Slider2.default, { id: 'energySlider', onChange: this.energyChanged.bind(this) })
                    )
                  )
                )
              )
            )
          ),
          React.createElement(
            'div',
            { className: 'ui relaxed divided list' },
            this.state.advancedFoodList
          )
        )
      );
    }
  }]);

  return Homepage;
}(React.Component);

exports.default = Homepage;