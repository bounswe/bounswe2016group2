from django.conf.urls import url, include
from django.contrib import admin

from api.controller import ingredient, user, food, inclusion, ateFood, ateIngredient, restaurant, diet

urlpatterns = [
    # USER ROUTES
    url(r'^users/signup$', user.signup),
    url(r'^users/signin$', user.signin),
    url(r'^users/signout$', user.signout),
    url(r'^users/me$', user.me),
    url(r'^users$', user.users),
    url(r'^users/(?P<userId>[0-9]+)$', user.user),
    url(r'^users/history$', user.history),
    # INGREDIENT ROUTES
    url(r'^ingredients$', ingredient.ingredients),
    url(r'^ingredients/(?P<ingredientId>[0-9]+)$', ingredient.ingredient),
    url(r'^ingredients/(?P<ingredientId>[0-9]+)/ate$', ateIngredient.ateIngredient),
    url(r'^ingredients/(?P<slug>[\w-]+)$', ingredient.slug),
    url(r'^ingredientSearch', ingredient.search),
    url(r'^ingredientMocks$', ingredient.createMocks),
    # FOOD ROUTES
    url(r'^foods$', food.foods),
    url(r'^foods/(?P<foodId>[0-9]+)$', food.food),
    url(r'^foods/(?P<foodId>[0-9]+)/ate$', ateFood.ateFood),
    url(r'^foods/(?P<slug>[\w-]+)$', food.slug),
    url(r'^foodSearch$', food.search),
    url(r'^foodMocks$', food.createMocks),
    # INCLUSION ROUTES
    url(r'^foods/(?P<food>[0-9]+)/ingredients/(?P<ingredient>[0-9]+)$', inclusion.inclusion),
    # RESTAURANT ROUTES
    url(r'^restaurants$', restaurant.restaurants),
    url(r'^restaurants/(?P<restaurantId>[0-9]+)$', restaurant.restaurant),
    # DIET ROUTES
    url(r'^diets$', diet.diets),
    url(r'^diets/(?P<dietId>[0-9]+)$', diet.diet),
    # ADMIN ROUTES
    url(r'^admin/', admin.site.urls),
    url(r'^docs', include('rest_framework_docs.urls'))
]
