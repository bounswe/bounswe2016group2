'use strict';

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

var SignUpModal = function (_React$Component2) {
  _inherits(SignUpModal, _React$Component2);

  function SignUpModal(props) {
    _classCallCheck(this, SignUpModal);

    var _this3 = _possibleConstructorReturn(this, (SignUpModal.__proto__ || Object.getPrototypeOf(SignUpModal)).call(this, props));

    _this3.state = {
      data: null,
      errors: null
    };
    _this3.first_nameChanged = _this3.first_nameChanged.bind(_this3);
    _this3.last_nameChanged = _this3.last_nameChanged.bind(_this3);
    _this3.emailChanged = _this3.emailChanged.bind(_this3);
    _this3.passwordChanged = _this3.passwordChanged.bind(_this3);
    _this3.submit = _this3.submit.bind(_this3);
    return _this3;
  }

  _createClass(SignUpModal, [{
    key: 'first_nameChanged',
    value: function first_nameChanged(e) {
      this.setState({ first_name: e.target.value });
    }
  }, {
    key: 'last_nameChanged',
    value: function last_nameChanged(e) {
      this.setState({ last_name: e.target.value });
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
      var _this4 = this;

      e.preventDefault();
      this.setState({ errors: null });
      var postData = {
        email: this.state.email,
        password: this.state.password,
        first_name: this.state.first_name,
        last_name: this.state.last_name
      };
      Api.signup(postData).then(function (data) {
        Api.signin(postData).then(function (data) {
          reactCookie.save('token', data.token);
          reactCookie.save('email', postData.email);
          window.location.href = '/';
        });
      }).catch(function (err) {
        _this4.setState({ errors: err.data });
      });
    }
  }, {
    key: 'render',
    value: function render() {
      var formClassName = 'ui form';
      if (this.state.errors) formClassName += ' error';
      return React.createElement(
        'div',
        { id: 'signUpModal', className: 'ui small modal' },
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
                'First name'
              ),
              React.createElement('input', { type: 'text', name: 'firstName', placeholder: 'First name', value: this.state.first_name, onChange: this.first_nameChanged })
            ),
            React.createElement(
              'div',
              { className: 'field' },
              React.createElement(
                'label',
                null,
                'Last name'
              ),
              React.createElement('input', { type: 'text', name: 'lastName', placeholder: 'Last name', value: this.state.last_name, onChange: this.last_nameChanged })
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

  return SignUpModal;
}(React.Component);

var NavbarUser = function (_React$Component3) {
  _inherits(NavbarUser, _React$Component3);

  function NavbarUser(props) {
    _classCallCheck(this, NavbarUser);

    var _this5 = _possibleConstructorReturn(this, (NavbarUser.__proto__ || Object.getPrototypeOf(NavbarUser)).call(this, props));

    _this5.state = { userEmail: props.userEmail };
    _this5.openSigninModal = _this5.openSigninModal.bind(_this5);
    _this5.openSignupModal = _this5.openSignupModal.bind(_this5);
    _this5.signout = _this5.signout.bind(_this5);
    return _this5;
  }

  _createClass(NavbarUser, [{
    key: 'openSigninModal',
    value: function openSigninModal() {
      $('#signInModal.ui.modal').modal('show');
    }
  }, {
    key: 'openSignupModal',
    value: function openSignupModal() {
      $('#signUpModal.ui.modal').modal('show');
    }
  }, {
    key: 'signout',
    value: function signout() {
      reactCookie.remove('email');
      reactCookie.remove('token');
      window.location.href = '/';
    }
  }, {
    key: 'render',
    value: function render() {
      if (this.state.userEmail) {
        return React.createElement(
          'div',
          { className: 'right menu' },
          React.createElement(
            'a',
            { className: 'item' },
            userEmail
          ),
          React.createElement(
            'a',
            { className: 'item', onClick: this.signout },
            'Sign Out'
          )
        );
      } else {
        return React.createElement(
          'div',
          { className: 'right menu' },
          React.createElement(SignInModal, null),
          React.createElement(
            'a',
            { className: 'item', onClick: this.openSigninModal },
            'Sign In'
          ),
          React.createElement(SignUpModal, null),
          React.createElement(
            'a',
            { className: 'item', onClick: this.openSignupModal },
            'Sign Up'
          )
        );
      }
    }
  }]);

  return NavbarUser;
}(React.Component);