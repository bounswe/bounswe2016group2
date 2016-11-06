from django.db import models

from rest_framework import serializers

from api.model.food import Food
from api.model.ingredient import Ingredient


class Inclusion(models.Model):
    food = models.ForeignKey(Food, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    weight = models.FloatField()


class InclusionSerializer(serializers.ModelSerializer):

    class Meta:
        model = Inclusion
        fields = ()
