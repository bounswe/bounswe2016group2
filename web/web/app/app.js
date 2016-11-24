const root = document.getElementById('root')
const token = reactCookie.load('token')
const userEmail = reactCookie.load('email')

var router = new Navigo({root:'/', useHash:false});

router
  .on("/addFood/", function() {
    ReactDOM.render(
      <Layout><AddFood/></Layout>
    , root);
  })
  .on("/food/:id", function(params) {
    ReactDOM.render(
      <Layout><FoodPage id={params.id}/></Layout>,
      root);
  })
  .on("/ingredient/:id", function(params) {
    ReactDOM.render(
      <Layout><IngrPage id={params.id}/></Layout>,
      root);
  })
  .on("/profile/", function(params) {
    ReactDOM.render(
      <Layout><UserHomepage/></Layout>,
      root);
  })
  .on("*",function () {
    ReactDOM.render(
      <Layout><FoodSearch/></Layout>
    , root);
  })
  .resolve()
