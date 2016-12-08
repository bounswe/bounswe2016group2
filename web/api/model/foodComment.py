from django.db import models

from api.model.food import Food
from django.contrib.auth.models import User


class FoodComment(models.Model):

    comment = models.CharField(max_length=255)
    photo = models.URLField(max_length=255, null=True, blank=True)

    user = models.ForeignKey(User, on_delete=models.CASCADE)
    food = models.ForeignKey(Food, on_delete=models.CASCADE, null=True, blank=True)

    class Meta:
        unique_together = ('user', 'food')
