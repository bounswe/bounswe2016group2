"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var DietPage = function (_React$Component) {
  _inherits(DietPage, _React$Component);

  function DietPage(props) {
    _classCallCheck(this, DietPage);

    var _this = _possibleConstructorReturn(this, (DietPage.__proto__ || Object.getPrototypeOf(DietPage)).call(this, props));

    _this.state = {
      id: props.id,
      diet: {
        ingredients: []
      }
    };
    _this.fetch = _this.fetch.bind(_this);
    return _this;
  }

  _createClass(DietPage, [{
    key: "componentWillMount",
    value: function componentWillMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: "fetch",
    value: function fetch(id) {
      var _this2 = this;

      Api.getDiet(id).then(function (data) {
        _this2.setState({ diet: data });
      }).catch(function (err) {
        _this2.setState({ errors: err });
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
            this.state.diet.name || "Diet not found",
            " "
          )
        ),
        this.state.diet.description && React.createElement(
          "div",
          { className: "ui segment", style: { textAlign: 'center' } },
          React.createElement(
            "h2",
            { className: "ui header" },
            "Description"
          ),
          React.createElement(
            "p",
            null,
            this.state.diet.description
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h2",
            { className: "ui header", style: { textAlign: 'center' } },
            "Minimum and Maximum Values"
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
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
            React.createElement(
              "tbody",
              null,
              React.createElement(
                "tr",
                null,
                React.createElement(
                  "td",
                  null,
                  "Minimum Value"
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minEnergy),
                  " ",
                  Constants.value.energy.unit,
                  " "
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minProteinVal),
                  " ",
                  Constants.macro.protein.unit,
                  " "
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minCarbVal),
                  " ",
                  Constants.macro.carb.unit,
                  " "
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minFatVal),
                  " ",
                  Constants.macro.fat.unit,
                  " "
                )
              ),
              React.createElement(
                "tr",
                null,
                React.createElement(
                  "td",
                  null,
                  "Maximum Value"
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxEnergy),
                  " ",
                  Constants.value.energy.unit,
                  " "
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxProteinVal),
                  " ",
                  Constants.macro.protein.unit,
                  " "
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxCarbVal),
                  " ",
                  Constants.macro.carb.unit,
                  " "
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxFatVal),
                  " ",
                  Constants.macro.fat.unit,
                  " "
                )
              ),
              React.createElement(
                "tr",
                null,
                React.createElement(
                  "td",
                  null,
                  "Minimum Rate"
                ),
                React.createElement("td", null),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minProteinRate)
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minCarbRate)
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.minFatRate)
                )
              ),
              React.createElement(
                "tr",
                null,
                React.createElement(
                  "td",
                  null,
                  "Maximum Rate"
                ),
                React.createElement("td", null),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxProteinRate)
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxCarbRate)
                ),
                React.createElement(
                  "td",
                  null,
                  Number(this.state.diet.maxFatRate)
                )
              )
            )
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "h2",
            { className: "ui header", style: { textAlign: 'center' } },
            "Excluded Ingredients"
          )
        ),
        React.createElement(
          "div",
          { className: "ui segment" },
          React.createElement(
            "div",
            { className: "ui relaxed divided list" },
            this.state.diet.ingredients.map(function (ingredient, index) {
              return React.createElement(
                "div",
                { className: "item", key: index, style: { textAlign: 'center' } },
                React.createElement(
                  "h3",
                  null,
                  React.createElement(
                    "a",
                    { href: '/ingredient/' + ingredient.id },
                    ingredient.name
                  )
                )
              );
            })
          )
        )
      );
    }
  }]);

  return DietPage;
}(React.Component);

exports.default = DietPage;