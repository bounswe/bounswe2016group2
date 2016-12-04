'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var SignUpServerModal = function (_React$Component) {
  _inherits(SignUpServerModal, _React$Component);

  function SignUpServerModal(props) {
    _classCallCheck(this, SignUpServerModal);

    var _this = _possibleConstructorReturn(this, (SignUpServerModal.__proto__ || Object.getPrototypeOf(SignUpServerModal)).call(this, props));

    _this.state = {
      data: null,
      errors: null
    };
    _this.server_nameChanged = _this.server_nameChanged.bind(_this);
    _this.server_phoneNumberChanged = _this.server_phoneNumberChanged.bind(_this);
    _this.emailChanged = _this.emailChanged.bind(_this);
    _this.passwordChanged = _this.passwordChanged.bind(_this);
    _this.submit = _this.submit.bind(_this);
    return _this;
  }

  _createClass(SignUpServerModal, [{
    key: 'server_nameChanged',
    value: function server_nameChanged(e) {
      this.setState({ server_name: e.target.value });
    }
  }, {
    key: 'server_phoneNumberChanged',
    value: function server_phoneNumberChanged(e) {
      this.setState({ server_phoneNumber: e.target.value });
    }
  }, {
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
      this.setState({ errors: null });
      var postData = {
        email: this.state.email,
        password: this.state.password,
        server_name: this.state.server_name,
        server_phoneNumber: this.state.server_phoneNumber
      };

      Api.signup(postData).then(function (data) {
        Api.signin(postData).then(function (data) {
          reactCookie.save('token', data.token);
          reactCookie.save('email', postData.email);
          window.location.href = '/';
        });
      }).catch(function (err) {
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
        { id: 'signUpServerModal', className: 'ui small modal' },
        React.createElement('i', { className: 'close icon' }),
        React.createElement(
          'div',
          { className: 'header' },
          'Sign Up'
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
                'Server name'
              ),
              React.createElement('input', { type: 'text', name: 'serverName', placeholder: 'Server name', value: this.state.server_name, onChange: this.server_nameChanged })
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Phone Number'
              ),
              React.createElement('input', { type: 'text', name: 'phoneNumber', placeholder: 'Phone Number', value: this.state.server_phoneNumber, onChange: this.server_phoneNumberChanged })
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'E-mail'
              ),
              React.createElement('input', { type: 'text', name: 'email', placeholder: 'E-mail', value: this.state.email, onChange: this.emailChanged }),
              this.state.errors && this.state.errors.email && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.email[0]
                )
              )
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Password'
              ),
              React.createElement('input', { type: 'password', name: 'password', placeholder: 'Password', value: this.state.password, onChange: this.passwordChanged }),
              this.state.errors && this.state.errors.password && React.createElement(
                'div',
                { className: 'ui error message' },
                React.createElement(
                  'p',
                  null,
                  this.state.errors.password[0]
                )
              )
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Repeat password'
              ),
              React.createElement('input', { type: 'password', name: 'repeatPassword', placeholder: 'Password' })
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

  return SignUpServerModal;
}(React.Component);