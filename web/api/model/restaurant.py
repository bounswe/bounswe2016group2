from django.db import models
from django.utils.text import slugify

from django.contrib.auth.models import User


class Restaurant(models.Model):
    name = models.CharField(max_length=64, unique=True)
    slug = models.SlugField(max_length=64, unique=True)
    photo = models.URLField(max_length=255, null=True, blank=True)

    user = models.ForeignKey(User, on_delete=models.CASCADE, null=True)

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Restaurant, self).save(*args, **kwargs)
