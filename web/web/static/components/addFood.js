'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var AddFood = function (_React$Component) {
  _inherits(AddFood, _React$Component);

  function AddFood(props) {
    _classCallCheck(this, AddFood);

    var _this = _possibleConstructorReturn(this, (AddFood.__proto__ || Object.getPrototypeOf(AddFood)).call(this, props));

    _this.state = {
      data: null,
      errors: null
    };
    _this.nameChanged = _this.nameChanged.bind(_this);
    _this.slugChanged = _this.slugChanged.bind(_this);
    _this.submit = _this.submit.bind(_this);
    return _this;
  }

  _createClass(AddFood, [{
    key: 'nameChanged',
    value: function nameChanged(e) {
      this.setState({ name: e.target.value });
    }
  }, {
    key: 'slugChanged',
    value: function slugChanged(e) {
      this.setState({ slug: e.target.value });
    }
  }, {
    key: 'submit',
    value: function submit(e) {
      var _this2 = this;

      e.preventDefault();
      this.setState({ errors: null });
      var postData = {
        name: this.state.name,
        slug: this.state.slug
      };
      Api.addFood(postData).then(function (data) {}).catch(function (err) {
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
        { id: 'addFoodForm' },
        React.createElement(
          'div',
          { className: 'header' },
          'Add New Food'
        ),
        React.createElement(
          'div',
          { className: 'content' },
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
              React.createElement('input', { type: 'text', name: 'name', placeholder: 'name', value: this.state.name, onChange: this.nameChanged }),
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
                ' Slug '
              ),
              React.createElement('input', { type: 'text', name: 'slug', placeholder: 'slug', value: this.state.slug, onChange: this.slugChanged }),
              this.state.errors && this.state.errors.slug && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.slug[0]
                )
              )
            ),
            React.createElement(
              'button',
              { className: 'ui button', type: 'submit', style: { width: '100%' }, onClick: this.submit },
              'Add Food'
            )
          )
        )
      );
    }
  }]);

  return AddFood;
}(React.Component);