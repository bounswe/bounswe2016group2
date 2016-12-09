from django.utils.text import slugify

from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.ingredient import Ingredient
from api.model.food import Food
from api.model.restaurant import Restaurant
from api.serializer.food import FoodSerializer
from api.serializer.ingredient import IngredientSerializer
from api.serializer.restaurant import RestaurantSerializer


def searchFoods(q):
    foods = Food.objects.filter(slug__startswith=q)[:10]
    if len(foods) == 0:
        foods = Food.objects.filter(slug__contains=q)
    serializer = FoodSerializer(foods, many=True)
    return serializer.data


def searchIngredients(q):
    ingredients = Ingredient.objects.filter(slug__startswith=q)[:10]
    if len(ingredients) == 0:
        ingredients = Ingredient.objects.filter(slug__contains=q)
    serializer = IngredientSerializer(ingredients, many=True)
    return serializer.data


def searchRestaurants(q):
    restaurants = Restaurant.objects.filter(slug__startswith=q)[:10]
    if len(restaurants) == 0:
        restaurants = Restaurant.objects.filter(slug__contains=q)
    serializer = RestaurantSerializer(restaurants, many=True)
    return serializer.data


@api_view(['GET'])
def search(req):
    """
    Search food
    """
    q = slugify(req.GET.get('query', ''))
    if q == '':
        return Response({
            'foods': [],
            'ingredients': [],
            'restaurants': []
        })

    foods = searchFoods(q)
    ingredients = searchIngredients(q)
    restaurants = searchRestaurants(q)

    return Response({
        'foods': foods,
        'ingredients': ingredients,
        'restaurants': restaurants
    })
