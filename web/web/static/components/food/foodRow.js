'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var FoodRow = function (_React$Component) {
  _inherits(FoodRow, _React$Component);

  function FoodRow(props) {
    _classCallCheck(this, FoodRow);

    var _this = _possibleConstructorReturn(this, (FoodRow.__proto__ || Object.getPrototypeOf(FoodRow)).call(this, props));

    var ingredientStr = '';
    props.data.ingredients.forEach(function (ingredient) {
      ingredientStr += ' ' + ingredient.name;
    });
    _this.state = {
      name: props.data.name,
      id: props.data.id,
      ingredientStr: ingredientStr
    };
    _this.click = _this.click.bind(_this);
    return _this;
  }

  _createClass(FoodRow, [{
    key: 'click',
    value: function click() {
      var link = '/foods/' + this.state.id;
      return function () {
        window.location.href = link;
      };
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'item' },
        React.createElement(
          'div',
          { className: 'content' },
          React.createElement(
            'a',
            { className: 'header', onClick: this.click() },
            this.state.name
          ),
          React.createElement(
            'div',
            { className: 'description' },
            this.state.ingredientStr
          )
        )
      );
    }
  }]);

  return FoodRow;
}(React.Component);