from rest_framework import serializers

from api.model.restaurant import Restaurant
from api.model.food import Food


class SingleFoodSerializer(serializers.ModelSerializer):
    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Food
        fields = '__all__'
        depth = 1


class RestaurantSerializer(serializers.ModelSerializer):

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
