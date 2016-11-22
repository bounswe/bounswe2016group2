from django.db import models

from rest_framework import serializers

from api.model.ingredient import Ingredient
from django.contrib.auth.models import User


class AteIngredient(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)

    value = models.FloatField()
    unit = models.CharField(max_length=32)
    created = models.DateTimeField(auto_now_add=True)


class AteIngredientSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    ingredient = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all())
    value = serializers.FloatField(min_value=0)
    unit = serializers.CharField(default='g')

    class Meta:
        model = AteIngredient
        fields = ('user', 'ingredient', 'value', 'unit')
