'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var FoodPage = function (_React$Component) {
  _inherits(FoodPage, _React$Component);

  function FoodPage(props) {
    _classCallCheck(this, FoodPage);

    var _this = _possibleConstructorReturn(this, (FoodPage.__proto__ || Object.getPrototypeOf(FoodPage)).call(this, props));

    _this.state = {
      id: props.id,
      data: null,
      errors: null,
      ingredients: [{ name: '', defaultUnit: '', defaultValue: '' }]
    };
    _this.fetch = _this.fetch.bind(_this);
    _this.fetch(_this.state.id);
    return _this;
  }

  _createClass(FoodPage, [{
    key: 'fetch',
    value: function fetch(id) {
      var _this2 = this;

      Api.getFood(id).then(function (data) {
        _this2.setState({
          name: data.name,
          url: data.photo,
          ingredients: data.ingredients
        });
      }).catch(function (err) {
        _this2.setState({ errors: err.data });
        // TODO: print error message
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
            this.state.name,
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
          React.createElement(
            'div',
            null,
            React.createElement(
              'h2',
              { className: 'header' },
              ' Ingredients'
            ),
            this.state.ingredients.map(function (ingredient, index) {
              return React.createElement(
                'div',
                null,
                React.createElement(
                  'h3',
                  { className: 'header' },
                  ' ',
                  ingredient.name,
                  ' '
                ),
                React.createElement(
                  'div',
                  null,
                  ' ',
                  ingredient.defaultValue || 0,
                  ' ',
                  ingredient.defaultUnit,
                  ' '
                )
              );
            }, this)
          )
        )
      );
    }
  }]);

  return FoodPage;
}(React.Component);