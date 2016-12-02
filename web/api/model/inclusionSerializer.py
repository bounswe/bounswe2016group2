from rest_framework import serializers

from api.model.food import Food
from api.model.ingredient import Ingredient
from api.model.ingredient import Inclusion


class InclusionSerializer(serializers.ModelSerializer):

    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())
    ingredient = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all())
    value = serializers.FloatField(min_value=0, default=1)
    unit = serializers.CharField(default='g')

    class Meta:
        model = Inclusion
        fields = ('food', 'ingredient', 'value', 'unit')
