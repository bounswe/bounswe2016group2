from django.conf.urls import url, include
from django.contrib import admin

from api.controller import search, ingredient, user, food, inclusion, ateFood, ateIngredient, restaurant, diet, mock

urlpatterns = [
    # SEARCH ROUTES
    url(r'^search$', search.search),
    url(r'^searchFood$', search.searchFood),
    # USER ROUTES
    url(r'^users/signup$', user.signup),
    url(r'^users/signin$', user.signin),
    url(r'^users/signout$', user.signout),
    url(r'^users$', user.users),
    url(r'^users/(?P<userId>[0-9]+)$', user.user),
    url(r'^users/me$', user.me),
    url(r'^users/me/detail$', user.meDetail),
    url(r'^users/me/history$', user.history),
    url(r'^users/me/restaurantHistory$', user.restaurantHistory),
    # INGREDIENT ROUTES
    url(r'^ingredients$', ingredient.ingredients),
    url(r'^ingredients/(?P<ingredientId>[0-9]+)$', ingredient.ingredient),
    url(r'^ingredients/(?P<ingredientId>[0-9]+)/ate$', ateIngredient.ateIngredient),
    url(r'^ingredients/(?P<slug>[\w-]+)$', ingredient.slug),
    url(r'^ingredientSearch', ingredient.search),
    # FOOD ROUTES
    url(r'^foods$', food.foods),
    url(r'^foods/(?P<foodId>[0-9]+)$', food.food),
    url(r'^foods/(?P<foodId>[0-9]+)/ate$', ateFood.ateFood),
    url(r'^foods/(?P<foodId>[0-9]+)/comment$', food.comment),
    url(r'^foods/(?P<foodId>[0-9]+)/rate$', food.rate),
    url(r'^foods/(?P<slug>[\w-]+)$', food.slug),
    url(r'^myFoods$', food.myFoods),
    # INCLUSION ROUTES
    url(r'^foods/(?P<food>[0-9]+)/ingredients/(?P<ingredient>[0-9]+)$', inclusion.inclusion),
    # RESTAURANT ROUTES
    url(r'^restaurants$', restaurant.restaurants),
    url(r'^restaurants/(?P<restaurantId>[0-9]+)$', restaurant.restaurant),
    url(r'^restaurants/(?P<restaurantId>[0-9]+)/foods/(?P<foodId>[0-9]+)$', restaurant.restaurantFood),
    url(r'^restaurants/(?P<restaurantId>[0-9]+)/comment', restaurant.comment),
    url(r'^restaurants/(?P<restaurantId>[0-9]+)/rate', restaurant.rate),
    url(r'^myRestaurants$', restaurant.myRestaurants),
    # DIET ROUTES
    url(r'^diets$', diet.diets),
    url(r'^diets/(?P<dietId>[0-9]+)$', diet.diet),
    url(r'^myDiets$', diet.myDiets),
    url(r'^myDiets/(?P<dietId>[0-9]+)$', diet.myDiet),
    # ADMIN ROUTES
    url(r'^admin/', admin.site.urls),
    url(r'^docs', include('rest_framework_docs.urls')),
    url(r'^createMocks$', mock.createMocks)
]
