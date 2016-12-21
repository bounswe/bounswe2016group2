from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.food import Food
from api.model.foodComment import FoodComment
from api.model.foodRate import FoodRate
from api.model.tag import Tag
from api.serializer.food import FoodSerializer, FoodReadSerializer, FoodPureSerializer
from api.serializer.foodComment import FoodCommentSerializer
from api.serializer.foodRate import FoodRateSerializer
from api.serializer.tag import TagSerializer
from api.service import food as FoodService


@api_view(['GET', 'POST'])
def foods(req):
    """
    Get all foods, or create a new one
    """
    if req.method == 'GET':
        foods = Food.objects.all()
        serializer = FoodPureSerializer(foods, many=True)
        data = serializer.data
        for food in data:
            FoodService.calculateRate(food)
        return Response(data)

    elif req.method == 'POST':
        if(req.user.id):
            req.data['user'] = req.user.id
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
        food = FoodService.calculateDetails(serializer.data)
        return Response(food)

    elif req.method == 'PUT':
        if(req.user.id):
            req.data['user'] = req.user.id
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


@api_view(['POST', 'DELETE'])
def comment(req, foodId):
    """
    Add, modify or delete comment on food
    """
    data = {}
    data['user'] = req.user.id
    data['food'] = foodId
    if 'comment' in req.data:
        data['comment'] = req.data['comment']

    if req.method == 'POST':
        try:
            comment = FoodComment.objects.get(user=req.user.id, food=foodId)
            serializer = FoodCommentSerializer(comment, data=data)
        except FoodComment.DoesNotExist:
            serializer = FoodCommentSerializer(data=data)

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    if req.method == 'DELETE':
        try:
            FoodComment.objects.get(food=foodId, user=req.user.id).delete()
            return Response({}, status=status.HTTP_204_NO_CONTENT)
        except FoodComment.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

    return Response(serializer.data)


@api_view(['POST', 'DELETE'])
def rate(req, foodId):
    """
    Add, modify or delete rate on food
    """
    data = {}
    data['user'] = req.user.id
    data['food'] = foodId
    if 'score' in req.data:
        data['score'] = req.data['score']

    if req.method == 'POST':
        try:
            comment = FoodRate.objects.get(user=req.user.id, food=foodId)
            serializer = FoodRateSerializer(comment, data=data)
        except FoodRate.DoesNotExist:
            serializer = FoodRateSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    if req.method == 'DELETE':
        try:
            FoodRate.objects.get(food=foodId, user=req.user.id).delete()
            return Response({}, status=status.HTTP_204_NO_CONTENT)
        except FoodRate.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

    return Response(serializer.data)


@api_view(['POST', 'DELETE'])
def tag(req, foodId):
    """
    Add or delete tag on food
    """
    name = req.data['name'] if 'name' in req.data else ''

    try:
        food = Food.objects.get(id=foodId)
    except Food.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    try:
        tag = Tag.objects.get(name=name)
    except Tag.DoesNotExist:
        serializer = TagSerializer(data={'name': name})
        if serializer.is_valid():
            serializer.save()
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    tag = Tag.objects.get(name=name)

    if req.method == 'POST':
        food.tags.add(tag)

    if req.method == 'DELETE':
        food.tags.remove(tag)

    food.save()
    return Response({})


@api_view(['GET'])
def myFoods(req):
    """
    Get all foods, or create a new one
    """
    foods = Food.objects.filter(user=req.user.id)
    serializer = FoodPureSerializer(foods, many=True)
    data = serializer.data
    for food in data:
        FoodService.calculateRate(food)
    return Response(data)
