'use strict';

var root = document.getElementById('root');
var token = reactCookie.load('token');
var userEmail = reactCookie.load('email');

var router = new Navigo({ root: '/', useHash: false });

router.on("/addFood/", function () {
  ReactDOM.render(React.createElement(
    Layout,
    null,
    React.createElement(AddFood, null)
  ), root);
}).on("*", function () {
  ReactDOM.render(React.createElement(
    Layout,
    null,
    React.createElement(FoodSearch, null)
  ), root);
}).resolve();