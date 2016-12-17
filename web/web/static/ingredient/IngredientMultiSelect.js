'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var IngredientMultiSelect = function (_React$Component) {
  _inherits(IngredientMultiSelect, _React$Component);

  function IngredientMultiSelect(props) {
    _classCallCheck(this, IngredientMultiSelect);

    var _this = _possibleConstructorReturn(this, (IngredientMultiSelect.__proto__ || Object.getPrototypeOf(IngredientMultiSelect)).call(this, props));

    _this.state = {
      ingredients: props.ingredients,
      selectedIds: props.selectedIds || []
    };
    return _this;
  }

  _createClass(IngredientMultiSelect, [{
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
        $('#ingredientInput .ui.dropdown').dropdown({
          onChange: function onChange(index) {
            self.setState({
              ingredient: self.state.ingredients[index],
              value: self.state.ingredients[index].defaultValue,
              unit: self.state.ingredients[index].defaultUnit,
              measureValue: self.state.ingredients[index].measureValue
            });
            self.inclusion.ingredientId = self.state.ingredients[index].id;
          }
        });
      }).catch(function (err) {
        _this2.setState({ errors: err });
      });
    }
  }, {
    key: 'valueChanged',
    value: function valueChanged(event) {
      var newValue = event.target.value;
      var defValue = this.state.ingredient.defaultValue;
      var measureValue = this.state.ingredient.measureValue * (newValue / defValue);
      this.setState({
        value: newValue,
        measureValue: measureValue.toFixed(2)
      });
      this.inclusion.value = newValue;
    }
  }, {
    key: 'measureValueChanged',
    value: function measureValueChanged(event) {
      var newMeasureValue = event.target.value;
      var defMeasureValue = this.state.ingredient.measureValue;
      var value = this.state.ingredient.defaultValue * (newMeasureValue / defMeasureValue);
      this.setState({
        measureValue: newMeasureValue,
        value: value.toFixed(2)
      });
      this.inclusion.value = value;
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { id: 'ingredientInput' + this.id, style: { marginBottom: 20 } },
        React.createElement(
          'div',
          { className: 'ui search selection dropdown' },
          React.createElement('input', { type: 'hidden', name: 'gender' }),
          React.createElement('i', { className: 'dropdown icon' }),
          React.createElement(
            'div',
            { className: 'default text' },
            'Ingredient'
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
        ),
        React.createElement(
          'div',
          { className: 'ui right labeled input', style: { marginLeft: 20 } },
          React.createElement('input', { type: 'text', placeholder: 'Value', value: this.state.measureValue, onChange: this.measureValueChanged.bind(this), style: { width: 75 } }),
          React.createElement(
            'div',
            { className: 'ui basic label' },
            this.state.ingredient.measureUnit
          )
        ),
        React.createElement(
          'div',
          { className: 'ui right labeled input', style: { marginLeft: 20 } },
          React.createElement('input', { type: 'text', placeholder: 'Value', value: this.state.value, onChange: this.valueChanged.bind(this), style: { width: 75 } }),
          React.createElement(
            'div',
            { className: 'ui basic label' },
            this.state.ingredient.defaultUnit
          )
        )
      );
    }
  }]);

  return IngredientMultiSelect;
}(React.Component);

exports.default = IngredientMultiSelect;