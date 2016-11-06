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

    class Meta:
        model = Ingredient
        fields = ('id', 'name')
