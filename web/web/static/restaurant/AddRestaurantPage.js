'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _MultipleSelect = require('../service/MultipleSelect.js');

var _MultipleSelect2 = _interopRequireDefault(_MultipleSelect);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var AddFoodPage = function (_React$Component) {
  _inherits(AddFoodPage, _React$Component);

  function AddFoodPage(props) {
    _classCallCheck(this, AddFoodPage);

    var _this = _possibleConstructorReturn(this, (AddFoodPage.__proto__ || Object.getPrototypeOf(AddFoodPage)).call(this, props));

    _this.state = {
      errors: null,
      foods: []
    };
    return _this;
  }

  _createClass(AddFoodPage, [{
    key: 'setFoodSelectOptions',
    value: function setFoodSelectOptions() {
      var self = this;
      Api.me().then(function (data) {
        self.setState({
          options: data.foods
        });
      });
    }
  }, {
    key: 'submit',
    value: function submit(e) {
      var _this2 = this;

      e.preventDefault();
      var postData = {
        name: this.state.name,
        photo: this.state.photo,
        description: this.state.description
      };
      Api.addRestaurant(postData).then(function (data) {
        console.log(data);
        var foodPromises = [];
        var restaurantId = data.id;
        _this2.state.foods.forEach(function (food, index) {
          foodPromises.push(Api.addFoodToRestaurant(restaurantId, food));
        });

        // Evenly, it's possible to use .catch
        Promise.all(foodPromises).then(function () {
          router.navigate('../restaurants/' + data.id);
        }).catch(function (reason) {
          console.log('error', reason);
          router.navigate('../restaurants/' + data.id);
        });
      }).catch(function (err) {
        console.log(err);
        _this2.setState({ errors: err.data });
      });
    }
  }, {
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
    key: 'photoChanged',
    value: function photoChanged(event) {
      this.setState({
        photo: event.target.value
      });
    }
  }, {
    key: 'foodsChanged',
    value: function foodsChanged(foods) {
      this.setState({
        foods: foods
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
          'Add New Restaurant'
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
                'Name'
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
                'Description'
              ),
              React.createElement('input', { type: 'text', name: 'description', placeholder: 'description', value: this.state.description, onChange: this.descriptionChanged.bind(this) }),
              this.state.errors && this.state.errors.description && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.description[0]
                )
              )
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Photo'
              ),
              React.createElement('input', { type: 'url', name: 'photo', placeholder: 'image url', value: this.state.photo, onChange: this.photoChanged.bind(this) })
            ),
            this.state.errors && this.state.errors.photo && React.createElement(
              'div',
              { className: 'ui error message' },
              React.createElement(
                'p',
                null,
                this.state.errors.photo[0]
              )
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Foods'
              ),
              React.createElement(_MultipleSelect2.default, { onChange: this.foodsChanged.bind(this), setOptions: this.setFoodSelectOptions, name: 'foods', placeholder: 'Select foods' })
            ),
            this.state.errors && this.state.errors.foods && React.createElement(
              'div',
              { className: 'ui error message' },
              React.createElement(
                'p',
                null,
                this.state.errors.foods[0]
              )
            )
          )
        ),
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

  return AddFoodPage;
}(React.Component);

exports.default = AddFoodPage;