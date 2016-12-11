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

var route = function route(path, callback) {
  router.on(path, function (params) {
    var component = callback(params);
    ReactDOM.render(React.createElement(
      _Layout2.default,
      null,
      component
    ), root);
  });
};

route("/addFood/", function () {
  return React.createElement(_AddFoodPage2.default, null);
});
route("/foods/:id", function (params) {
  return React.createElement(_FoodPage2.default, { id: params.id });
});
route("/ingredient/:id", function (params) {
  return React.createElement(_IngredientPage2.default, { id: params.id });
});
route("/addRestaurant", function (params) {
  return React.createElement(_AddRestaurantPage2.default, null);
});
route("/restaurants/:id", function (params) {
  return React.createElement(_RestaurantPage2.default, { id: params.id });
});
route("/profile/", function (params) {
  return React.createElement(_UserHomepage2.default, null);
});
route("createDiet/", function () {
  return React.createElement(_CreateDietPage2.default, null);
});
route("*", function () {
  return React.createElement(_Homepage2.default, null);
});

router.resolve();