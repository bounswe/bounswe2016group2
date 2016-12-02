from django.db import models
from django.utils.text import slugify

from api.model.ingredient import Ingredient


class Food(models.Model):
    name = models.CharField(max_length=64, unique=True)
    slug = models.SlugField(max_length=64, unique=True)
    photo = models.URLField(max_length=255, null=True, blank=True)

    ingredients = models.ManyToManyField(Ingredient, through='Inclusion')

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Food, self).save(*args, **kwargs)
