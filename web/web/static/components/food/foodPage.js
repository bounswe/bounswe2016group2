"use strict";

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
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        null,
        React.createElement(
          "h3",
          { className: "header" },
          " ",
          React.createElement(
            "a",
            { href: '/ingredient/' + this.props.data.id },
            this.props.data.name,
            " "
          )
        ),
        React.createElement(
          "div",
          null,
          " ",
          this.props.data.defaultValue || 0,
          " ",
          this.props.data.defaultUnit,
          " "
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
      food: {
        ingredients: [],
        inclusions: [],
        details: {}
      }
    };
    _this2.fetch = _this2.fetch.bind(_this2);
    _this2.ateThis = _this2.ateThis.bind(_this2);
    return _this2;
  }

  _createClass(FoodPage, [{
    key: "componentWillMount",
    value: function componentWillMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: "fetch",
    value: function fetch(id) {
      var _this3 = this;

      Api.getFood(id).then(function (data) {
        console.log(data);
        _this3.setState({ food: data });
      }).catch(function (err) {
        _this3.setState({ errors: err });
      });
    }

    // TODO: currently not working

  }, {
    key: "ateThis",
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
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "ui segments" },
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h1",
            { className: "ui header", style: { textAlign: 'center' } },
            " ",
            this.state.food.name || "Food not found",
            " "
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment", style: { padding: 0, overflow: 'hidden', maxHeight: 400, textAlign: 'center', width: '100%' } },
          React.createElement("img", { src: this.state.food.photo, className: "img-responsive" })
        ),
        React.createElement(
          "div",
          { className: "ui segment", style: { textAlign: 'right' } },
          token && React.createElement(
            "button",
            { className: "ui button", type: "button", onClick: this.ateThis },
            "I ate this!"
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h1",
            { className: "ui header", style: { textAlign: 'center' } },
            "General Information"
          )
        ),
        React.createElement(
          "table",
          { className: "ui segment celled table", style: { width: '100%' } },
          React.createElement(
            "thead",
            null,
            React.createElement(
              "tr",
              null,
              React.createElement("th", null),
              React.createElement(
                "th",
                null,
                "Total"
              ),
              React.createElement(
                "th",
                null,
                Constants.macro.protein.name
              ),
              React.createElement(
                "th",
                null,
                Constants.macro.carb.name
              ),
              React.createElement(
                "th",
                null,
                Constants.macro.fat.name
              ),
              React.createElement(
                "th",
                null,
                "Other"
              ),
              React.createElement(
                "th",
                null,
                "Energy"
              )
            )
          ),
          React.createElement(
            "tr",
            null,
            React.createElement(
              "td",
              null,
              "Weight"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.weight,
              " g"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.protein && this.state.food.details.protein.weight,
              " g"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.carb && this.state.food.details.carb.weight,
              " g"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.fat && this.state.food.details.fat.weight,
              " g"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.other && this.state.food.details.other.weight,
              " g"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.energy,
              " kcal"
            )
          ),
          React.createElement(
            "tr",
            null,
            React.createElement(
              "td",
              null,
              "Rate"
            ),
            React.createElement(
              "td",
              null,
              "100 %"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.protein && Math.round(this.state.food.details.protein.weight / this.state.food.details.weight * 100),
              " %"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.carb && Math.round(this.state.food.details.carb.weight / this.state.food.details.weight * 100),
              " %"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.fat && Math.round(this.state.food.details.fat.weight / this.state.food.details.weight * 100),
              " %"
            ),
            React.createElement(
              "td",
              null,
              this.state.food.details.other && Math.round(this.state.food.details.other.weight / this.state.food.details.weight * 100),
              " %"
            ),
            React.createElement("td", null)
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h1",
            { className: "ui header", style: { textAlign: 'center' } },
            "Ingredients"
          )
        ),
        React.createElement(
          "table",
          { className: "ui segment celled table", style: { width: '100%' } },
          React.createElement(
            "thead",
            null,
            React.createElement(
              "tr",
              null,
              React.createElement(
                "th",
                null,
                "Name"
              ),
              React.createElement(
                "th",
                null,
                Constants.value.weight.name
              ),
              React.createElement(
                "th",
                null,
                "Measure"
              ),
              React.createElement(
                "th",
                null,
                Constants.value.energy.name
              ),
              React.createElement(
                "th",
                null,
                Constants.macro.protein.name
              ),
              React.createElement(
                "th",
                null,
                Constants.macro.carb.name
              ),
              React.createElement(
                "th",
                null,
                Constants.macro.fat.name
              )
            )
          ),
          this.state.food.inclusions.map(function (inclusion, index) {
            return React.createElement(
              "tr",
              { key: index },
              React.createElement(
                "td",
                null,
                React.createElement(
                  "a",
                  { href: '/ingredient/' + inclusion.ingredient.id },
                  inclusion.name
                )
              ),
              React.createElement(
                "td",
                null,
                inclusion.value,
                " ",
                inclusion.unit
              ),
              React.createElement(
                "td",
                null,
                inclusion.ingredient.measureValue,
                " ",
                inclusion.ingredient.measureUnit
              ),
              React.createElement(
                "td",
                null,
                inclusion.ingredient.energy,
                " kcal"
              ),
              React.createElement(
                "td",
                null,
                inclusion.ingredient.protein,
                " g"
              ),
              React.createElement(
                "td",
                null,
                inclusion.ingredient.carb,
                " g"
              ),
              React.createElement(
                "td",
                null,
                inclusion.ingredient.fat,
                " g"
              )
            );
          })
        )
      );
    }
  }]);

  return FoodPage;
}(React.Component);