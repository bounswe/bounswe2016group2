from rest_framework import serializers

from api.model.restaurant import Restaurant
from api.model.food import Food
from django.contrib.auth.models import User


class SingleFoodSerializer(serializers.ModelSerializer):
    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Food
        fields = '__all__'
        depth = 1


class RestaurantSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all(), required=False)
    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Restaurant
        fields = '__all__'
        depth = 1


class RestaurantDetailSerializer(serializers.ModelSerializer):
    foods = SingleFoodSerializer(source='food_set', many=True)

    class Meta:
        model = Restaurant
        fields = '__all__'
        depth = 1
