from rest_framework import serializers

from api.model.foodComment import FoodComment
from api.model.food import Food
from django.contrib.auth.models import User
from api.serializer.user import UserSerializer


class FoodCommentSerializer(serializers.ModelSerializer):

    comment = serializers.CharField(max_length=255)
    photo = serializers.CharField(max_length=255, allow_null=True, required=False)

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())

    class Meta:
        model = FoodComment
        fields = '__all__'
        depth = 1


class FoodCommentReadSerializer(serializers.ModelSerializer):

    user = UserSerializer()

    class Meta:
        model = FoodComment
        fields = '__all__'
        depth = 1


class FoodCommentPureSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())

    class Meta:
        model = FoodComment
        fields = ('comment', 'user', 'food')
        depth = 1
