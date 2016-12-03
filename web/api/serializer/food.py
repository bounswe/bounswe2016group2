from rest_framework import serializers

from api.model.food import Food
from api.serializer.inclusion import InclusionSerializer, InclusionReadSerializer


class FoodSerializer(serializers.ModelSerializer):

    inclusions = InclusionSerializer(source='inclusion_set', many=True)
    slug = serializers.SlugField(read_only=True, allow_null=True)

    class Meta:
        model = Food
        fields = '__all__'
        depth = 1


class FoodReadSerializer(serializers.ModelSerializer):

    inclusions = InclusionReadSerializer(source='inclusion_set', many=True)

    class Meta:
        model = Food
        fields = '__all__'
        depth = 2
