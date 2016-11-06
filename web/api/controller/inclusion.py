from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

# from api.model.food import Food, FoodSerializer
from api.model.inclusion import Inclusion, InclusionSerializer


@api_view(['GET', 'POST', 'PUT', 'DELETE'])
def inclusion(req, food, ingredient):
    """

    """
    # req.data['food'] = foodId
    # req.data['ingredient'] = ingredientId
    if req.method == 'POST':
        serializer = InclusionSerializer(data=req.data)
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
            req.data['food'] = food
            req.data['ingredient'] = ingredient
            serializer = InclusionSerializer(inclusion, data=req.data)
            if serializer.is_valid():
                serializer.save()
                return Response(serializer.data)
            else:
                return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

        elif req.method == 'DELETE':
            inclusion.delete()
            return HttpResponse(status=status.HTTP_204_NO_CONTENT)
