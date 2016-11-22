from django.http import HttpResponse

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.ateIngredient import AteIngredient, AteIngredientSerializer
from api.model.ingredient import Ingredient


@api_view(['GET', 'POST', 'DELETE'])
def ateIngredient(req, ingredientId):
    """
    Create or delete the existence of eating status of ingredient
    """
    data = {
        'ingredient': ingredientId,
        'user': req.user.id
    }
    if 'value' in req.data:
        data['value'] = req.data['value']
    if 'unit' in req.data:
        data['unit'] = req.data['unit']

    try:
        ingredient = Ingredient.objects.get(id=ingredientId)
        data['ingredient'] = ingredient.id
    except Ingredient.DoesNotExist:
        print('ammk')
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'POST':
        serializer = AteIngredientSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    else:
        # find the relation if request is not POST
        try:
            ateIngredient = AteIngredient.objects.get(user=req.user.id, ingredient=ingredient.id)
        except AteIngredient.DoesNotExist:
            return HttpResponse(status=status.HTTP_404_NOT_FOUND)

        if req.method == 'GET':
            serializer = AteIngredientSerializer(ateIngredient)
            return Response(serializer.data)

        elif req.method == 'DELETE':
            ateIngredient.delete()
            return HttpResponse(status=status.HTTP_204_NO_CONTENT)
