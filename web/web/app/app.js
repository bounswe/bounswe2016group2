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
  .on("*",function () {
    ReactDOM.render(
      //<Layout><FoodSearch/></Layout>
      <Layout><UserHomepage/></Layout>
    , root);
  })
  .resolve()
