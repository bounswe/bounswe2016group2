from django.db import models

from api.model.restaurant import Restaurant
from django.contrib.auth.models import User


class RestaurantRate(models.Model):

    score = models.FloatField()

    user = models.ForeignKey(User, on_delete=models.CASCADE)
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE)

    class Meta:
        unique_together = ('user', 'restaurant')
