import Layout from 'Layout.js'
import AddFoodPage from 'food/AddFoodPage.js'
import FoodPage from 'food/FoodPage.js'
import RestaurantPage from 'restaurant/RestaurantPage.js'
import AddRestaurantPage from 'restaurant/AddRestaurantPage.js'
import IngredientPage from 'ingredient/IngredientPage.js'
import UserHomepage from 'user/UserHomepage.js'
import Homepage from 'Homepage.js'
import CreateDietPage from 'diet/CreateDietPage.js'
import DietPage from 'diet/DietPage.js'

const route = (path, callback) => {
  router.on(path, (params) => {
    const component = callback(params)
    ReactDOM.render(<Layout>{component}</Layout>, root)
  })
}

route("/addFood/",        ()       => { return <AddFoodPage/>                   })
route("/foods/:id",       (params) => { return <FoodPage id={params.id}/>       })
route("/ingredient/:id",  (params) => { return <IngredientPage id={params.id}/> })
route("/addRestaurant",   (params) => { return <AddRestaurantPage/>             })
route("/restaurants/:id", (params) => { return <RestaurantPage id={params.id}/> })
route("/profile/",        (params) => { return <UserHomepage/>                  })
route("createDiet/",      ()       => { return <CreateDietPage/>                })
route("diets/:id",        (params) => { return <DietPage id={params.id}/>       })
route("*",                ()       => { return <Homepage/>                      })

router.resolve()
