"use strict";

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var SignInModal = function (_React$Component) {
  _inherits(SignInModal, _React$Component);

  function SignInModal(props) {
    _classCallCheck(this, SignInModal);

    var _this = _possibleConstructorReturn(this, (SignInModal.__proto__ || Object.getPrototypeOf(SignInModal)).call(this, props));

    _this.data = {};
    _this.changed = _this.changed.bind(_this);
    _this.submit = _this.submit.bind(_this);
    return _this;
  }

  _createClass(SignInModal, [{
    key: "changed",
    value: function changed(e) {
      this.data[e.target.name] = e.target.value;
    }
  }, {
    key: "submit",
    value: function submit(e) {
      e.preventDefault();
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { id: "signInModal", className: "ui small modal" },
        React.createElement("i", { className: "close icon" }),
        React.createElement(
          "div",
          { className: "header" },
          "Sign In"
        ),
        React.createElement(
          "div",
          { className: "content" },
          React.createElement(
            "form",
            { className: "ui form" },
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "E-mail"
              ),
              React.createElement("input", { type: "text", name: "email", placeholder: "E-mail", value: this.data.email, onChange: this.changed })
            ),
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "Password"
              ),
              React.createElement("input", { type: "password", name: "password", placeholder: "Password", value: this.data.password, onChange: this.changed })
            ),
            React.createElement(
              "button",
              { className: "ui button", type: "submit", style: { width: '100%' }, onClick: this.submit },
              "Submit"
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

    var _this2 = _possibleConstructorReturn(this, (SignUpModal.__proto__ || Object.getPrototypeOf(SignUpModal)).call(this, props));

    _this2.data = {};
    _this2.changed = _this2.changed.bind(_this2);
    _this2.submit = _this2.submit.bind(_this2);
    return _this2;
  }

  _createClass(SignUpModal, [{
    key: "changed",
    value: function changed(e) {
      this.data[e.target.name] = e.target.value;
    }
  }, {
    key: "submit",
    value: function submit(e) {
      e.preventDefault();
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { id: "signUpModal", className: "ui small modal" },
        React.createElement("i", { className: "close icon" }),
        React.createElement(
          "div",
          { className: "header" },
          "Sign Up"
        ),
        React.createElement(
          "div",
          { className: "content" },
          React.createElement(
            "form",
            { className: "ui form" },
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "First name"
              ),
              React.createElement("input", { type: "text", name: "firstName", placeholder: "First name", value: this.data.firstName, onChange: this.changed })
            ),
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "Last name"
              ),
              React.createElement("input", { type: "text", name: "lastName", placeholder: "Last name", value: this.data.lastName, onChange: this.changed })
            ),
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "E-mail"
              ),
              React.createElement("input", { type: "text", name: "email", placeholder: "E-mail", value: this.data.email, onChange: this.changed })
            ),
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "Password"
              ),
              React.createElement("input", { type: "password", name: "password", placeholder: "Password", value: this.data.password, onChange: this.changed })
            ),
            React.createElement(
              "div",
              { className: "field" },
              React.createElement(
                "label",
                null,
                "Repeat password"
              ),
              React.createElement("input", { type: "password", name: "repeatPassword", placeholder: "Password" })
            ),
            React.createElement(
              "button",
              { className: "ui button", type: "submit", style: { width: '100%' } },
              "Submit"
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

    var _this3 = _possibleConstructorReturn(this, (NavbarUser.__proto__ || Object.getPrototypeOf(NavbarUser)).call(this, props));

    _this3.state = { user: props.user };
    _this3.openSigninModal = _this3.openSigninModal.bind(_this3);
    _this3.openSignupModal = _this3.openSignupModal.bind(_this3);
    _this3.signout = _this3.signout.bind(_this3);
    return _this3;
  }

  _createClass(NavbarUser, [{
    key: "openSigninModal",
    value: function openSigninModal() {
      $('#signInModal.ui.modal').modal('show');
    }
  }, {
    key: "openSignupModal",
    value: function openSignupModal() {
      $('#signUpModal.ui.modal').modal('show');
    }
  }, {
    key: "signout",
    value: function signout() {}
  }, {
    key: "render",
    value: function render() {
      if (this.state.user) {
        return React.createElement(
          "div",
          { className: "right menu" },
          React.createElement(
            "a",
            { className: "item", onClick: this.signout },
            "Sign Out"
          )
        );
      } else {
        return React.createElement(
          "div",
          { className: "right menu" },
          React.createElement(SignInModal, null),
          React.createElement(
            "a",
            { className: "item", onClick: this.openSigninModal },
            "Sign In"
          ),
          React.createElement(SignUpModal, null),
          React.createElement(
            "a",
            { className: "item", onClick: this.openSignupModal },
            "Sign Up"
          )
        );
      }
    }
  }]);

  return NavbarUser;
}(React.Component);