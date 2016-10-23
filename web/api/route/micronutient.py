from django.conf.urls import url
from api.controller import micronutrient

urlpatterns = [
    url(r'^all$', micronutrient.all, name='all'),
    url(r'^create$', micronutrient.create, name='create'),
    url(r'^createDefaults$', micronutrient.createDefaults, name='createDefaults'),
    url(r'^delete/(?P<id>\d+)$', micronutrient.deleteById, name='delete'),
    url(r'^delete/(?P<slug>[\w-]+)$', micronutrient.deleteBySlug, name='delete'),
    url(r'^deleteAll$', micronutrient.deleteAll, name='deleteAll'),
]
