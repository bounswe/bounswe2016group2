from django.conf.urls import url
from api.controller import micronutrient

urlpatterns = [
    url(r'^all$', micronutrient.all, name='all'),
    url(r'^create$', micronutrient.create, name='create'),
    url(r'^delete$', micronutrient.delete, name='delete'),
    url(r'^deleteAll$', micronutrient.deleteAll, name='deleteAll'),
]
