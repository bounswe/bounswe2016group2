from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.food import Food
from api.model.foodComment import FoodComment
from api.model.foodRate import FoodRate
from api.serializer.food import FoodSerializer, FoodReadSerializer
from api.serializer.foodComment import FoodCommentSerializer
from api.serializer.foodRate import FoodRateSerializer
from api.service import food as FoodService


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
    req.data['food'] = foodId,
    req.data['user'] = req.user.id
    if req.method == 'POST':
        try:
            comment = FoodComment.objects.get(user=req.user.id, food=foodId)
            serializer = FoodCommentSerializer(comment, data=req.data)
        except FoodComment.DoesNotExist:
            serializer = FoodCommentSerializer(data=req.data)

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
    req.data['food'] = foodId,
    req.data['user'] = req.user.id
    if req.method == 'POST':
        try:
            comment = FoodRate.objects.get(user=req.user.id, food=foodId)
            serializer = FoodRateSerializer(comment, data=req.data)
        except FoodRate.DoesNotExist:
            serializer = FoodRateSerializer(data=req.data)
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
