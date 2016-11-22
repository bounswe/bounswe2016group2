from django.db import models

from rest_framework import serializers

from api.model.food import Food
from django.contrib.auth.models import User


class AteFood(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    food = models.ForeignKey(Food, on_delete=models.CASCADE)

    value = models.FloatField()
    created = models.DateTimeField(auto_now_add=True)


class AteFoodSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())
    value = serializers.FloatField(min_value=0)

    class Meta:
        model = AteFood
        fields = ('user', 'food', 'value')
