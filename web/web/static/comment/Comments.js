'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _Comment = require('comment/Comment.js');

var _Comment2 = _interopRequireDefault(_Comment);

var _PostComment = require('comment/PostComment.js');

var _PostComment2 = _interopRequireDefault(_PostComment);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var Comments = function (_React$Component) {
  _inherits(Comments, _React$Component);

  function Comments(props) {
    _classCallCheck(this, Comments);

    var _this = _possibleConstructorReturn(this, (Comments.__proto__ || Object.getPrototypeOf(Comments)).call(this, props));

    _this.foodId = props.foodId;
    _this.state = {
      comments: []
    };
    if (props.getComments) {
      _this.getComments = props.getComments.bind(_this);
      _this.getComments(_this.foodId);
    }

    // api functions
    _this.comment = props.comment.bind(_this);
    _this.commentFunction = _this.commentFunction.bind(_this);
    return _this;
  }

  _createClass(Comments, [{
    key: 'commentFunction',
    value: function commentFunction(e) {
      this.comment(e);

      // a quick solution for updating comments
      window.location.href = "foods/" + this.foodId;
    }
  }, {
    key: 'render',
    value: function render() {
      return React.createElement(
        'div',
        { className: 'ui comments' },
        this.state.comments.map(function (comment, index) {
          return React.createElement(_Comment2.default, { key: index, comment: comment });
        }, this),
        React.createElement(_PostComment2.default, { addComment: this.commentFunction })
      );
    }
  }]);

  return Comments;
}(React.Component);

exports.default = Comments;