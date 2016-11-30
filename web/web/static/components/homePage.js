'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var HomePage = function (_React$Component) {
  _inherits(HomePage, _React$Component);

  function HomePage(props) {
    _classCallCheck(this, HomePage);

    var _this = _possibleConstructorReturn(this, (HomePage.__proto__ || Object.getPrototypeOf(HomePage)).call(this, props));

    _this.state = {};
    _this.change = _this.change.bind(_this);
    _this.search = _this.search.bind(_this);
    return _this;
  }

  _createClass(HomePage, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      $('.menu .item').tab();
    }

    // when input is changed

  }, {
    key: 'change',
    value: function change(e) {
      this.setState({ query: e.target.value });
      this.search(e.target.value);
    }

    // send search api call

  }, {
    key: 'search',
    value: function search(query) {
      var _this2 = this;

      console.log('anan');
      Api.searchFood(query).then(function (foods) {
        var list = foods.map(function (food) {
          return React.createElement(FoodRow, { key: food.id, data: food });
        });
        _this2.setState({ list: list });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        null,
        React.createElement(
          'div',
          { className: 'ui top attached tabular menu' },
          React.createElement(
            'a',
            { className: 'item active', 'data-tab': 'search' },
            'Search'
          ),
          React.createElement(
            'a',
            { className: 'item', 'data-tab': 'advancedSearch' },
            'Advanced Search'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui bottom attached tab segment active', 'data-tab': 'search' },
          React.createElement(
            'div',
            null,
            React.createElement(
              'form',
              { className: 'ui form' },
              React.createElement(
                'div',
                { className: 'field' },
                React.createElement('input', { type: 'text', name: 'food', placeholder: 'Search food, ingredient or server',
                  value: this.state.query, onChange: this.change
                })
              )
            ),
            React.createElement('form', { className: 'ui form' })
          ),
          React.createElement(
            'ul',
            { className: 'ui list' },
            this.state.list
          )
        ),
        React.createElement('div', { className: 'ui bottom attached tab segment', 'data-tab': 'advancedSearch' })
      );
    }
  }]);

  return HomePage;
}(React.Component);