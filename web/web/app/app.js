import Layout from 'Layout.js'
import AddFoodPage from 'food/AddFoodPage.js'
import FoodPage from 'food/FoodPage.js'
import IngredientPage from 'ingredient/IngredientPage.js'
import UserHomepage from 'user/UserHomepage.js'
import Homepage from 'Homepage.js'
import CreateDietPage from 'diet/CreateDietPage.js'

var router = new Navigo({root:'/', useHash:false});


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
  .on("/profile/", function(params) {
    ReactDOM.render(
      <Layout><UserHomepage/></Layout>,
      root);
  })
  .on("createDiet/", function(){
    ReactDOM.render(
      <Layout><CreateDietPage/></Layout>,
      root);
  })
  .on("*",function () {
    ReactDOM.render(
      <Layout><Homepage/></Layout>
    , root);
  })
  .resolve()
