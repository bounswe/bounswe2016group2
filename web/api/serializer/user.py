from django.contrib.auth.models import User

from rest_framework import serializers
from rest_framework.validators import UniqueValidator

from api.serializer.restaurant import RestaurantSerializer
from api.serializer.diet import DietReadSerializer


class UserSerializer(serializers.HyperlinkedModelSerializer):

    username = serializers.CharField(required=False)
    email = serializers.EmailField(validators=[UniqueValidator(queryset=User.objects.all())])
    password = serializers.CharField(write_only=True, min_length=6)

    def create(self, validated_data):
        user = User.objects.create(
            username=validated_data['email'],
            email=validated_data['email']
        )
        user.set_password(validated_data['password'])

        if 'first_name' in validated_data:
            user.first_name = validated_data['first_name']
        if 'last_name' in validated_data:
            user.last_name = validated_data['last_name']

        user.save()
        return user

    class Meta:
        model = User
        fields = ('email', 'username', 'password', 'first_name', 'last_name')


class UserReadSerializer(serializers.HyperlinkedModelSerializer):

    username = serializers.CharField(required=False, read_only=True)
    email = serializers.EmailField(validators=[UniqueValidator(queryset=User.objects.all())])
    restaurants = RestaurantSerializer(source='restaurant_set', many=True)
    diets = DietReadSerializer(source='diet_set', many=True)

    class Meta:
        model = User
        fields = ('email', 'username', 'password', 'first_name', 'last_name', 'restaurants', 'diets')
