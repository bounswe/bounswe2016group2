from rest_framework.authtoken.models import Token

from django.contrib.auth.models import User

from api.model.food import Food
from api.model.foodRate import FoodRate
from api.model.foodComment import FoodComment
from api.model.restaurant import Restaurant
from api.model.restaurantRate import RestaurantRate
from api.model.restaurantComment import RestaurantComment
from api.serializer.user import UserReadSerializer
from api.serializer.food import FoodPureSerializer
from api.serializer.foodComment import FoodCommentPureSerializer
from api.serializer.foodRate import FoodRatePureSerializer
from api.serializer.restaurant import RestaurantDetailSerializer
from api.serializer.restaurantComment import RestaurantCommentPureSerializer
from api.serializer.restaurantRate import RestaurantRatePureSerializer
from api.serializer.diet import DietReadSerializer
from api.service import food as FoodService
from api.service import restaurant as RestaurantService


def deleteToken(user):
    try:
        token = Token.objects.get(user=user)
        if token:
            token.delete()
    except Exception:
        pass


def refreshToken(user):
    deleteToken(user)
    return Token.objects.create(user=user)


def getDetailedUser(id):
    userModelObj = User.objects.get(id=id)
    # serializer = UserReadSerializer(user)
    user = UserReadSerializer(userModelObj).data

    foods = Food.objects.filter(user=id)
    serializer = FoodPureSerializer(foods, many=True)
    data = serializer.data
    for food in data:
        FoodService.calculateRate(food)
    user['foods'] = data

    restaurants = Restaurant.objects.filter(user=id)
    serializer = RestaurantDetailSerializer(restaurants, many=True)
    data = serializer.data
    for restaurant in data:
        RestaurantService.calculateRate(restaurant)
    user['restaurants'] = data

    serializer = DietReadSerializer(userModelObj.diet_set, many=True)
    user['diets'] = serializer.data

    foodComments = FoodComment.objects.filter(user=id)
    user['foodComments'] = FoodCommentPureSerializer(foodComments, many=True).data
    foodRates = FoodRate.objects.filter(user=id)
    user['foodRates'] = FoodRatePureSerializer(foodRates, many=True).data
    restaurantComments = RestaurantComment.objects.filter(user=id)
    user['restaurantComments'] = RestaurantCommentPureSerializer(restaurantComments, many=True).data
    restaurantRates = RestaurantRate.objects.filter(user=id)
    user['restaurantRates'] = RestaurantRatePureSerializer(restaurantRates, many=True).data

    return user
