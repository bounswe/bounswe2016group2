from django.db import models
from django.utils.text import slugify

from api.model.ingredient import Ingredient
from api.model.restaurant import Restaurant
from django.contrib.auth.models import User


class Food(models.Model):
    name = models.CharField(max_length=64, unique=True)
    slug = models.SlugField(max_length=64, unique=True)
    description = models.CharField(max_length=255, null=True, blank=True)
    photo = models.URLField(max_length=255, null=True, blank=True)

    ingredients = models.ManyToManyField(Ingredient, through='Inclusion')
    user = models.ForeignKey(User, on_delete=models.CASCADE, null=True, blank=True)
    restaurant = models.ForeignKey(Restaurant, on_delete=models.CASCADE, null=True, blank=True)

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Food, self).save(*args, **kwargs)
