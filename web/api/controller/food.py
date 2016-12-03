from django.http import HttpResponse
from django.utils.text import slugify

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

# from api.service import food as FoodService
from api.model.food import Food
from api.serializer.food import FoodSerializer, FoodReadSerializer


@api_view(['GET', 'POST'])
def foods(req):
    """
    Get all foods, or create a new one
    """
    if req.method == 'GET':
        foods = Food.objects.all()
        serializer = FoodReadSerializer(foods, many=True)
        return Response(serializer.data)

    elif req.method == 'POST':
        serializer = FoodSerializer(data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'PUT', 'DELETE'])
def food(req, foodId):
    """
    Retrive, modify or delete single food by id
    """
    try:
        food = Food.objects.get(id=foodId)
    except Food.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = FoodReadSerializer(food)
        return Response(serializer.data)

    elif req.method == 'PUT':
        serializer = FoodSerializer(food, data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif req.method == 'DELETE':
        food.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['GET', 'DELETE'])
def slug(req, slug):
    """
    Retrive or delete single food by slug
    """
    try:
        food = Food.objects.get(slug=slug)
    except Food.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = FoodSerializer(food)
        return Response(serializer.data)

    elif req.method == 'DELETE':
        food.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST'])
def createMocks(req):
    """
    Add default foods in mocks to database
    """
    FoodService.createDefaults()
    return HttpResponse(status=status.HTTP_201_CREATED)


@api_view(['GET'])
def search(req):
    """
    Search food
    """
    q = slugify(req.GET.get('query', ''))
    foods = Food.objects.filter(slug__startswith=q)
    seriealizer = FoodSerializer(foods, many=True)
    return Response(seriealizer.data)
