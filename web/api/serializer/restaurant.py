from rest_framework import serializers

from api.model.restaurant import Restaurant
from api.model.food import Food
from django.contrib.auth.models import User

from api.serializer.restaurantComment import RestaurantCommentPureSerializer
from api.serializer.restaurantRate import RestaurantRatePureSerializer
from api.serializer.user import UserPureSerializer
from api.serializer.ingredient import IngredientPureSerializer


class FoodPureSerializer(serializers.ModelSerializer):

    ingredients = IngredientPureSerializer(many=True)

    class Meta:
        model = Food
        fields = ('id', 'name', 'description', 'photo', 'ingredients')


class RestaurantSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all(), required=False)
    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Restaurant
        fields = '__all__'
        depth = 1


class RestaurantDetailSerializer(serializers.ModelSerializer):
    foods = FoodPureSerializer(source='food_set', many=True)
    comments = RestaurantCommentPureSerializer(source='restaurantcomment_set', many=True)
    rates = RestaurantRatePureSerializer(source='restaurantrate_set', many=True)
    user = UserPureSerializer()

    class Meta:
        model = Restaurant
        fields = '__all__'
        depth = 1


class RestaurantPureSerializer(serializers.ModelSerializer):

    class Meta:
        model = Food
        fields = ('id', 'name')
