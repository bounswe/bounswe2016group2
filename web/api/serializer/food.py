from rest_framework import serializers

from api.model.food import Food


class FoodSerializer(serializers.ModelSerializer):

    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Food
        fields = '__all__'
        depth = 1
