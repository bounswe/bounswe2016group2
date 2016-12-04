from rest_framework import serializers

from api.model.restaurant import Restaurant
from django.contrib.auth.models import User


class RestaurantSerializer(serializers.ModelSerializer):

    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Restaurant
        fields = '__all__'
        depth = 1
