'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _MultipleSelect = require('service/MultipleSelect.js');

var _MultipleSelect2 = _interopRequireDefault(_MultipleSelect);

var _DietRow = require('diet/DietRow.js');

var _DietRow2 = _interopRequireDefault(_DietRow);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var MyDiet = function (_React$Component) {
  _inherits(MyDiet, _React$Component);

  function MyDiet(props) {
    _classCallCheck(this, MyDiet);

    var _this = _possibleConstructorReturn(this, (MyDiet.__proto__ || Object.getPrototypeOf(MyDiet)).call(this, props));

    _this.state = {
      myDiets: [],
      addDiets: [],
      removeDiets: []
    };

    _this.fetchMyDiets = _this.fetchMyDiets.bind(_this);
    _this.addDietsSelected = _this.addDietsSelected.bind(_this);
    _this.removeDietsSelected = _this.removeDietsSelected.bind(_this);
    _this.openAddDietModal = _this.openAddDietModal.bind(_this);
    _this.openRemoveDietModal = _this.openRemoveDietModal.bind(_this);
    _this.addDiets = _this.addDiets.bind(_this);
    _this.removeDiets = _this.removeDiets.bind(_this);
    _this.navigateToCreateDietPage = _this.navigateToCreateDietPage.bind(_this);
    return _this;
  }

  _createClass(MyDiet, [{
    key: 'fetchMyDiets',
    value: function fetchMyDiets() {
      var _this2 = this;

      Api.getMyDiets().then(function (data) {
        _this2.setState({ myDiets: data });
      }).catch(function (error) {
        _this2.setState({ errors: error });
      });
    }
  }, {
    key: 'componentWillMount',
    value: function componentWillMount() {
      this.fetchMyDiets();
    }
  }, {
    key: 'addDietOptions',
    value: function addDietOptions() {
      var self = this;
      Api.getDiets().then(function (data) {
        self.setState({ options: data });
      }).catch(function (error) {
        self.setState({ errors: error });
      });
    }
  }, {
    key: 'removeDietOptions',
    value: function removeDietOptions() {
      var self = this;
      Api.getMyDiets().then(function (data) {
        self.setState({ options: data });
      }).catch(function (error) {
        self.setState({ errors: error.data });
      });
    }
  }, {
    key: 'openAddDietModal',
    value: function openAddDietModal() {
      $('#addDietModal').modal('show');
    }
  }, {
    key: 'openRemoveDietModal',
    value: function openRemoveDietModal() {
      $('#removeDietModal').modal('show');
    }
  }, {
    key: 'addDietsSelected',
    value: function addDietsSelected(addDiets) {
      this.setState({ addDiets: addDiets });
    }
  }, {
    key: 'removeDietsSelected',
    value: function removeDietsSelected(removeDiets) {
      this.setState({ removeDiets: removeDiets });
    }
  }, {
    key: 'addDiets',
    value: function addDiets(event) {
      var _this3 = this;

      var dietList = this.state.addDiets.map(function (diet, index) {
        return parseInt(diet);
      });
      var promises = [];
      dietList.forEach(function (diet, index) {
        promises.push(Api.addDiet(diet));
      });
      Promise.all(promises).then(function (data) {
        _this3.fetchMyDiets();
        $('#addDietSuccess').modal('show');
      }).catch(function (error) {
        _this3.setState({ errors: error.data });
        _this3.fetchMyDiets();
      });
    }
  }, {
    key: 'removeDiets',
    value: function removeDiets(event) {
      var _this4 = this;

      var dietList = this.state.removeDiets.map(function (diet, index) {
        return parseInt(diet);
      });
      var promises = [];
      dietList.forEach(function (diet, index) {
        promises.push(Api.removeDiet(diet));
      });
      Promise.all(promises).then(function (data) {
        _this4.fetchMyDiets();
        $('#removeDietSuccess').modal('show');
      }).catch(function (error) {
        _this4.setState({ errors: error.data });
        _this4.fetchMyDiets();
      });
    }
  }, {
    key: 'navigateToCreateDietPage',
    value: function navigateToCreateDietPage() {
      router.navigate('../createDiet');
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
            'button',
            { className: 'ui button', type: 'button', onClick: this.navigateToCreateDietPage },
            'Create diet'
          ),
          React.createElement(
            'button',
            { className: 'ui button', type: 'button', onClick: this.openAddDietModal },
            'Add diet'
          ),
          React.createElement(
            'button',
            { className: 'ui button', type: 'button', onClick: this.openRemoveDietModal },
            'Remove diet'
          )
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
              'Diets has been added to your diets section'
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
                React.createElement(_MultipleSelect2.default, { onChange: this.addDietsSelected, setOptions: this.addDietOptions, name: 'addDiets', placeholder: 'Select diets' })
              ),
              React.createElement(
                'button',
                { className: 'ui button', type: 'button', style: { width: '100%' }, onClick: this.addDiets },
                'Submit'
              )
            )
          )
        ),
        React.createElement(
          'div',
          { id: 'removeDietSuccess', className: 'ui small modal' },
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
              'Diets has been removed from your diets section'
            )
          )
        ),
        React.createElement(
          'div',
          { id: 'removeDietModal', className: 'ui small modal' },
          React.createElement('i', { className: 'close icon' }),
          React.createElement(
            'div',
            { className: 'header' },
            'Select diets to remove'
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
                React.createElement(_MultipleSelect2.default, { onChange: this.removeDietsSelected, setOptions: this.removeDietOptions, name: 'removeDiets', placeholder: 'Select diets' })
              ),
              React.createElement(
                'button',
                { className: 'ui button', type: 'button', style: { width: '100%' }, onClick: this.removeDiets },
                'Submit'
              )
            )
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'h2',
            { className: 'ui header', style: { textAlign: 'center' } },
            'My Diets'
          )
        ),
        React.createElement(
          'div',
          { className: 'ui segment' },
          React.createElement(
            'div',
            { className: 'ui relaxed list' },
            this.state.myDiets.map(function (diet, index) {
              return React.createElement(_DietRow2.default, { key: index, data: diet });
            })
          )
        )
      );
    }
  }]);

  return MyDiet;
}(React.Component);

exports.default = MyDiet;