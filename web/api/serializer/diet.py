from rest_framework import serializers

from api.model.diet import Diet
from api.model.ingredient import Ingredient
from api.serializer.ingredient import IngredientPureSerializer


class DietReadSerializer(serializers.ModelSerializer):

    ingredients = IngredientPureSerializer(many=True)

    class Meta:
        model = Diet
        fields = (
            'id', 'ingredients', 'name', 'description',
            'minEnergy', 'maxEnergy',
            'minProteinVal', 'maxProteinVal', 'minCarbVal', 'maxCarbVal', 'minFatVal', 'maxFatVal',
            'minProteinRate', 'maxProteinRate', 'minCarbRate', 'maxCarbRate', 'minFatRate', 'maxFatRate'
        )
        depth = 2


class DietSerializer(serializers.ModelSerializer):

    slug = serializers.SlugField(read_only=True, allow_null=True)

    minEnergy = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxEnergy = serializers.FloatField(min_value=0, max_value=9999, default=9999)

    minProteinVal = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxProteinVal = serializers.FloatField(min_value=0, max_value=9999, default=9999)
    minCarbVal = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxCarbVal = serializers.FloatField(min_value=0, max_value=9999, default=9999)
    minFatVal = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxFatVal = serializers.FloatField(min_value=0, max_value=9999, default=9999)

    minProteinRate = serializers.FloatField(min_value=0, max_value=1, default=0)
    maxProteinRate = serializers.FloatField(min_value=0, max_value=1, default=1)
    minCarbRate = serializers.FloatField(min_value=0, max_value=1, default=0)
    maxCarbRate = serializers.FloatField(min_value=0, max_value=1, default=1)
    minFatRate = serializers.FloatField(min_value=0, max_value=1, default=0)
    maxFatRate = serializers.FloatField(min_value=0, max_value=1, default=1)

    ingredients = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all(), many=True)

    class Meta:
        model = Diet
        fields = '__all__'
        depth = 1


class DietFilterSerializer(serializers.ModelSerializer):

    name = serializers.SlugField(read_only=True, allow_null=True)
    slug = serializers.SlugField(read_only=True, allow_null=True)

    minEnergy = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxEnergy = serializers.FloatField(min_value=0, max_value=9999, default=9999)

    minProteinVal = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxProteinVal = serializers.FloatField(min_value=0, max_value=9999, default=9999)
    minCarbVal = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxCarbVal = serializers.FloatField(min_value=0, max_value=9999, default=9999)
    minFatVal = serializers.FloatField(min_value=0, max_value=9999, default=0)
    maxFatVal = serializers.FloatField(min_value=0, max_value=9999, default=9999)

    minProteinRate = serializers.FloatField(min_value=0, max_value=1, default=1)
    maxProteinRate = serializers.FloatField(min_value=0, max_value=1, default=0)
    minCarbRate = serializers.FloatField(min_value=0, max_value=1, default=1)
    maxCarbRate = serializers.FloatField(min_value=0, max_value=1, default=0)
    minFatRate = serializers.FloatField(min_value=0, max_value=1, default=0)
    maxFatRate = serializers.FloatField(min_value=0, max_value=1, default=1)

    ingredients = serializers.PrimaryKeyRelatedField(queryset=Ingredient.objects.all(), many=True, default=[])

    class Meta:
        model = Diet
        fields = '__all__'
        depth = 1
