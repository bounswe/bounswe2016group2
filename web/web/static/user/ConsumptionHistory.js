'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _DatePicker = require('service/DatePicker.js');

var _DatePicker2 = _interopRequireDefault(_DatePicker);

var _FoodRow = require('food/FoodRow.js');

var _FoodRow2 = _interopRequireDefault(_FoodRow);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ConsumptionHistory = function (_React$Component) {
  _inherits(ConsumptionHistory, _React$Component);

  function ConsumptionHistory(props) {
    _classCallCheck(this, ConsumptionHistory);

    var _this = _possibleConstructorReturn(this, (ConsumptionHistory.__proto__ || Object.getPrototypeOf(ConsumptionHistory)).call(this, props));

    _this.state = {
      data: {
        total: {},
        daily: []
      }
    };

    _this.input = {
      fromDate: moment().subtract(1, 'month').format('DD-MM-YYYY'),
      toDate: moment().format('DD-MM-YYYY')
    };

    _this.fetch = _this.fetch.bind(_this);
    return _this;
  }

  _createClass(ConsumptionHistory, [{
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch();
    }
  }, {
    key: 'fetch',
    value: function fetch() {
      var _this2 = this;

      Api.consumptionHistory().then(function (data) {
        console.log(data);
        _this2.setState({ data: data });
      }).catch(function (error) {
        console.log(error);
      });
    }
  }, {
    key: 'fromDateChanged',
    value: function fromDateChanged(e) {
      this.input.fromDate = moment(e).format('DD-MM-YYYY');
    }
  }, {
    key: 'toDateChanged',
    value: function toDateChanged(e) {
      this.input.toDate = moment(e).format('DD-MM-YYYY');
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'ui segment' },
        React.createElement(
          'div',
          { style: { display: 'flex', alignItems: 'center' } },
          React.createElement(
            'span',
            { style: { marginLeft: 10, marginRight: 10 } },
            'From'
          ),
          React.createElement(_DatePicker2.default, { name: 'consumptionStartDate', placeholder: 'Start Date', 'default': this.input.fromDate, onChange: this.fromDateChanged.bind(this) }),
          React.createElement(
            'span',
            { style: { marginLeft: 10, marginRight: 10 } },
            'to'
          ),
          React.createElement(_DatePicker2.default, { name: 'consumptionEndDate', placeholder: 'End Date', 'default': this.input.toDate, onChange: this.toDateChanged.bind(this) }),
          React.createElement(
            'button',
            { className: 'ui button', style: { marginLeft: 10, marginRight: 10 } },
            'Refresh'
          )
        ),
        React.createElement(
          'div',
          { style: { marginTop: 20 } },
          this.state.data.daily.map(function (dailyData) {
            return React.createElement(
              'div',
              { key: dailyData.date },
              React.createElement(
                'h3',
                null,
                dailyData.date
              ),
              dailyData.ateFoods.map(function (ateFood) {
                return React.createElement(_FoodRow2.default, { key: ateFood.created, data: ateFood.food });
              })
            );
          })
        )
      );
    }
  }]);

  return ConsumptionHistory;
}(React.Component);

exports.default = ConsumptionHistory;