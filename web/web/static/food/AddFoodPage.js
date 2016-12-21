'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _IngredientInput = require('ingredient/IngredientInput.js');

var _IngredientInput2 = _interopRequireDefault(_IngredientInput);

var _TagSelect = require('service/TagSelect.js');

var _TagSelect2 = _interopRequireDefault(_TagSelect);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var AddFoodPage = function (_React$Component) {
  _inherits(AddFoodPage, _React$Component);

  function AddFoodPage(props) {
    _classCallCheck(this, AddFoodPage);

    var _this = _possibleConstructorReturn(this, (AddFoodPage.__proto__ || Object.getPrototypeOf(AddFoodPage)).call(this, props));

    _this.state = {
      data: null,
      errors: null,
      ingredients: [],
      selectedTags: [],
      inclusions: [{ 'ingredientId': null, 'unit': 'g' }]
    };
    return _this;
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
      var _this2 = this;

      e.preventDefault();
      this.setState({ errors: null });
      var postData = {
        name: this.state.name,
        photo: this.state.photo
      };
      Api.addFood(postData).then(function (data) {
        console.log(data);
        var promises = [];
        var foodId = data.id;
        _this2.state.inclusions.forEach(function (inclusion, index) {
          var ingredientId = inclusion.ingredientId;
          var newData = {
            'value': inclusion.value,
            'unit': inclusion.unit
          };
          console.log(data.id, ingredientId, newData);
          if (ingredientId) {
            promises.push(Api.addIngredientToFood(data.id, ingredientId, newData));
          }
        });

        _this2.state.selectedTags.forEach(function (tag, index) {
          promises.push(Api.addTagToFood(data.id, tag));
        });

        // Evenly, it's possible to use .catch
        Promise.all(promises).then(function (values) {
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
        _this2.setState({ errors: err.data });
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
    key: 'tagsChanged',
    value: function tagsChanged(tags) {
      this.setState({
        selectedTags: tags
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
            return React.createElement(_IngredientInput2.default, { id: index, key: index, inclusion: inclusion });
          }),
          React.createElement(
            'button',
            { className: 'ui button', onClick: this.addMoreIngredient.bind(this) },
            'Add more'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(_TagSelect2.default, { onChange: this.tagsChanged.bind(this), name: 'tags', placeholder: 'Search tag' })
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

exports.default = AddFoodPage;