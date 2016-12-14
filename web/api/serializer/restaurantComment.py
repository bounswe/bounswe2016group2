from rest_framework import serializers

from api.model.restaurantComment import RestaurantComment
from api.model.restaurant import Restaurant
from django.contrib.auth.models import User
from api.serializer.user import UserSerializer


class RestaurantCommentSerializer(serializers.ModelSerializer):

    comment = serializers.CharField(max_length=255)
    photo = serializers.CharField(max_length=255, allow_null=True, required=False)

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    restaurant = serializers.PrimaryKeyRelatedField(queryset=Restaurant.objects.all())

    class Meta:
        model = RestaurantComment
        fields = '__all__'
        depth = 1


class RestaurantCommentReadSerializer(serializers.ModelSerializer):

    user = UserSerializer()

    class Meta:
        model = RestaurantComment
        fields = '__all__'
        depth = 1


class RestaurantCommentPureSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    restaurant = serializers.PrimaryKeyRelatedField(queryset=Restaurant.objects.all())

    class Meta:
        model = RestaurantComment
        fields = ('comment', 'photo', 'user', 'restaurant')
        depth = 1
