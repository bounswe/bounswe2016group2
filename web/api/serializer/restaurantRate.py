from rest_framework import serializers

from api.model.restaurantRate import RestaurantRate
from api.model.restaurant import Restaurant
from django.contrib.auth.models import User
from api.serializer.user import UserSerializer


class RestaurantRateSerializer(serializers.ModelSerializer):

    score = serializers.FloatField(min_value=0, max_value=5)

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    restaurant = serializers.PrimaryKeyRelatedField(queryset=Restaurant.objects.all())

    class Meta:
        model = RestaurantRate
        fields = '__all__'
        depth = 1


class RestaurantRateReadSerializer(serializers.ModelSerializer):

    user = UserSerializer()

    class Meta:
        model = RestaurantRate
        fields = '__all__'
        depth = 1
