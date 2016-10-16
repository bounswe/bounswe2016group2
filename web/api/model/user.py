import datetime

from mongoengine import *

class User(Document):
    name = StringField(max_length=200, required=True)
    createdAt = DateTimeField(default=datetime.datetime.now)
