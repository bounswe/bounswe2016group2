from django.db import models
from django.utils.text import slugify

from rest_framework import serializers


class Micronutrient(models.Model):
    name = models.CharField(max_length=100)
    slug = models.CharField(max_length=100, unique=True)

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Micronutrient, self).save(*args, **kwargs)


class MicronutrientSerializer(serializers.ModelSerializer):

    class Meta:
        model = Micronutrient
        fields = ('id', 'name', 'slug')
