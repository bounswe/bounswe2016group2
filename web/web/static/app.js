'use strict';

var _Layout = require('Layout.js');

var _Layout2 = _interopRequireDefault(_Layout);

var _AddFoodPage = require('food/AddFoodPage.js');

var _AddFoodPage2 = _interopRequireDefault(_AddFoodPage);

var _FoodPage = require('food/FoodPage.js');

var _FoodPage2 = _interopRequireDefault(_FoodPage);

var _RestaurantPage = require('restaurant/RestaurantPage.js');

var _RestaurantPage2 = _interopRequireDefault(_RestaurantPage);

var _AddRestaurantPage = require('restaurant/AddRestaurantPage.js');

var _AddRestaurantPage2 = _interopRequireDefault(_AddRestaurantPage);

var _IngredientPage = require('ingredient/IngredientPage.js');

var _IngredientPage2 = _interopRequireDefault(_IngredientPage);

var _UserHomepage = require('user/UserHomepage.js');

var _UserHomepage2 = _interopRequireDefault(_UserHomepage);

var _Homepage = require('Homepage.js');

var _Homepage2 = _interopRequireDefault(_Homepage);

var _CreateDietPage = require('diet/CreateDietPage.js');

var _CreateDietPage2 = _interopRequireDefault(_CreateDietPage);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

router.on("/addFood/", function () {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_AddFoodPage2.default, null)
  ), root);
}).on("/foods/:id", function (params) {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_FoodPage2.default, { id: params.id })
  ), root);
}).on("/ingredient/:id", function (params) {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_IngredientPage2.default, { id: params.id })
  ), root);
}).on("/addRestaurant", function (params) {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_AddRestaurantPage2.default, null)
  ), root);
}).on("/restaurants/:id", function (params) {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_RestaurantPage2.default, { id: params.id })
  ), root);
}).on("/profile/", function (params) {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_UserHomepage2.default, null)
  ), root);
}).on("createDiet/", function () {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_CreateDietPage2.default, null)
  ), root);
}).on("*", function () {
  ReactDOM.render(React.createElement(
    _Layout2.default,
    null,
    React.createElement(_Homepage2.default, null)
  ), root);
}).resolve();