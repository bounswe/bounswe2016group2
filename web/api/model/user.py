import datetime

from django.db import models

class User():
    first_name = models.CharField(max_length=50)
    last_name = models.CharField(max_length=50)
    created_at = models.DateTimeField(default=datetime.datetime.now)
