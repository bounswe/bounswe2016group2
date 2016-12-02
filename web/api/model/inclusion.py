from django.db import models

from api.model.food import Food
from api.model.ingredient import Ingredient


class Inclusion(models.Model):
    food = models.ForeignKey(Food, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)

    value = models.FloatField()
    unit = models.CharField(max_length=32)

    class Meta:
        unique_together = ('food', 'ingredient')
