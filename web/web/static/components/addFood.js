'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var AddFood = function (_React$Component) {
  _inherits(AddFood, _React$Component);

  function AddFood(props) {
    _classCallCheck(this, AddFood);

    var _this = _possibleConstructorReturn(this, (AddFood.__proto__ || Object.getPrototypeOf(AddFood)).call(this, props));

    _this.state = {
      data: null,
      errors: null,
      ingredient: '',
      ingredientId: '',
      ingredients: [{ name: '', weight: '', list: '', id: '', errors: '' }]
    };
    _this.nameChanged = _this.nameChanged.bind(_this);
    _this.slugChanged = _this.slugChanged.bind(_this);
    _this.submit = _this.submit.bind(_this);
    _this.ingredientNameChanged = _this.ingredientNameChanged.bind(_this);
    _this.ingredientWeightChanged = _this.ingredientWeightChanged.bind(_this);
    _this.appendIngredient = _this.appendIngredient.bind(_this);
    return _this;
  }

  _createClass(AddFood, [{
    key: 'nameChanged',
    value: function nameChanged(e) {
      this.setState({ name: e.target.value });
    }
  }, {
    key: 'slugChanged',
    value: function slugChanged(e) {
      this.setState({ slug: e.target.value });
    }
  }, {
    key: 'ingredientNameChanged',
    value: function ingredientNameChanged(index, e) {
      var ingArray = this.state.ingredients;
      var currentIng = ingArray[index];
      currentIng.name = e.target.value;
      ingArray[index] = currentIng;
      this.setState({ ingredients: ingArray });
      this.ingredientSearch(currentIng.name, index);
    }
  }, {
    key: 'ingredientWeightChanged',
    value: function ingredientWeightChanged(index, e) {
      var ingArray = this.state.ingredients;
      var currentIng = ingArray[index];
      currentIng.weight = e.target.value;
      ingArray[index] = currentIng;
      this.setState({ ingredients: ingArray });
    }
  }, {
    key: 'appendIngredient',
    value: function appendIngredient(e) {
      e.preventDefault();
      var newIng = { name: '', weight: '', id: '' };
      this.setState({ ingredients: this.state.ingredients.concat(newIng) });
    }
  }, {
    key: 'ingredientSearch',
    value: function ingredientSearch(query, index) {
      var _this2 = this;

      Api.searchIngredient(query).then(function (ingres) {
        var list = ingres.map(function (ing) {
          _this2.setState({ ingredientId: ing.id });
          return React.createElement(
            'div',
            { className: 'item' },
            ing.name
          );
        });
        var ingArray = _this2.state.ingredients;
        var currentIng = ingArray[index];
        currentIng.list = list;
        currentIng.id = ingres[ingres.length - 1].id;
        ingArray[index] = currentIng;
        _this2.setState({ ingredients: ingArray });
      });
    }
  }, {
    key: 'submit',
    value: function submit(e) {
      var _this3 = this;

      e.preventDefault();
      this.setState({ errors: null });
      var postData = {
        name: this.state.name,
        slug: this.state.slug
      };

      Api.addFood(postData).then(function (data) {
        _this3.state.ingredients.map(function (ingredient, index) {
          var _this4 = this;

          var currentIng = {
            weight: parseInt(ingredient.weight),
            ingredient: ingredient.id,
            food: data.id
          };
          Api.addIngredientToFood(currentIng).then(function (data) {}).catch(function (err) {
            console.log("error during ingredient insertion");
            console.log(err.data);

            var ingArray = _this4.state.ingredients;
            var currentIng = ingArray[index];
            console.log(err.data);
            currentIng.errors = err.data.weight[0];
            ingArray[index] = currentIng;
            console.log(currentIng);
            _this4.setState({ ingredients: ingArray, errors: err.data });
            return;
            // TODO: remove added food from database
          });
        }, _this3);

        // TODO: redirect to the page of newly added food
      }).catch(function (err) {
        _this3.setState({ errors: err.data });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var formClassName = 'ui form';
      if (this.state.errors) formClassName += ' error';
      return React.createElement(
        'div',
        { id: 'addFoodForm' },
        React.createElement(
          'div',
          { className: 'header' },
          'Add New Food'
        ),
        React.createElement(
          'div',
          { className: 'content' },
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
              React.createElement('input', { type: 'text', name: 'name', placeholder: 'name', value: this.state.name, onChange: this.nameChanged }),
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
                ' Slug '
              ),
              React.createElement('input', { type: 'text', name: 'slug', placeholder: 'slug', value: this.state.slug, onChange: this.slugChanged }),
              this.state.errors && this.state.errors.slug && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.slug[0]
                )
              )
            ),
            this.state.ingredients.map(function (ingredient, index) {
              return React.createElement(
                'div',
                { className: 'fields' },
                React.createElement(
                  'div',
                  { className: 'field' },
                  React.createElement(
                    'label',
                    null,
                    ' Ingredient '
                  ),
                  React.createElement('input', { type: 'text', placeholder: 'ingredient', value: ingredient.name, onChange: this.ingredientNameChanged.bind(this, index) }),
                  React.createElement(
                    'ul',
                    { className: 'ui list' },
                    ingredient.list
                  )
                ),
                React.createElement(
                  'div',
                  { className: 'field' },
                  React.createElement(
                    'label',
                    null,
                    ' Weight '
                  ),
                  React.createElement('input', { type: 'text', placeholder: 'weight', value: ingredient.weight, onChange: this.ingredientWeightChanged.bind(this, index) }),
                  ingredient.errors != '' && React.createElement(
                    'div',
                    { className: 'ui error message' },
                    React.createElement(
                      'p',
                      null,
                      ingredient.errors
                    )
                  )
                )
              );
            }, this),
            React.createElement(
              'a',
              { href: 'javascript:void(0);', onClick: this.appendIngredient },
              'Append ingredient'
            ),
            React.createElement(
              'button',
              { className: 'ui button', type: 'submit', style: { width: '100%' }, onClick: this.submit },
              'Add Food'
            )
          )
        )
      );
    }
  }]);

  return AddFood;
}(React.Component);