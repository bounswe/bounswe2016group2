'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _FoodRow = require('../food/FoodRow.js');

var _FoodRow2 = _interopRequireDefault(_FoodRow);

var _Comments = require('comment/Comments.js');

var _Comments2 = _interopRequireDefault(_Comments);

var _Rate = require('rate/Rate.js');

var _Rate2 = _interopRequireDefault(_Rate);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var RestaurantPage = function (_React$Component) {
  _inherits(RestaurantPage, _React$Component);

  function RestaurantPage(props) {
    _classCallCheck(this, RestaurantPage);

    var _this = _possibleConstructorReturn(this, (RestaurantPage.__proto__ || Object.getPrototypeOf(RestaurantPage)).call(this, props));

    _this.state = {
      id: props.id,
      restaurant: {
        foods: []
      }
    };
    _this.fetch = _this.fetch.bind(_this);
    _this.comment = _this.comment.bind(_this);
    _this.restaurantRated = _this.restaurantRated.bind(_this);
    return _this;
  }

  _createClass(RestaurantPage, [{
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: 'fetch',
    value: function fetch(id) {
      var _this2 = this;

      Api.getRestaurant(id).then(function (data) {
        console.log(data);
        _this2.setState({ restaurant: data });
      }).catch(function (err) {
        _this2.setState({ errors: err });
      });

      Api.me().then(function (data) {
        var restaurantRates = data.restaurantRates;
        var userRate = 0;
        for (var i = 0; i < restaurantRates.length; i++) {
          if (restaurantRates[i].restaurant == _this2.state.id) {
            userRate = restaurantRates[i].score;
          }
        }
        _this2.setState({ userRate: userRate });
      });
    }
  }, {
    key: 'getComments',
    value: function getComments(id) {
      var _this3 = this;

      var self = this;
      Api.getRestaurant(id).then(function (data) {
        self.setState({ comments: data.comments });
      }).catch(function (err) {
        _this3.setState({ errors: err.data });
      });
    }
  }, {
    key: 'comment',
    value: function comment(data) {
      var _this4 = this;

      Api.commentOnRestaurant(this.state.id, data).then(function (data) {
        _this4.fetch(_this4.state.id); // get updated comments list
      }).catch(function (error) {
        _this4.setState({ errors: error.data });
      });
    }
  }, {
    key: 'restaurantRated',
    value: function restaurantRated(rate) {
      var _this5 = this;

      var postData = {
        score: rate
      };
      Api.rateOnRestaurant(this.state.id, postData).then(function (data) {
        console.log(data);
      }).catch(function (error) {
        _this5.setState({ errors: error.data });
      });
    }
  }, {
    key: 'getRating',
    value: function getRating(id) {
      var _this6 = this;

      Api.getRestaurant(id).then(function (data) {
        _this6.setState({ rating: data.rate });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'ui segments' },
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h1',
            { className: 'ui header', style: { textAlign: 'center' } },
            ' ',
            this.state.restaurant.name || "Restaurant not found",
            ' '
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment', style: { display: 'flex', justifyContent: 'space-between', alignItems: 'center' } },
          token && this.state.restaurant.rate !== undefined && this.state.userRate !== undefined && React.createElement(_Rate2.default, { id: this.state.id, label: 'Restaurant Rating', onChange: this.restaurantRated, getRating: this.getRating, initialRating: this.state.restaurant.rate, initialUserRating: this.state.userRate, name: 'restaurants' + this.state.id })
        ),
        React.createElement(
          'div',
          { className: 'ui segment', style: { padding: 0, overflow: 'hidden', maxHeight: 400, textAlign: 'center', width: '100%' } },
          React.createElement('img', { src: this.state.restaurant.photo, className: 'img-responsive' })
        ),
        this.state.restaurant.description && React.createElement(
          'div',
          { className: 'ui segment', style: { textAlign: 'center' } },
          React.createElement(
            'h2',
            { className: 'ui header' },
            'Description'
          ),
          React.createElement(
            'p',
            null,
            this.state.restaurant.description
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h1',
            { className: 'ui header', style: { textAlign: 'center' } },
            'Foods'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          this.state.restaurant.foods.map(function (food) {
            return React.createElement(_FoodRow2.default, { key: food.id, data: food });
          })
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h1',
            { className: 'ui header', style: { textAlign: 'center' } },
            'Comments'
          )
        ),
        this.state.restaurant.comments && React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(_Comments2.default, { getComments: this.getComments, id: this.state.id, comment: this.comment })
        )
      );
    }
  }]);

  return RestaurantPage;
}(React.Component);

exports.default = RestaurantPage;