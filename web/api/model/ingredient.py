from django.db import models
from django.utils.text import slugify

from rest_framework import serializers


class Ingredient(models.Model):
    name = models.CharField(max_length=64, unique=True)
    slug = models.SlugField(max_length=64, unique=True)

    weight = models.FloatField()
    energy = models.FloatField()

    protein = models.FloatField()
    carb = models.FloatField()
    fat = models.FloatField()

    saturatedFat = models.FloatField()
    sugar = models.FloatField()
    fibre = models.FloatField()
    cholesterol = models.FloatField()
    calcium = models.FloatField()
    iron = models.FloatField()
    sodium = models.FloatField()
    potassium = models.FloatField()
    magnesium = models.FloatField()
    phosphorus = models.FloatField()
    thiamin = models.FloatField()
    riboflavin = models.FloatField()
    niacin = models.FloatField()
    folate = models.FloatField()

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Ingredient, self).save(*args, **kwargs)


class IngredientSerializer(serializers.ModelSerializer):
    weight = serializers.FloatField(min_value=0)
    energy = serializers.FloatField(min_value=0)

    protein = serializers.FloatField(min_value=0)
    carb = serializers.FloatField(min_value=0)
    fat = serializers.FloatField(min_value=0)

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
