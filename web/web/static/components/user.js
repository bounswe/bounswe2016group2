'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var NavbarUser = function (_React$Component) {
  _inherits(NavbarUser, _React$Component);

  function NavbarUser(props) {
    _classCallCheck(this, NavbarUser);

    var _this = _possibleConstructorReturn(this, (NavbarUser.__proto__ || Object.getPrototypeOf(NavbarUser)).call(this, props));

    _this.state = { user: props.user };
    _this.signin = _this.signin.bind(_this);
    _this.signout = _this.signout.bind(_this);
    return _this;
  }

  _createClass(NavbarUser, [{
    key: 'signin',
    value: function signin() {
      this.setState({ user: user });
    }
  }, {
    key: 'signout',
    value: function signout() {
      this.setState({ user: false });
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'right menu' },
        React.createElement(
          'a',
          { className: 'item', onClick: this.state.user ? this.signout : this.signin },
          this.state.user ? 'Sign Out' : 'Sign In'
        )
      );
    }
  }]);

  return NavbarUser;
}(React.Component);