'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _MultipleSelect = require('service/MultipleSelect.js');

var _MultipleSelect2 = _interopRequireDefault(_MultipleSelect);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var MyDiet = function (_React$Component) {
  _inherits(MyDiet, _React$Component);

  function MyDiet(props) {
    _classCallCheck(this, MyDiet);

    var _this = _possibleConstructorReturn(this, (MyDiet.__proto__ || Object.getPrototypeOf(MyDiet)).call(this, props));

    _this.state = {};

    _this.fetch = _this.fetch.bind(_this);
    _this.dietsSelected = _this.dietsSelected.bind(_this);
    _this.addDiets = _this.addDiets.bind(_this);
    return _this;
  }

  _createClass(MyDiet, [{
    key: 'fetch',
    value: function fetch() {
      Api.getMyDiets().then(function (data) {
        console.log(data);
      }).catch(function (error) {
        console.log(error);
      });
    }
  }, {
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetch();
    }
  }, {
    key: 'setDietSelectionOptions',
    value: function setDietSelectionOptions() {
      var self = this;
      Api.getDiets().then(function (data) {
        self.setState({ options: data });
      }).catch(function (error) {
        self.setState({ errors: error });
      });
    }
  }, {
    key: 'openAddDietModal',
    value: function openAddDietModal() {
      $('#addDietModal').modal('show');
    }
  }, {
    key: 'openSuccessModal',
    value: function openSuccessModal() {
      $('#addDietSuccess').modal('show');
    }
  }, {
    key: 'dietsSelected',
    value: function dietsSelected(diets) {
      this.setState({ diets: diets });
      console.log(diets);
    }
  }, {
    key: 'addDiets',
    value: function addDiets(event) {
      var _this2 = this;

      var promises = [];
      this.state.diets.forEach(function (diet, index) {
        promises.push(Api.addDiet(diet));
      });
      console.log(promises);
      Promise.all(promises).then(function () {
        console.log('yay o/');
        _this2.openSuccessModal();
      }).catch(function (error) {
        console.log(error);
        _this2.setState({ errors: error.data });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'ui segment' },
        React.createElement(
          'div',
          null,
          React.createElement(
            'div',
            null,
            React.createElement(
              'button',
              { className: 'ui button', type: 'button', onClick: this.openAddDietModal },
              'Add diet'
            ),
            React.createElement(
              'div',
              { id: 'addDietSuccess', className: 'ui small modal' },
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
                  'The diet has been added to your diets section'
                )
              )
            ),
            React.createElement(
              'div',
              { id: 'addDietModal', className: 'ui small modal' },
              React.createElement('i', { className: 'close icon' }),
              React.createElement(
                'div',
                { className: 'header' },
                'Select diets to add'
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
                    React.createElement(_MultipleSelect2.default, { onChange: this.dietsSelected, setOptions: this.setDietSelectionOptions, name: 'diets', placeholder: 'Select diets' })
                  ),
                  React.createElement(
                    'button',
                    { className: 'ui button', type: 'button', style: { width: '100%' }, onClick: this.addDiets },
                    'Submit'
                  )
                )
              )
            )
          )
        )
      );
    }
  }]);

  return MyDiet;
}(React.Component);

exports.default = MyDiet;