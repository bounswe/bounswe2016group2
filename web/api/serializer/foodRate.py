from rest_framework import serializers

from api.model.foodRate import FoodRate
from api.model.food import Food
from django.contrib.auth.models import User
from api.serializer.user import UserSerializer


class FoodRateSerializer(serializers.ModelSerializer):

    score = serializers.FloatField(min_value=0, max_value=5)

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())

    class Meta:
        model = FoodRate
        fields = '__all__'
        depth = 1


class FoodRateReadSerializer(serializers.ModelSerializer):

    user = UserSerializer()

    class Meta:
        model = FoodRate
        fields = '__all__'
        depth = 1
