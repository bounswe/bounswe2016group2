from django.utils.text import slugify

from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

from api.model.ingredient import Ingredient
from api.model.food import Food
from api.model.restaurant import Restaurant
from api.serializer.food import FoodSerializer, FoodReadSerializer
from api.serializer.ingredient import IngredientSerializer
from api.serializer.restaurant import RestaurantSerializer
from api.serializer.diet import DietFilterSerializer
from api.service import food as FoodService


# q: query string
def searchFoods(q):
    foods = Food.objects.filter(slug__startswith=q)
    if len(foods) == 0:
        foods = Food.objects.filter(slug__contains=q)
    serializer = FoodSerializer(foods, many=True)
    return serializer.data


# q: query string
def searchIngredients(q):
    ingredients = Ingredient.objects.filter(slug__startswith=q)
    if len(ingredients) == 0:
        ingredients = Ingredient.objects.filter(slug__contains=q)
    serializer = IngredientSerializer(ingredients, many=True)
    return serializer.data


# q: query string
def searchRestaurants(q):
    restaurants = Restaurant.objects.filter(slug__startswith=q)
    if len(restaurants) == 0:
        restaurants = Restaurant.objects.filter(slug__contains=q)
    serializer = RestaurantSerializer(restaurants, many=True)
    return serializer.data


# foods: food dict array
# diet: diet dict
def searchFoodByDiet(foods, diet):
    result = []
    for food in foods:
        FoodService.calculateDetails(food)
        exclude = False

        if not diet['minEnergy'] < food['details']['energy'] < diet['maxEnergy']:
            exclude = True
        if not diet['minProteinVal'] < food['details']['protein']['weight'] < diet['maxProteinVal']:
            exclude = True
        if not diet['minCarbVal'] < food['details']['carb']['weight'] < diet['maxCarbVal']:
            exclude = True
        if not diet['minFatVal'] < food['details']['fat']['weight'] < diet['maxFatVal']:
            exclude = True

        if not exclude:
            result.append(food)
    return result


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
        'foods': foods[:10],
        'ingredients': ingredients[:10],
        'restaurants': restaurants[:10]
    })


@api_view(['POST'])
def searchFood(req):
    """
    Search food with filtering options
    """
    q = slugify(req.POST.get('query', ''))
    dietSerializer = DietFilterSerializer(data=req.data)

    if not dietSerializer.is_valid():
        return Response(dietSerializer.errors, status=status.HTTP_400_BAD_REQUEST)

    foods = Food.objects.filter(slug__contains=q)
    foods = FoodReadSerializer(foods, many=True).data
    diet = dietSerializer.data

    result = searchFoodByDiet(foods, diet)

    return Response(result)