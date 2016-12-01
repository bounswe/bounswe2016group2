'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var IngredientInput = function (_React$Component) {
  _inherits(IngredientInput, _React$Component);

  function IngredientInput(props) {
    _classCallCheck(this, IngredientInput);

    var _this = _possibleConstructorReturn(this, (IngredientInput.__proto__ || Object.getPrototypeOf(IngredientInput)).call(this, props));

    _this.inclusion = props.inclusion;
    _this.id = props.id;
    _this.state = {
      ingredients: [],
      ingredient: {
        measureValue: 0,
        measureUnit: ''
      }
    };
    return _this;
  }

  _createClass(IngredientInput, [{
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
        $('#ingredientInput' + _this2.id + ' .ui.dropdown').dropdown({
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

  return IngredientInput;
}(React.Component);

var AddFoodPage = function (_React$Component2) {
  _inherits(AddFoodPage, _React$Component2);

  function AddFoodPage(props) {
    _classCallCheck(this, AddFoodPage);

    var _this3 = _possibleConstructorReturn(this, (AddFoodPage.__proto__ || Object.getPrototypeOf(AddFoodPage)).call(this, props));

    _this3.state = {
      data: null,
      errors: null,
      ingredients: [],
      inclusions: [{ 'ingredientId': null, 'unit': 'g' }]
    };
    return _this3;
  }

  _createClass(AddFoodPage, [{
    key: 'addMoreIngredient',
    value: function addMoreIngredient() {
      this.setState({
        inclusions: this.state.inclusions.concat([{ 'ingredientId': null, 'unit': 'g' }])
      });
    }
  }, {
    key: 'submit',
    value: function submit(e) {
      var _this4 = this;

      e.preventDefault();
      this.setState({ errors: null });
      var postData = {
        name: this.state.name,
        photo: this.state.photo
      };
      Api.addFood(postData).then(function (data) {
        console.log(data);
        var inclusionProms = [];
        var foodId = data.id;
        _this4.state.inclusions.forEach(function (inclusion, index) {
          var ingredientId = inclusion.ingredientId;
          var newData = {
            'value': inclusion.value,
            'unit': inclusion.unit
          };
          console.log(data.id, ingredientId, newData);
          if (ingredientId) {
            inclusionProms.push(Api.addIngredientToFood(data.id, ingredientId, newData));
          }
          // console.log(this.state);
        });

        // Evenly, it's possible to use .catch
        Promise.all(inclusionProms).then(function (values) {
          router.navigate('../foods/' + data.id);
        }).catch(function (reason) {
          console.log('error', reason);
          router.navigate('../foods/' + data.id);
        });

        // this.state.ingredients.forEach((ingredient,index) {
        //   const currentIng = {
        //     weight: parseInt(ingredient.weight),
        //     ingredient: ingredient.id,
        //     food: data.id
        //   }
        //   Api.addIngredientToFood(currentIng.food, currentIng.ingredient, currentIng)
        //     .then((data) => {
        //     }).catch((err) => {
        //       var ingArray = this.state.ingredients;
        //       var currentIng = ingArray[index];
        //       currentIng.errors = err.data.weight[0];
        //       ingArray[index] = currentIng;
        //       this.setState({ingredients: ingArray, errors: err.data});
        //
        //       Api.deleteFood(data.id).then().catch();
        //       return;
        //   })}, this);
      }).catch(function (err) {
        console.log(err);
        _this4.setState({ errors: err.data });
      });
    }
  }, {
    key: 'nameChanged',
    value: function nameChanged(event) {
      this.setState({
        name: event.target.value
      });
    }
  }, {
    key: 'photoChanged',
    value: function photoChanged(event) {
      this.setState({
        photo: event.target.value
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var formClassName = 'ui form';
      if (this.state.errors) formClassName += ' error';
      return React.createElement(
        'div',
        { className: 'ui segments' },
        React.createElement(
          'div',
          { className: 'ui segment' },
          'Add New Food'
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'form',
            { className: formClassName },
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                ' Name '
              ),
              React.createElement('input', { type: 'text', name: 'name', placeholder: 'name', value: this.state.name, onChange: this.nameChanged.bind(this) }),
              this.state.errors && this.state.errors.name && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.name[0]
                )
              )
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                ' Photo '
              ),
              React.createElement('input', { type: 'url', name: 'photo', placeholder: 'image url', value: this.state.photo, onChange: this.photoChanged.bind(this) })
            ),
            this.state.errors && this.state.errors.photo && React.createElement(
              'div',
              { className: 'ui error message' },
              React.createElement(
                'p',
                null,
                this.state.errors.photo[0]
              )
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          'Ingredients'
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          this.state.inclusions.map(function (inclusion, index) {
            return React.createElement(IngredientInput, { id: index, key: index, inclusion: inclusion });
          }),
          React.createElement(
            'button',
            { className: 'ui button', onClick: this.addMoreIngredient.bind(this) },
            'Add more'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment', style: { textAlign: 'center' } },
          React.createElement(
            'button',
            { className: 'ui button', type: 'submit', onClick: this.submit.bind(this) },
            'Submit'
          )
        )
      );
    }
  }]);

  return AddFoodPage;
}(React.Component);

var AddFood = function (_React$Component3) {
  _inherits(AddFood, _React$Component3);

  function AddFood() {
    _classCallCheck(this, AddFood);

    return _possibleConstructorReturn(this, (AddFood.__proto__ || Object.getPrototypeOf(AddFood)).apply(this, arguments));
  }

  _createClass(AddFood, [{
    key: 'removeIngredient',
    value: function removeIngredient(index, e) {
      e.preventDefault();
      var ingArray = this.state.ingredients;
      ingArray.splice(index, 1);
      this.setState({ ingredients: ingArray });
    }
  }]);

  return AddFood;
}(React.Component);