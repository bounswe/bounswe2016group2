'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var DietRow = function (_React$Component) {
  _inherits(DietRow, _React$Component);

  function DietRow(props) {
    _classCallCheck(this, DietRow);

    var _this = _possibleConstructorReturn(this, (DietRow.__proto__ || Object.getPrototypeOf(DietRow)).call(this, props));

    var excludedIngredientStr = '';
    props.data.ingredients.forEach(function (ingredient, i) {
      if (i != 0) excludedIngredientStr += ', ';
      excludedIngredientStr += ingredient.name;
    });
    if (excludedIngredientStr) excludedIngredientStr += " excluded";

    _this.state = {
      name: props.data.name,
      id: props.data.id,
      excludedIngredientStr: excludedIngredientStr
    };
    return _this;
  }

  _createClass(DietRow, [{
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'item', style: { marginBottom: 10, textAlign: 'center' } },
        React.createElement(
          'div',
          { className: 'content' },
          React.createElement(
            'a',
            { className: 'header', style: { fontSize: 16 }, href: "/diets/" + this.state.id },
            this.state.name
          ),
          React.createElement(
            'div',
            { className: 'description' },
            this.state.excludedIngredientStr
          )
        )
      );
    }
  }]);

  return DietRow;
}(React.Component);

exports.default = DietRow;