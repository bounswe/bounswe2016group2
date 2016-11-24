"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var IngrPage = function (_React$Component) {
  _inherits(IngrPage, _React$Component);

  function IngrPage(props) {
    _classCallCheck(this, IngrPage);

    var _this = _possibleConstructorReturn(this, (IngrPage.__proto__ || Object.getPrototypeOf(IngrPage)).call(this, props));

    _this.state = {
      id: props.id,
      data: [{}],
      errors: null
    };
    _this.fetch = _this.fetch.bind(_this);
    _this.ingrProps = _this.ingrProps.bind(_this);
    return _this;
  }

  _createClass(IngrPage, [{
    key: "componentDidMount",
    value: function componentDidMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: "fetch",
    value: function fetch(id) {
      var _this2 = this;

      Api.getIngr(id).then(function (data) {
        console.log(data);
        _this2.setState({
          name: data.name,
          url: data.photo,
          data: data
        });
        _this2.ingrProps();
      }).catch(function (err) {
        _this2.setState({ errors: err.data });
        // TODO: print error message
      });
    }
  }, {
    key: "ingrProps",
    value: function ingrProps() {
      var result = [];
      for (var prop in this.state.data) {
        var propValue = this.state.data[prop];
        result.push(React.createElement(
          "div",
          null,
          React.createElement(
            "label",
            { style: { "font-weight": "bold" } },
            " ",
            prop,
            " "
          ),
          React.createElement(
            "label",
            null,
            " ",
            propValue,
            " "
          )
        ));
      }
      this.setState({ ingrProps: result });
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { id: "IngredientPage" },
        React.createElement(
          "div",
          { className: "header" },
          React.createElement(
            "h1",
            { className: "ui header" },
            " ",
            this.state.name,
            " "
          )
        ),
        React.createElement(
          "div",
          { className: "content" },
          React.createElement(
            "div",
            null,
            this.state.url && React.createElement("img", { src: this.state.url, style: { width: 400, height: 400 } })
          ),
          React.createElement(
            "div",
            null,
            React.createElement(
              "h2",
              { className: "header" },
              " Properties"
            ),
            this.state.ingrProps
          )
        )
      );
    }
  }]);

  return IngrPage;
}(React.Component);