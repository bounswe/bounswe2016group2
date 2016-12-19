"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _PostComment = require("comment/PostComment.js");

var _PostComment2 = _interopRequireDefault(_PostComment);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Comment = function (_React$Component) {
  _inherits(Comment, _React$Component);

  function Comment(props) {
    _classCallCheck(this, Comment);

    var _this = _possibleConstructorReturn(this, (Comment.__proto__ || Object.getPrototypeOf(Comment)).call(this, props));

    _this.userId = props.comment.user;
    _this.comment = props.comment.comment;
    _this.state = {};

    _this.fetchUserName = _this.fetchUserName.bind(_this);
    _this.fetchUserName();
    return _this;
  }

  _createClass(Comment, [{
    key: "fetchUserName",
    value: function fetchUserName() {
      var _this2 = this;

      Api.getUser(this.userId).then(function (data) {
        var screenName = "";
        if (data.first_name || data.last_name) {
          screenName = data.first_name + " " + data.last_name;
        } else {
          screenName = data.username;
        }
        _this2.setState({ user: screenName });
      }).catch(function (error) {
        _this2.setState({ errors: error.data });
      });
    }
  }, {
    key: "render",
    value: function render() {
      return React.createElement(
        "div",
        { className: "comment" },
        React.createElement(
          "div",
          { className: "content" },
          React.createElement(
            "div",
            { className: "author" },
            this.state.user
          ),
          React.createElement(
            "div",
            { className: "text" },
            this.comment
          )
        )
      );
    }
  }]);

  return Comment;
}(React.Component);

exports.default = Comment;