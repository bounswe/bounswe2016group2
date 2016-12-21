from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.restaurant import Restaurant
from api.model.food import Food
from api.model.restaurantComment import RestaurantComment
from api.model.restaurantRate import RestaurantRate
from api.serializer.restaurant import RestaurantSerializer, RestaurantDetailSerializer
from api.serializer.restaurantComment import RestaurantCommentSerializer
from api.serializer.restaurantRate import RestaurantRateSerializer
from api.service import restaurant as RestaurantService


@api_view(['GET', 'POST'])
def restaurants(req):
    """
    Get all restaurants, or create a new one
    """
    if req.method == 'GET':
        restaurants = Restaurant.objects.all()
        serializer = RestaurantDetailSerializer(restaurants, many=True)
        data = serializer.data
        for restaurant in data:
            RestaurantService.calculateRate(restaurant)
        return Response(data)

    elif req.method == 'POST':
        req.data['user'] = req.user.id
        serializer = RestaurantSerializer(data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


# @TODO when no user or when restaurant doesnt belong to the user
@api_view(['GET', 'PUT', 'DELETE'])
def restaurant(req, restaurantId):
    """
    Retrive, modify or delete single restaurant by id
    """
    try:
        restaurant = Restaurant.objects.get(id=restaurantId)
    except Restaurant.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = RestaurantDetailSerializer(restaurant)
        data = serializer.data
        RestaurantService.calculateRate(data)
        return Response(data)

    elif req.method == 'PUT':
        req.data['user'] = req.user.id
        serializer = RestaurantSerializer(restaurant, data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif req.method == 'DELETE':
        restaurant.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST', 'DELETE'])
def restaurantFood(req, restaurantId, foodId):
    try:
        restaurant = Restaurant.objects.get(id=restaurantId)
        food = Food.objects.get(id=foodId)
    except Restaurant.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)
    except Food.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)
    if req.method == 'POST':
        restaurant.food_set.add(food)
        return Response({}, status=status.HTTP_201_CREATED)
    if req.method == 'DELETE':
        try:
            restaurant.food_set.remove(food)
        except Restaurant.DoesNotExist as e:
            return Response(status=status.HTTP_404_NOT_FOUND)
        return Response(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST', 'DELETE'])
def comment(req, restaurantId):
    """
    Add, modify or delete comment on restaurant
    """
    data = {}
    data['user'] = req.user.id
    data['restaurant'] = restaurantId
    if 'comment' in req.data:
        data['comment'] = req.data['comment']

    if req.method == 'POST':
        try:
            comment = RestaurantComment.objects.get(user=req.user.id, restaurant=restaurantId)
            serializer = RestaurantCommentSerializer(comment, data=data)
        except RestaurantComment.DoesNotExist:
            serializer = RestaurantCommentSerializer(data=data)

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    if req.method == 'DELETE':
        try:
            RestaurantComment.objects.get(restaurant=restaurantId, user=req.user.id).delete()
            return Response({}, status=status.HTTP_204_NO_CONTENT)
        except RestaurantComment.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

    return Response(serializer.data)


@api_view(['POST', 'DELETE'])
def rate(req, restaurantId):
    """
    Add, modify or delete rate on restaurant
    """
    data = {}
    data['user'] = req.user.id
    data['restaurant'] = restaurantId
    if 'score' in req.data:
        data['score'] = req.data['score']

    req.data['restaurant'] = restaurantId
    req.data['user'] = req.user.id
    if req.method == 'POST':
        try:
            comment = RestaurantRate.objects.get(user=req.user.id, restaurant=restaurantId)
            serializer = RestaurantRateSerializer(comment, data=data)
        except RestaurantRate.DoesNotExist:
            serializer = RestaurantRateSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    if req.method == 'DELETE':
        try:
            RestaurantRate.objects.get(restaurant=restaurantId, user=req.user.id).delete()
            return Response({}, status=status.HTTP_204_NO_CONTENT)
        except RestaurantRate.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

    return Response(serializer.data)


@api_view(['GET'])
def myRestaurants(req):
    """
    Get all restaurants, or create a new one
    """
    if req.method == 'GET':
        restaurants = Restaurant.objects.filter(user=req.user.id)
        serializer = RestaurantDetailSerializer(restaurants, many=True)
        data = serializer.data
        for restaurant in data:
            RestaurantService.calculateRate(restaurant)
        return Response(data)
