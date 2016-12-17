'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

// onChange: function to be called on option selected or removed
// name: form name
// placeholder: placeholder
var TagSelect = function (_React$Component) {
  _inherits(TagSelect, _React$Component);

  function TagSelect(props) {
    _classCallCheck(this, TagSelect);

    var _this = _possibleConstructorReturn(this, (TagSelect.__proto__ || Object.getPrototypeOf(TagSelect)).call(this, props));

    _this.data = props.data;
    _this.state = {
      name: props.name,
      placeholder: props.placeholder,
      tags: [],
      selectedTags: []

    };
    _this.onSelectedTagsChange = props.onChange;
    return _this;
  }

  _createClass(TagSelect, [{
    key: 'queryChanged',
    value: function queryChanged(e) {
      this.setState({ query: e.target.value });
      if (e.target.value == '') {
        this.setState({
          list: []
        });
      }
    }
  }, {
    key: 'search',
    value: function search(e) {
      var _this2 = this;

      e.preventDefault();
      if (this.state.query == '') return;
      var self = this;
      Api.searchTag(this.state.query).then(function (data) {
        var list = data.map(function (tag) {
          return React.createElement(
            'div',
            { className: 'item', key: tag.name },
            React.createElement(
              'div',
              { className: 'content' },
              React.createElement(
                'a',
                { className: 'header', onClick: _this2.addTag.bind({ context: self, tag: tag }) },
                tag.name
              ),
              React.createElement(
                'div',
                { className: 'description' },
                tag.description
              )
            )
          );
        });
        self.setState({
          list: list
        });
      });
    }
  }, {
    key: 'addTag',
    value: function addTag() {
      var self = this.context;
      var tag = this.tag;
      var selectedTags = self.state.selectedTags;
      if (selectedTags.indexOf(tag) == -1) {
        selectedTags.push(tag);
        self.setState({
          selectedTags: selectedTags
        });
        self.onSelectedTagsChange(selectedTags);
      }
    }
  }, {
    key: 'removeTag',
    value: function removeTag() {
      var self = this.context;
      var tag = this.tag;
      var selectedTags = self.state.selectedTags;
      selectedTags.forEach(function (nextTag, i) {
        if (nextTag.name == tag.name) {
          selectedTags.splice(i, 1);
        }
      });
      self.setState({
        selectedTags: selectedTags
      });
      self.onSelectedTagsChange(selectedTags);
    }
  }, {
    key: 'render',
    value: function render() {
      var _this3 = this;

      var self = this;
      return React.createElement(
        'div',
        null,
        React.createElement(
          'div',
          { className: 'ui action input' },
          React.createElement('input', { type: 'text', placeholder: 'Search tag', value: this.state.query, onChange: this.queryChanged.bind(this) }),
          React.createElement(
            'button',
            { className: 'ui button', onClick: this.search.bind(this) },
            'Search'
          )
        ),
        React.createElement(
          'div',
          { style: { marginTop: 20, marginBottom: 20 } },
          this.state.selectedTags.map(function (tag) {
            return React.createElement(
              'a',
              { key: tag.name, className: 'ui label' },
              tag.name,
              React.createElement('i', { className: 'delete icon', onClick: _this3.removeTag.bind({ context: self, tag: tag }) })
            );
          })
        ),
        React.createElement(
          'div',
          { className: 'ui relaxed divided list' },
          this.state.list
        )
      );
    }
  }]);

  return TagSelect;
}(React.Component);

exports.default = TagSelect;