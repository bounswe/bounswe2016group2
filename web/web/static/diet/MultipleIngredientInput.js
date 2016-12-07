'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var MultipleIngredientInput = function (_React$Component) {
  _inherits(MultipleIngredientInput, _React$Component);

  function MultipleIngredientInput(props) {
    _classCallCheck(this, MultipleIngredientInput);

    var _this = _possibleConstructorReturn(this, (MultipleIngredientInput.__proto__ || Object.getPrototypeOf(MultipleIngredientInput)).call(this, props));

    _this.ingredients = props.ingredients;
    _this.state = {
      ingredients: [],
      selectedIngredients: []
    };
    return _this;
  }

  _createClass(MultipleIngredientInput, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      var _this2 = this;

      var self = this;
      // fetch ingredeints
      Api.getIngredients().then(function (data) {
        _this2.setState({
          ingredients: data
        });
        // searchable semantic dropdown for ingredient select
        $('#MultipleIngredientInput .ui.dropdown').dropdown({
          onChange: function onChange(index) {
            var indices = index.split(',');
            var ingredientIds = [];
            indices.forEach(function (index) {
              ingredientIds.push(self.state.ingredients[index].id);
            });
            self.ingredients.list = ingredientIds;
          }
        });
      }).catch(function (err) {
        _this2.setState({ errors: err });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { id: 'MultipleIngredientInput' },
        React.createElement(
          'div',
          { className: 'ui search selection dropdown multiple' },
          React.createElement('i', { className: 'dropdown icon' }),
          React.createElement(
            'div',
            { className: 'default text' },
            'Select ingredients...'
          ),
          React.createElement(
            'div',
            { className: 'menu' },
            this.state.ingredients.map(function (ingredient, index) {
              return React.createElement(
                'div',
                { className: 'item', 'data-value': index, key: index },
                ingredient.name
              );
            })
          )
        )
      );
    }
  }]);

  return MultipleIngredientInput;
}(React.Component);

exports.default = MultipleIngredientInput;