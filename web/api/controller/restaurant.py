from django.http import HttpResponse
from django.utils.text import slugify

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.restaurant import Restaurant
from api.model.food import Food
from api.serializer.food import FoodSerializer, FoodReadSerializer
from api.serializer.restaurant import RestaurantSerializer
from api.serializer.user import UserSerializer
from api.service import food as FoodService


@api_view(['GET', 'POST'])
def restaurants(req):
    """
    Get all restaurants, or create a new one
    """
    if req.method == 'GET':
        restaurants = Restaurant.objects.all()
        serializer = RestaurantSerializer(restaurants, many=True)
        return Response(serializer.data)

    elif req.method == 'POST':
        req.data['user'] = req.user.id
        serializer = RestaurantSerializer(data=req.data)
        print(req.user)
        if serializer.is_valid():
            serializer.save()
            print(serializer.data)
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
