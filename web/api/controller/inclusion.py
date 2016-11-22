from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

# from api.model.food import Food, FoodSerializer
from api.model.inclusion import Inclusion, InclusionSerializer


@api_view(['GET', 'POST', 'PUT', 'DELETE'])
def inclusion(req, food, ingredient):
    """
    Create, retrive, modify or delete the existence of ingredient in food
    """
    data = {
        'food': food,
        'ingredient': ingredient
    }
    if 'value' in req.data:
        data['value'] = req.data['value']
    if 'unit' in req.data:
        data['unit'] = req.data['unit']
    if req.method == 'POST':
        serializer = InclusionSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    else:
        # find the relation if request is not POST
        try:
            inclusion = Inclusion.objects.get(food=food, ingredient=ingredient)
        except Inclusion.DoesNotExist:
            return HttpResponse(status=status.HTTP_404_NOT_FOUND)

        if req.method == 'GET':
            serializer = InclusionSerializer(inclusion)
            return Response(serializer.data)

        elif req.method == 'PUT':
            serializer = InclusionSerializer(inclusion, data=data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        elif req.method == 'DELETE':
            inclusion.delete()
            return HttpResponse(status=status.HTTP_204_NO_CONTENT)
