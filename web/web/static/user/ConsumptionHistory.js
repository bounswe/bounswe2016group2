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
    _this.updateDailyCharts = _this.updateDailyCharts.bind(_this);
    return _this;
  }

  _createClass(ConsumptionHistory, [{
    key: 'componentDidMount',
    value: function componentDidMount() {
      this.fetch();
    }
  }, {
    key: 'updateDailyCharts',
    value: function updateDailyCharts() {
      // console.log(this.state.data);
      var dailyWeight = [];
      var dailyEnergy = [];
      var dailyProtein = [];
      var dailyCarb = [];
      var dailyFat = [];
      var dailyMicro = {
        saturatedFat: [],
        sugar: [],
        fibre: [],
        cholesterol: [],
        calcium: [],
        iron: [],
        sodium: [],
        potassium: [],
        magnesium: [],
        phosphorus: [],
        thiamin: [],
        riboflavin: [],
        niacin: [],
        folate: []
      };
      this.state.data.daily.forEach(function (dailyData) {
        var date = moment(dailyData.date, 'DD-MM-YYYY').toDate();
        dailyWeight.push({ x: date, y: dailyData.weight });
        dailyEnergy.push({ x: date, y: dailyData.energy });
        dailyProtein.push({ x: date, y: dailyData.protein.weight });
        dailyCarb.push({ x: date, y: dailyData.carb.weight });
        dailyFat.push({ x: date, y: dailyData.fat.weight });
        Object.keys(dailyMicro).forEach(function (key) {
          dailyMicro[key].push({ x: date, y: dailyData.others[key] });
        });
      });
      Highcharts.chart('dailyMacroChart', {
        chart: {
          height: 300,
          spacingTop: 40
        },
        title: {
          text: 'Macronutrients'
        },
        tooltip: {
          shared: true,
          valueDecimals: 2,
          valueSuffix: ' g'
        },
        xAxis: {
          type: 'datetime'
        },
        series: [{ type: 'line', name: 'Protein', data: dailyProtein }, { type: 'line', name: 'Carbonhydrate', data: dailyCarb }, { type: 'line', name: 'Fat', data: dailyFat }, { type: 'line', name: 'Total weight', data: dailyWeight }]
      });
      Highcharts.chart('dailyEnergyChart', {
        chart: {
          height: 300,
          spacingTop: 40
        },
        title: {
          text: 'Energy'
        },
        legend: {
          enabled: false
        },
        tooltip: {
          shared: true,
          valueDecimals: 0,
          valueSuffix: ' kcal'
        },
        xAxis: {
          type: 'datetime'
        },
        series: [{ type: 'line', name: 'Energy', data: dailyEnergy }]
      });
      Highcharts.chart('dailyMicroChart', {
        chart: {
          height: 300,
          spacingTop: 40
        },
        title: {
          text: 'Micronutrients'
        },
        legend: {
          enabled: false
        },
        tooltip: {
          shared: true,
          valueDecimals: 2
        },
        xAxis: {
          type: 'datetime'
        },
        series: [{ type: 'line', tooltip: { valueSuffix: ' g' }, name: 'Saturated fat', data: dailyMicro.saturatedFat }, { type: 'line', tooltip: { valueSuffix: ' g' }, name: 'Sugar', data: dailyMicro.sugar }, { type: 'line', tooltip: { valueSuffix: ' g' }, name: 'Fibre', data: dailyMicro.fibre }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Cholesterol', data: dailyMicro.cholesterol }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Calcium', data: dailyMicro.calcium }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Iron', data: dailyMicro.iron }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Sodium', data: dailyMicro.sodium }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Potassium', data: dailyMicro.potassium }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Magnesium', data: dailyMicro.magnesium }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Phosphorus', data: dailyMicro.phosphorus }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Thiamin', data: dailyMicro.thiamin }, { type: 'line', tooltip: { valueSuffix: ' mg' }, name: 'Riboflavin', data: dailyMicro.riboflavin }, { type: 'line', tooltip: { valueSuffix: ' NE' }, name: 'Niacin', data: dailyMicro.niacin }, { type: 'line', tooltip: { valueSuffix: ' DFE' }, name: 'Folate', data: dailyMicro.folate }]
      });
    }
  }, {
    key: 'fetch',
    value: function fetch() {
      var _this2 = this;

      Api.consumptionHistory().then(function (data) {
        data.daily = data.daily.sort(function (a, b) {
          return moment(a.date, 'DD-MM-YYYY').unix() - moment(b.date, 'DD-MM-YYYY').unix();
        });
        _this2.setState({ data: data });
        _this2.updateDailyCharts();
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
          null,
          React.createElement('div', { id: 'dailyMacroChart' }),
          React.createElement('div', { id: 'dailyEnergyChart' }),
          React.createElement('div', { id: 'dailyMicroChart' })
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