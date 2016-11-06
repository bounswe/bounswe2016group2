from django.db import models
from django.utils.text import slugify

from rest_framework import serializers

from api.model.ingredient import Ingredient


class Food(models.Model):
    name = models.CharField(max_length=64, unique=True)
    slug = models.SlugField(max_length=64, unique=True)
    calory = models.IntegerField()

    ingredients = models.ManyToManyField(Ingredient, through='Inclusion')

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Food, self).save(*args, **kwargs)


class FoodSerializer(serializers.ModelSerializer):

    class Meta:
        model = Food
        fields = ('id', 'name')
