'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var SignInModal = function (_React$Component) {
  _inherits(SignInModal, _React$Component);

  function SignInModal(props) {
    _classCallCheck(this, SignInModal);

    var _this = _possibleConstructorReturn(this, (SignInModal.__proto__ || Object.getPrototypeOf(SignInModal)).call(this, props));

    _this.state = {};
    _this.emailChanged = _this.emailChanged.bind(_this);
    _this.passwordChanged = _this.passwordChanged.bind(_this);
    _this.submit = _this.submit.bind(_this);
    return _this;
  }

  _createClass(SignInModal, [{
    key: 'emailChanged',
    value: function emailChanged(e) {
      this.setState({ email: e.target.value });
    }
  }, {
    key: 'passwordChanged',
    value: function passwordChanged(e) {
      this.setState({ password: e.target.value });
    }
  }, {
    key: 'submit',
    value: function submit(e) {
      var _this2 = this;

      e.preventDefault();
      this.setState({ error: false });
      var postData = {
        email: this.state.email,
        password: this.state.password
      };
      Api.signin(postData).then(function (data) {
        reactCookie.save('token', data.token);
        reactCookie.save('email', postData.email);
        window.location.href = '/';
      }).catch(function (err) {
        _this2.setState({ error: true });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var formClassName = 'ui form';
      if (this.state.error) formClassName += ' error';
      return React.createElement(
        'div',
        { id: 'signInModal', className: 'ui small modal' },
        React.createElement('i', { className: 'close icon' }),
        React.createElement(
          'div',
          { className: 'header' },
          'Sign In'
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
                'E-mail'
              ),
              React.createElement('input', { type: 'text', name: 'email', placeholder: 'E-mail', value: this.state.email, onChange: this.emailChanged })
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Password'
              ),
              React.createElement('input', { type: 'password', name: 'password', placeholder: 'Password', value: this.state.password, onChange: this.passwordChanged })
            ),
            React.createElement(
              'div',
              { className: 'ui error message' },
              React.createElement(
                'p',
                null,
                'Invalid email or password'
              )
            ),
            React.createElement(
              'button',
              { className: 'ui button', type: 'submit', style: { width: '100%' }, onClick: this.submit },
              'Submit'
            )
          )
        )
      );
    }
  }]);

  return SignInModal;
}(React.Component);

exports.default = SignInModal;