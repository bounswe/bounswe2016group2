from django.utils.text import slugify

from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework import status

from api.model.ingredient import Ingredient
from api.model.food import Food
from api.model.restaurant import Restaurant
from api.model.diet import Diet
from api.serializer.food import FoodReadSerializer, FoodPureSerializer
from api.serializer.ingredient import IngredientPureSerializer
from api.serializer.restaurant import RestaurantPureSerializer
from api.serializer.diet import DietFilterSerializer
from api.service import food as FoodService
from api.service import restaurant as RestaurantService
from api.service import tag as TagService


# q: query string
def searchFoods(q):
    foods = Food.objects.filter(slug__startswith=q)
    if len(foods) == 0:
        foods = Food.objects.filter(slug__contains=q)
    serializer = FoodPureSerializer(foods, many=True)
    data = serializer.data
    for food in data:
        FoodService.calculateRate(food)
    return data


# q: query string
def searchIngredients(q):
    ingredients = Ingredient.objects.filter(slug__startswith=q)
    if len(ingredients) == 0:
        ingredients = Ingredient.objects.filter(slug__contains=q)
    serializer = IngredientPureSerializer(ingredients, many=True)
    return serializer.data


# q: query string
def searchRestaurants(q):
    restaurants = Restaurant.objects.filter(slug__startswith=q)
    if len(restaurants) == 0:
        restaurants = Restaurant.objects.filter(slug__contains=q)
    serializer = RestaurantPureSerializer(restaurants, many=True)
    data = serializer.data
    for restaurant in data:
        RestaurantService.calculateRate(restaurant)
    return serializer.data


# foods: food dict array
# diet: diet dict
def searchFoodByDiet(foods, diets):
    result = []
    for food in foods:
        FoodService.calculateDetails(food)
        exclude = False

        for diet in diets:
            if not diet['minEnergy'] < food['details']['energy'] < diet['maxEnergy']:
                exclude = True
            if not diet['minProteinVal'] < food['details']['protein']['weight'] < diet['maxProteinVal']:
                exclude = True
            if not diet['minCarbVal'] < food['details']['carb']['weight'] < diet['maxCarbVal']:
                exclude = True
            if not diet['minFatVal'] < food['details']['fat']['weight'] < diet['maxFatVal']:
                exclude = True

        for ingredient in food['ingredients']:
            for diet in diets:
                if ingredient['id'] in diet['ingredients']:
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

    diets = [dietSerializer.data]
    if 'diets' in req.data:
        for dietId in req.data['diets']:
            print(dietId)
            try:
                dietDict = DietFilterSerializer(Diet.objects.get(id=dietId)).data
                diets.append(dietDict)
            except Diet.DoesNotExists as e:
                return Response(status=status.HTTP_404_NOT_FOUND)

    foods = Food.objects.filter(slug__contains=q)
    foods = FoodReadSerializer(foods, many=True).data

    result = searchFoodByDiet(foods, diets)

    return Response(result)


@api_view(['GET'])
def searchTag(req):
    """
    Search food
    """
    q = slugify(req.GET.get('query', ''))
    if q == '':
        return Response([])

    tags = TagService.search(q)

    return Response(tags)
