from rest_framework import serializers

from api.model.food import Food
from api.model.ateFood import AteFood
from api.serializer.food import FoodSerializer
from django.contrib.auth.models import User


class AteFoodSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())
    value = serializers.FloatField(min_value=0)

    class Meta:
        model = AteFood
        fields = ('user', 'food', 'value', 'created')


class AteFoodDetailSerializer(serializers.ModelSerializer):
    food = FoodSerializer()

    class Meta:
        model = AteFood
        fields = ('food', 'value')
