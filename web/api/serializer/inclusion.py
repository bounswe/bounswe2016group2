from rest_framework import serializers

from api.model.food import Food
from api.model.ingredient import Ingredient
from api.model.inclusion import Inclusion
from api.serializer.ingredient import IngredientSerializer


class InclusionSerializer(serializers.HyperlinkedModelSerializer):

    food = serializers.PrimaryKeyRelatedField(queryset=Food.objects.all())
    ingredient = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all())
    value = serializers.FloatField(min_value=0, default=1)
    unit = serializers.CharField(default='g')

    class Meta:
        model = Inclusion
        fields = ('food', 'ingredient', 'value', 'unit')


class InclusionReadSerializer(serializers.HyperlinkedModelSerializer):

    value = serializers.FloatField(min_value=0, default=1)
    unit = serializers.CharField(default='g')
    food = serializers.ReadOnlyField(source='food.id')
    ingredient = IngredientSerializer()

    class Meta:
        model = Inclusion
        fields = ('food', 'ingredient', 'value', 'unit')
