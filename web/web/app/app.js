import Layout from 'Layout.js'
import AddFoodPage from 'food/AddFoodPage.js'
import FoodPage from 'food/FoodPage.js'
import RestaurantPage from 'restaurant/RestaurantPage.js'
import AddRestaurantPage from 'restaurant/AddRestaurantPage.js'
import IngredientPage from 'ingredient/IngredientPage.js'
import UserHomepage from 'user/UserHomepage.js'
import Homepage from 'Homepage.js'


router
  .on("/addFood/", function() {
    ReactDOM.render(
      <Layout><AddFoodPage/></Layout>
    , root);
  })
  .on("/foods/:id", function(params) {
    ReactDOM.render(
      <Layout><FoodPage id={params.id}/></Layout>,
      root);
  })
  .on("/ingredient/:id", function(params) {
    ReactDOM.render(
      <Layout><IngredientPage id={params.id}/></Layout>,
      root);
  })
  .on("/addRestaurant", function(params) {
    ReactDOM.render(
      <Layout><AddRestaurantPage/></Layout>,
      root);
  })
  .on("/restaurants/:id", function(params) {
    ReactDOM.render(
      <Layout><RestaurantPage id={params.id}/></Layout>,
      root);
  })
  .on("/profile/", function(params) {
    ReactDOM.render(
      <Layout><UserHomepage/></Layout>,
      root);
  })
  .on("*",function () {
    ReactDOM.render(
      <Layout><Homepage/></Layout>
    , root);
  })
  .resolve()
