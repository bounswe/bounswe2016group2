from rest_framework import serializers

from api.model.ingredient import Ingredient
from api.model.ateIngredient import AteIngredient
from api.serializer.ingredient import IngredientSerializer
from django.contrib.auth.models import User


class AteIngredientSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    ingredient = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all())
    value = serializers.FloatField(min_value=0)
    unit = serializers.CharField(default='g')

    class Meta:
        model = AteIngredient
        fields = ('user', 'ingredient', 'value', 'unit')


class AteIngredientDetailSerializer(serializers.ModelSerializer):
    ingredient = IngredientSerializer()

    class Meta:
        model = AteIngredient
        fields = ('ingredient', 'value', 'unit', 'created')
