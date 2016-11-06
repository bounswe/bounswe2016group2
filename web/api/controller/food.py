from django.http import HttpResponse
from django.utils.text import slugify

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

# from api.service import food as FoodService
from api.model.food import Food, FoodSerializer


@api_view(['GET', 'POST'])
def foods(req):
    """
    Get all foods, or create a new one
    """
    if req.method == 'GET':
        foods = Food.objects.all()
        serializer = FoodSerializer(foods, many=True)
        return Response(serializer.data)

    elif req.method == 'POST':
        if 'name' in req.POST:
            req.POST['slug'] = slugify(req.POST['name'])
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

    if 'name' in req.POST:
        req.POST['slug'] = slugify(req.POST['name'])

    if req.method == 'GET':
        serializer = FoodSerializer(food)
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
    # FoodService.createDefaults()
    return HttpResponse(status=status.HTTP_201_CREATED)
