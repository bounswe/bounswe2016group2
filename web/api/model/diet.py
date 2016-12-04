from django.db import models
from django.utils.text import slugify

from api.model.ingredient import Ingredient
from django.contrib.auth.models import User


class Diet(models.Model):
    name = models.CharField(max_length=64, unique=True)
    slug = models.SlugField(max_length=64, unique=True)
    description = models.CharField(max_length=255, null=True, blank=True)

    minEnergy = models.FloatField()  # kcal
    maxEnergy = models.FloatField()  # kcal

    minProteinVal = models.FloatField()
    maxProteinVal = models.FloatField()
    minCarbVal = models.FloatField()
    maxCarbVal = models.FloatField()
    minFatVal = models.FloatField()
    maxFatVal = models.FloatField()

    minProteinRate = models.FloatField()
    maxProteinRate = models.FloatField()
    minCarbRate = models.FloatField()
    maxCarbRate = models.FloatField()
    minFatRate = models.FloatField()
    maxFatRate = models.FloatField()

    ingredients = models.ManyToManyField(Ingredient)
    user = models.ManyToManyField(User)

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Diet, self).save(*args, **kwargs)
