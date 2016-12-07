'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _IntervalSelection = require('diet/IntervalSelection.js');

var _IntervalSelection2 = _interopRequireDefault(_IntervalSelection);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var CreateDietPage = function (_React$Component) {
  _inherits(CreateDietPage, _React$Component);

  function CreateDietPage(props) {
    _classCallCheck(this, CreateDietPage);

    var _this = _possibleConstructorReturn(this, (CreateDietPage.__proto__ || Object.getPrototypeOf(CreateDietPage)).call(this, props));

    _this.state = {
      data: null,
      errors: null,
      ingredients: []
    };

    _this.energy = {};
    _this.proteinVal = {};
    _this.carbVal = {};
    _this.fatVal = {};
    _this.proteinRate = {};
    _this.carbRate = {};
    _this.fatRate = {};
    return _this;
  }

  _createClass(CreateDietPage, [{
    key: 'nameChanged',
    value: function nameChanged(event) {
      this.setState({
        name: event.target.value
      });
    }
  }, {
    key: 'descriptionChanged',
    value: function descriptionChanged(event) {
      this.setState({
        description: event.target.value
      });
    }
  }, {
    key: 'submit',
    value: function submit(event) {
      var _this2 = this;

      event.preventDefault();
      this.setState({ errors: null });

      var postData = {
        name: this.state.name,
        description: this.state.description,
        minEnergy: this.energy.min,
        maxEnergy: this.energy.max,
        minProteinVal: this.proteinVal.min,
        maxProteinVal: this.proteinVal.max,
        minCarbVal: this.carbVal.min,
        maxCarbVal: this.carbVal.max,
        minFatVal: this.fatVal.min,
        maxFatVal: this.fatVal.max,
        minProteinRate: this.proteinRate.min,
        maxProteinRate: this.proteinRate.max,
        minCarbRate: this.carbRate.min,
        maxCarbRate: this.carbRate.max,
        minFatRate: this.fatRate.min,
        maxFatRate: this.fatRate.max,
        ingredients: this.state.ingredients
      };

      Api.createDiet(postData).then(function (data) {
        console.log(data);
      }).catch(function (err) {
        console.log(err);
        _this2.setState({ errors: err.data });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var formClassName = 'ui form';
      if (this.state.errors) formClassName += ' error';
      return React.createElement(
        'div',
        { className: 'ui segments' },
        React.createElement(
          'div',
          { className: 'ui segment' },
          'Create a New Diet'
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'form',
            { className: formClassName },
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                ' Name '
              ),
              React.createElement('input', { type: 'text', name: 'name', placeholder: 'name', value: this.state.name, onChange: this.nameChanged.bind(this) }),
              this.state.errors && this.state.errors.name && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.name[0]
                )
              )
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                ' Description '
              ),
              React.createElement('input', { type: 'text', name: 'description', placeholder: 'description', value: this.state.description, onChange: this.descriptionChanged.bind(this) })
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          'Minimum and Maximum Values'
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(_IntervalSelection2.default, { label: 'Energy', unit: 'kcal', variable: this.energy }),
          React.createElement(_IntervalSelection2.default, { label: 'Protein', unit: 'g', variable: this.proteinVal }),
          React.createElement(_IntervalSelection2.default, { label: 'Carbohydrate', unit: 'g', variable: this.carbVal }),
          React.createElement(_IntervalSelection2.default, { label: 'Fat', unit: 'g', variable: this.fatVal })
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          'Minimum and Maximum Rates'
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(_IntervalSelection2.default, { label: 'Protein', unit: '', variable: this.proteinRate }),
          React.createElement(_IntervalSelection2.default, { label: 'Carbohydrate', unit: '', variable: this.carbRate }),
          React.createElement(_IntervalSelection2.default, { label: 'Fat', unit: '', variable: this.fatRate })
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          'Selected Ingredients'
        ),
        React.createElement('div', { className: 'ui segment' }),
        React.createElement(
          'div',
          { className: 'ui segment', style: { textAlign: 'center' } },
          React.createElement(
            'button',
            { className: 'ui button', type: 'submit', onClick: this.submit.bind(this) },
            'Submit'
          )
        )
      );
    }
  }]);

  return CreateDietPage;
}(React.Component);

exports.default = CreateDietPage;