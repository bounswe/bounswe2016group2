from django.db import models

from api.model.ingredient import Ingredient
from django.contrib.auth.models import User


class AteIngredient(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    ingredient = models.ForeignKey(Ingredient, on_delete=models.CASCADE)

    value = models.FloatField()
    unit = models.CharField(max_length=32)
    created = models.DateTimeField(auto_now_add=True)
