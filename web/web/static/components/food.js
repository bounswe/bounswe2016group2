"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var FoodInput = function (_React$Component) {
  _inherits(FoodInput, _React$Component);

  function FoodInput(props) {
    _classCallCheck(this, FoodInput);

    var _this = _possibleConstructorReturn(this, (FoodInput.__proto__ || Object.getPrototypeOf(FoodInput)).call(this, props));

    _this.state = {};
    _this.search = props.search;
    _this.change = _this.change.bind(_this);
    return _this;
  }

  _createClass(FoodInput, [{
    key: "change",
    value: function change(e) {
      this.setState({ query: e.target.value });
      this.search(e.target.value);
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        null,
        React.createElement(
          "form",
          { className: "ui form" },
          React.createElement(
            "div",
            { className: "field" },
            React.createElement("input", { type: "text", name: "food", placeholder: "Search food",
              value: this.state.query, onChange: this.change
            })
          )
        ),
        React.createElement("form", { className: "ui form" })
      );
    }
  }]);

  return FoodInput;
}(React.Component);

var Food = function (_React$Component2) {
  _inherits(Food, _React$Component2);

  function Food(props) {
    _classCallCheck(this, Food);

    var _this2 = _possibleConstructorReturn(this, (Food.__proto__ || Object.getPrototypeOf(Food)).call(this, props));

    var ingredientStr = '';
    props.data.ingredients.forEach(function (ingredient) {
      ingredientStr += ' ' + ingredient.name;
    });
    _this2.state = {
      name: props.data.name,
      id: props.data.id,
      ingredientStr: ingredientStr
    };
    _this2.click = _this2.click.bind(_this2);
    return _this2;
  }

  _createClass(Food, [{
    key: "click",
    value: function click() {
      var link = '/food/' + this.state.id;
      return function () {
        window.location.href = link;
      };
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "item", onClick: this.click() },
        React.createElement(
          "div",
          { className: "header" },
          this.state.name
        ),
        this.state.ingredientStr
      );
    }
  }]);

  return Food;
}(React.Component);

var FoodSearch = function (_React$Component3) {
  _inherits(FoodSearch, _React$Component3);

  function FoodSearch(props) {
    _classCallCheck(this, FoodSearch);

    var _this3 = _possibleConstructorReturn(this, (FoodSearch.__proto__ || Object.getPrototypeOf(FoodSearch)).call(this, props));

    _this3.search = _this3.search.bind(_this3);
    _this3.state = { foods: [] };
    return _this3;
  }

  _createClass(FoodSearch, [{
    key: "search",
    value: function search(query) {
      var _this4 = this;

      Api.searchFood(query).then(function (foods) {
        var list = foods.map(function (food) {
          return React.createElement(Food, { key: food.id, data: food });
        });
        _this4.setState({ list: list });
      });
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "ui center aligned segment" },
        React.createElement(FoodInput, { search: this.search }),
        React.createElement(
          "ul",
          { className: "ui list" },
          this.state.list
        )
      );
    }
  }]);

  return FoodSearch;
}(React.Component);