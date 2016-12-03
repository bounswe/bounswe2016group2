from rest_framework import serializers

from api.model.ingredient import Ingredient


class IngredientSerializer(serializers.ModelSerializer):

    slug = serializers.SlugField(read_only=True, allow_null=True)

    energy = serializers.FloatField(min_value=0)

    protein = serializers.FloatField(min_value=0)
    carb = serializers.FloatField(min_value=0)
    fat = serializers.FloatField(min_value=0)

    measureValue = serializers.FloatField(default=100, min_value=0, required=False)
    measureUnit = serializers.CharField(default='g')
    defaultValue = serializers.FloatField(min_value=0, required=False)
    defaultUnit = serializers.CharField(default='g')

    saturatedFat = serializers.FloatField(min_value=0, default=0)
    sugar = serializers.FloatField(min_value=0, default=0)
    fibre = serializers.FloatField(min_value=0, default=0)
    cholesterol = serializers.FloatField(min_value=0, default=0)
    calcium = serializers.FloatField(min_value=0, default=0)
    iron = serializers.FloatField(min_value=0, default=0)
    sodium = serializers.FloatField(min_value=0, default=0)
    potassium = serializers.FloatField(min_value=0, default=0)
    magnesium = serializers.FloatField(min_value=0, default=0)
    phosphorus = serializers.FloatField(min_value=0, default=0)
    thiamin = serializers.FloatField(min_value=0, default=0)
    riboflavin = serializers.FloatField(min_value=0, default=0)
    niacin = serializers.FloatField(min_value=0, default=0)
    folate = serializers.FloatField(min_value=0, default=0)

    class Meta:
        model = Ingredient
        fields = '__all__'
        depth = 1
