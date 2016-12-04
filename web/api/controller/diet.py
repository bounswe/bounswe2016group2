from django.http import HttpResponse
from django.utils.text import slugify

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.food import Food
from api.model.diet import Diet
from api.serializer.food import FoodSerializer, FoodReadSerializer
from api.serializer.diet import DietSerializer, DietReadSerializer
from api.service import food as FoodService


@api_view(['GET', 'POST'])
def diets(req):
    """
    Get all diets, or create a new one
    """
    if req.method == 'GET':
        diets = Diet.objects.all()
        serializer = DietReadSerializer(diets, many=True)
        return Response(serializer.data)

    elif req.method == 'POST':
        serializer = DietSerializer(data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
