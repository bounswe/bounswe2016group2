from django.db import models

from api.model.food import Food
from django.contrib.auth.models import User


class AteFood(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    food = models.ForeignKey(Food, on_delete=models.CASCADE)

    value = models.FloatField()
    created = models.DateTimeField(auto_now_add=True)
