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

    _this.id = props.comment.id;
    _this.username = props.comment.username;
    _this.commentTime = props.comment.time;
    _this.commentText = props.comment.text;

    _this.state = {
      showReplySection: false
    };

    _this.reply = _this.reply.bind(_this);
    return _this;
  }

  _createClass(Comment, [{
    key: "reply",
    value: function reply() {
      this.setState({ showReplySection: true });
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
            "a",
            { className: "author" },
            this.username
          ),
          React.createElement(
            "div",
            { className: "metadata" },
            React.createElement(
              "span",
              { className: "date" },
              this.commentTime
            )
          ),
          React.createElement(
            "div",
            { className: "text" },
            this.commentText
          ),
          React.createElement(
            "div",
            { className: "actions" },
            React.createElement(
              "a",
              { className: "reply", onClick: this.reply },
              "Reply"
            )
          )
        ),
        this.state.showReplySection &&
        // TODO: Only restaurants can reply to comments
        React.createElement(_PostComment2.default, null)
      );
    }
  }]);

  return Comment;
}(React.Component);

exports.default = Comment;