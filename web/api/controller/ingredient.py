from django.http import HttpResponse
from django.utils.text import slugify

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

# from api.service import ingredient as IngredientService
from api.model.ingredient import Ingredient, IngredientSerializer


@api_view(['GET', 'POST'])
def ingredients(req):
    """
    Get all ingredients, or create a new one
    """
    if req.method == 'GET':
        ingredients = Ingredient.objects.all()
        serializer = IngredientSerializer(ingredients, many=True)
        return Response(serializer.data)

    elif req.method == 'POST':
        serializer = IngredientSerializer(data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'PUT', 'DELETE'])
def ingredient(req, ingredientId):
    """
    Retrive, modify or delete single ingredient by id
    """
    try:
        ingredient = Ingredient.objects.get(id=ingredientId)
    except Ingredient.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = IngredientSerializer(ingredient)
        return Response(serializer.data)

    elif req.method == 'PUT':
        serializer = IngredientSerializer(ingredient, data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif req.method == 'DELETE':
        ingredient.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['GET', 'DELETE'])
def slug(req, slug):
    """
    Retrive or delete single ingredient by slug
    """
    try:
        ingredient = Ingredient.objects.get(slug=slug)
    except Ingredient.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = IngredientSerializer(ingredient)
        return Response(serializer.data)

    elif req.method == 'DELETE':
        ingredient.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST'])
def createMocks(req):
    """
    Add default ingredients in mocks to database
    """
    # IngredientService.createDefaults()
    return HttpResponse(status=status.HTTP_201_CREATED)

@api_view(['GET'])
def search(req):
    """
    Search ingredient
    """
    q = slugify(req.GET.get('query', ''))
    ingredients = Ingredient.objects.filter(slug__startswith=q)
    seriealizer = IngredientSerializer(ingredients, many=True)
    return Response(seriealizer.data)
