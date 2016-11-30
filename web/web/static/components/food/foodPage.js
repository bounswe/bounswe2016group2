'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Ingredient = function (_React$Component) {
  _inherits(Ingredient, _React$Component);

  function Ingredient(props) {
    _classCallCheck(this, Ingredient);

    return _possibleConstructorReturn(this, (Ingredient.__proto__ || Object.getPrototypeOf(Ingredient)).call(this, props));
  }

  _createClass(Ingredient, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        null,
        React.createElement(
          'h3',
          { className: 'header' },
          ' ',
          React.createElement(
            'a',
            { href: '/ingredient/' + this.props.data.id },
            this.props.data.name,
            ' '
          )
        ),
        React.createElement(
          'div',
          null,
          ' ',
          this.props.data.defaultValue || 0,
          ' ',
          this.props.data.defaultUnit,
          ' '
        )
      );
    }
  }]);

  return Ingredient;
}(React.Component);

var FoodPage = function (_React$Component2) {
  _inherits(FoodPage, _React$Component2);

  function FoodPage(props) {
    _classCallCheck(this, FoodPage);

    var _this2 = _possibleConstructorReturn(this, (FoodPage.__proto__ || Object.getPrototypeOf(FoodPage)).call(this, props));

    _this2.state = {
      id: props.id,
      name: null,
      data: null,
      errors: null,
      ingredients: []
    };
    _this2.fetch = _this2.fetch.bind(_this2);
    _this2.ateThis = _this2.ateThis.bind(_this2);
    return _this2;
  }

  _createClass(FoodPage, [{
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: 'fetch',
    value: function fetch(id) {
      var _this3 = this;

      Api.getFood(id).then(function (data) {
        _this3.setState({
          name: data.name,
          url: data.photo,
          ingredients: data.ingredients
        });
      }).catch(function (err) {
        _this3.setState({ errors: err });
      });
    }

    // TODO: currently not working

  }, {
    key: 'ateThis',
    value: function ateThis() {
      var query = {
        value: 1
      };
      Api.foodAte(this.state.id, query).then(function (data) {
        console.log(data);
      }).catch(function (err) {
        console.log(err);
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { id: 'FoodPage' },
        React.createElement(
          'div',
          { className: 'header' },
          React.createElement(
            'h1',
            { className: 'ui header' },
            ' ',
            this.state.name || "Food not found",
            ' '
          )
        ),
        React.createElement(
          'div',
          { className: 'content' },
          React.createElement(
            'div',
            null,
            React.createElement('img', { src: this.state.url, style: { width: 400, height: 400 } })
          ),
          userEmail && this.state.name && React.createElement(
            'button',
            { className: 'ui button', type: 'button', style: { width: '10%' }, onClick: this.ateThis },
            'I ate this!'
          ),
          React.createElement(
            'div',
            null,
            React.createElement(
              'h2',
              { className: 'header' },
              ' Ingredients'
            ),
            this.state.ingredients.map(function (ingredient, index) {
              return React.createElement(Ingredient, { data: ingredient, key: index });
            }, this)
          )
        )
      );
    }
  }]);

  return FoodPage;
}(React.Component);