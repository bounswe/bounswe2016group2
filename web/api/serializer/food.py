from rest_framework import serializers

from api.model.food import Food
from api.model.restaurant import Restaurant
from django.contrib.auth.models import User
from api.serializer.ingredient import IngredientPureSerializer
from api.serializer.inclusion import InclusionReadSerializer
from api.serializer.restaurant import RestaurantSerializer
from api.serializer.foodComment import FoodCommentPureSerializer
from api.serializer.foodRate import FoodRatePureSerializer
from api.serializer.user import UserSerializer


class FoodSerializer(serializers.ModelSerializer):

    slug = serializers.SlugField(read_only=True, allow_null=True)
    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all(), required=False)
    restaurant = serializers.PrimaryKeyRelatedField(queryset=Restaurant.objects.all(), required=False)

    class Meta:
        model = Food
        fields = '__all__'
        depth = 1


class FoodReadSerializer(serializers.ModelSerializer):

    inclusions = InclusionReadSerializer(source='inclusion_set', many=True)
    comments = FoodCommentPureSerializer(source='foodcomment_set', many=True)
    rates = FoodRatePureSerializer(source='foodrate_set', many=True)
    restaurant = RestaurantSerializer()
    user = UserSerializer()

    class Meta:
        model = Food
        fields = '__all__'
        depth = 1


class FoodPureSerializer(serializers.ModelSerializer):

    ingredients = IngredientPureSerializer(many=True)
    rates = FoodRatePureSerializer(source='foodrate_set', many=True)

    class Meta:
        model = Food
        fields = ('id', 'name', 'description', 'photo', 'ingredients', 'rates')
