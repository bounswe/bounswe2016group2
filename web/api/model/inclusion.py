from django.db import models

from rest_framework import serializers

from api.model.food import Food
from api.model.ingredient import Ingredient


class Inclusion(models.Model):
    food = models.ForeignKey(Food, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)
    weight = models.FloatField()

    class Meta:
        unique_together = ('food', 'ingredient')


class InclusionSerializer(serializers.ModelSerializer):

    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())
    ingredient = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all())
    weight = serializers.FloatField(min_value=0)

    class Meta:
        model = Inclusion
        fields = ('food', 'ingredient', 'weight')
