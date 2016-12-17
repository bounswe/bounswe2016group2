from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.ateFood import AteFood
from api.serializer.ateFood import AteFoodSerializer
from api.model.food import Food


@api_view(['GET', 'POST', 'DELETE'])
def ateFood(req, foodId):
    """
    Create or delete the existence of eating status of food
    """
    data = {
        'food': foodId,
        'user': req.user.id
    }
    if 'value' in req.data:
        data['value'] = req.data['value']
    if 'unit' in req.data:
        data['unit'] = req.data['unit']

    try:
        food = Food.objects.get(id=foodId)
        data['food'] = food.id
    except Food.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'POST':
        serializer = AteFoodSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    else:
        # find the relation if request is not POST
        try:
            ateFood = AteFood.objects.get(user=req.user.id, food=food.id)
        except AteFood.DoesNotExist:
            return HttpResponse(status=status.HTTP_404_NOT_FOUND)

        if req.method == 'GET':
            serializer = AteFoodSerializer(ateFood)
            return Response(serializer.data)

        elif req.method == 'DELETE':
            ateFood.delete()
            return HttpResponse(status=status.HTTP_204_NO_CONTENT)
