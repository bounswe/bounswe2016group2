from django.db import models

from api.model.food import Food
from django.contrib.auth.models import User


class FoodRate(models.Model):

    score = models.FloatField()

    user = models.ForeignKey(User, on_delete=models.CASCADE)
    food = models.ForeignKey(Food, on_delete=models.CASCADE)

    class Meta:
        unique_together = ('user', 'food')
