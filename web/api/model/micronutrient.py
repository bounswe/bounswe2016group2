from django.db import models
from django.utils.text import slugify

class Micronutrient(models.Model):
    name = models.CharField(max_length=100)
    slug = models.CharField(max_length=100, unique=True)

    def save(self, *args, **kwargs):
        self.slug = slugify(self.name)
        self.full_clean()

        super(Micronutrient, self).save(*args, **kwargs)
