'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _Comments = require('comment/Comments.js');

var _Comments2 = _interopRequireDefault(_Comments);

var _Rate = require('rate/Rate.js');

var _Rate2 = _interopRequireDefault(_Rate);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

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
      servingSize: 1,
      food: {
        ingredients: [],
        inclusions: [],
        details: {}
      }
    };
    _this.fetch = _this.fetch.bind(_this);
    _this.ateThis = _this.ateThis.bind(_this);
    _this.servingSizeChanged = _this.servingSizeChanged.bind(_this);
    _this.comment = _this.comment.bind(_this);
    _this.foodRated = _this.foodRated.bind(_this);
    return _this;
  }

  _createClass(FoodPage, [{
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch(this.state.id);
    }
  }, {
    key: 'fetch',
    value: function fetch(id) {
      var _this2 = this;

      Api.getFood(id).then(function (data) {
        _this2.setState({ food: data });
      }).catch(function (err) {
        _this2.setState({ errors: err });
      });

      Api.me().then(function (data) {
        var foodRates = data.foodRates;
        var userRate = 0;
        for (var i = 0; i < foodRates.length; i++) {
          if (foodRates[i].food == _this2.state.id) {
            userRate = foodRates[i].score;
          }
        }
        _this2.setState({ userRate: userRate });
      });
    }
  }, {
    key: 'openAteFoodModal',
    value: function openAteFoodModal() {
      $('#ateFoodModal').modal('show');
    }
  }, {
    key: 'openSuccessModal',
    value: function openSuccessModal() {
      $('#ateFoodSuccModal').modal('show');
    }
  }, {
    key: 'servingSizeChanged',
    value: function servingSizeChanged(e) {
      this.setState({ servingSize: e.target.value });
    }
  }, {
    key: 'ateThis',
    value: function ateThis(e) {
      var _this3 = this;

      e.preventDefault();
      var data = {
        value: this.state.servingSize
      };
      Api.foodAte(this.state.id, data).then(function (data) {
        $('#ateFoodSuccModal').modal('show');
      }).catch(function (err) {
        _this3.setState({ errors: err.data });
      });
    }
  }, {
    key: 'comment',
    value: function comment(data) {
      var _this4 = this;

      Api.commentOnFood(this.state.id, data).then(function (data) {
        _this4.fetch(_this4.state.id); // get updated comments list
      }).catch(function (error) {
        _this4.setState({ errors: error.data });
      });
    }
  }, {
    key: 'getComments',
    value: function getComments(foodId) {
      var _this5 = this;

      var self = this;
      Api.getFood(foodId).then(function (data) {
        self.setState({ comments: data.comments });
      }).catch(function (err) {
        _this5.setState({ errors: err.data });
      });
    }
  }, {
    key: 'foodRated',
    value: function foodRated(rate) {
      var _this6 = this;

      var postData = {
        score: rate
      };
      Api.rateOnFood(this.state.id, postData).catch(function (error) {
        _this6.setState({ errors: error.data });
      });
    }
  }, {
    key: 'getRating',
    value: function getRating(id) {
      var _this7 = this;

      Api.getFood(id).then(function (data) {
        _this7.setState({ rating: data.rate });
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
            this.state.food.name || "Food not found",
            ' '
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment', style: { padding: 0, overflow: 'hidden', maxHeight: 400, textAlign: 'center', width: '100%' } },
          React.createElement('img', { src: this.state.food.photo, className: 'img-responsive' })
        ),
        React.createElement(
          'div',
          { className: 'ui segment', style: { display: 'flex', justifyContent: 'space-between', alignItems: 'center' } },
          this.state.food.restaurant && React.createElement(
            'div',
            null,
            React.createElement(
              'span',
              { style: { marginRight: 5 } },
              'Restaurant:'
            ),
            React.createElement(
              'a',
              { href: "/restaurants/" + this.state.food.restaurant.id },
              this.state.food.restaurant.name
            )
          ),
          token && this.state.food.rate !== undefined && React.createElement(
            'div',
            null,
            React.createElement(
              'button',
              { className: 'ui button', type: 'button', onClick: this.openAteFoodModal },
              'I ate this!'
            ),
            React.createElement(
              'div',
              { id: 'ateFoodSuccModal', className: 'ui small modal' },
              React.createElement(
                'div',
                { className: 'ui message success' },
                React.createElement('i', { className: 'close icon' }),
                React.createElement(
                  'div',
                  { className: 'header' },
                  'Success!'
                ),
                React.createElement(
                  'p',
                  null,
                  'The food has been saved to your nutrition history'
                )
              )
            ),
            React.createElement(
              'div',
              { id: 'ateFoodModal', className: 'ui small modal' },
              React.createElement('i', { className: 'close icon' }),
              React.createElement(
                'div',
                { className: 'header' },
                'You ate this?'
              ),
              React.createElement(
                'div',
                { className: 'content' },
                React.createElement(
                  'form',
                  { className: 'ui form' },
                  React.createElement(
                    'div',
                    { className: 'field' },
                    React.createElement(
                      'label',
                      null,
                      'Serving size'
                    ),
                    React.createElement('input', { type: 'number', min: '0', max: '100', name: 'servingSize', value: this.state.servingSize, onChange: this.servingSizeChanged })
                  ),
                  React.createElement(
                    'button',
                    { className: 'ui button', type: 'submit', style: { width: '100%' }, onClick: this.ateThis },
                    'Submit'
                  )
                )
              )
            )
          ),
          token && this.state.food.rate !== undefined && this.state.userRate !== undefined && React.createElement(_Rate2.default, { id: this.state.id, label: 'Food Rating', onChange: this.foodRated, getRating: this.getRating, initialRating: this.state.food.rate, initialUserRating: this.state.userRate, name: 'foods' + this.state.id })
        ),
        React.createElement(
          'div',
          null,
          this.state.food.description && React.createElement(
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
              this.state.food.description
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h1',
            { className: 'ui header', style: { textAlign: 'center' } },
            'General Information'
          )
        ),
        React.createElement(
          'table',
          { className: 'ui segment celled table', style: { width: '100%' } },
          React.createElement(
            'thead',
            null,
            React.createElement(
              'tr',
              null,
              React.createElement('th', null),
              React.createElement(
                'th',
                null,
                'Total'
              ),
              React.createElement(
                'th',
                null,
                Constants.macro.protein.name
              ),
              React.createElement(
                'th',
                null,
                Constants.macro.carb.name
              ),
              React.createElement(
                'th',
                null,
                Constants.macro.fat.name
              ),
              React.createElement(
                'th',
                null,
                'Other'
              ),
              React.createElement(
                'th',
                null,
                'Energy'
              )
            )
          ),
          React.createElement(
            'tbody',
            null,
            React.createElement(
              'tr',
              null,
              React.createElement(
                'td',
                null,
                'Weight'
              ),
              React.createElement(
                'td',
                null,
                Number(this.state.food.details.weight).toFixed(2),
                ' g'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.protein && Number(this.state.food.details.protein.weight).toFixed(2),
                ' g'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.carb && Number(this.state.food.details.carb.weight).toFixed(2),
                ' g'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.fat && Number(this.state.food.details.fat.weight).toFixed(2),
                ' g'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.other && Number(this.state.food.details.other.weight).toFixed(2),
                ' g'
              ),
              React.createElement(
                'td',
                null,
                Number(this.state.food.details.energy).toFixed(2),
                ' kcal'
              )
            ),
            React.createElement(
              'tr',
              null,
              React.createElement(
                'td',
                null,
                'Rate'
              ),
              React.createElement(
                'td',
                null,
                '100 %'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.protein && Math.round(this.state.food.details.protein.weight / this.state.food.details.weight * 100),
                ' %'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.carb && Math.round(this.state.food.details.carb.weight / this.state.food.details.weight * 100),
                ' %'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.fat && Math.round(this.state.food.details.fat.weight / this.state.food.details.weight * 100),
                ' %'
              ),
              React.createElement(
                'td',
                null,
                this.state.food.details.other && Math.round(this.state.food.details.other.weight / this.state.food.details.weight * 100),
                ' %'
              ),
              React.createElement('td', null)
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h1',
            { className: 'ui header', style: { textAlign: 'center' } },
            'Ingredients'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'table',
            { className: 'ui segment celled table', style: { width: '100%' } },
            React.createElement(
              'thead',
              null,
              React.createElement(
                'tr',
                null,
                React.createElement(
                  'th',
                  null,
                  'Name'
                ),
                React.createElement(
                  'th',
                  null,
                  Constants.value.weight.name
                ),
                React.createElement(
                  'th',
                  null,
                  'Measure'
                ),
                React.createElement(
                  'th',
                  null,
                  Constants.value.energy.name
                ),
                React.createElement(
                  'th',
                  null,
                  Constants.macro.protein.name
                ),
                React.createElement(
                  'th',
                  null,
                  Constants.macro.carb.name
                ),
                React.createElement(
                  'th',
                  null,
                  Constants.macro.fat.name
                )
              )
            ),
            React.createElement(
              'tbody',
              null,
              this.state.food.inclusions.map(function (inclusion, index) {
                return React.createElement(
                  'tr',
                  { key: index },
                  React.createElement(
                    'td',
                    null,
                    React.createElement(
                      'a',
                      { href: '/ingredient/' + inclusion.ingredient.id },
                      inclusion.name
                    )
                  ),
                  React.createElement(
                    'td',
                    null,
                    Number(inclusion.value).toFixed(2),
                    ' ',
                    inclusion.unit
                  ),
                  React.createElement(
                    'td',
                    null,
                    Number(inclusion.ingredient.measureValue).toFixed(2),
                    ' ',
                    inclusion.ingredient.measureUnit
                  ),
                  React.createElement(
                    'td',
                    null,
                    Number(inclusion.ingredient.energy).toFixed(2),
                    ' kcal'
                  ),
                  React.createElement(
                    'td',
                    null,
                    Number(inclusion.ingredient.protein).toFixed(2),
                    ' g'
                  ),
                  React.createElement(
                    'td',
                    null,
                    Number(inclusion.ingredient.carb).toFixed(2),
                    ' g'
                  ),
                  React.createElement(
                    'td',
                    null,
                    Number(inclusion.ingredient.fat).toFixed(2),
                    ' g'
                  )
                );
              })
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h1',
            { className: 'ui header', style: { textAlign: 'center' } },
            'Semantic Tags'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'ul',
            null,
            this.state.food.tags && this.state.food.tags.map(function (tag, index) {
              return React.createElement(
                'div',
                { key: index, className: 'item', style: { marginBottom: 10 } },
                React.createElement(
                  'div',
                  { className: 'content' },
                  React.createElement(
                    'div',
                    { className: 'header', style: { fontSize: 16 } },
                    tag.name
                  )
                )
              );
            })
          )
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
        this.state.food.comments && React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(_Comments2.default, { getComments: this.getComments, id: this.state.id, comment: this.comment })
        )
      );
    }
  }]);

  return FoodPage;
}(React.Component);

exports.default = FoodPage;