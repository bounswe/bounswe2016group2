from django.conf.urls import url, include
from django.contrib import admin

from api.controller import micronutrient

urlpatterns = [
    url(r'^user/', include('api.route.user')),

    url(r'^micronutrients$', micronutrient.micronutrients),
    url(r'^micronutrients/(?P<micronutrientId>[0-9]+)$', micronutrient.micronutrient),
    url(r'^micronutrients/(?P<slug>[\w-]+)$', micronutrient.slug),
    url(r'^micronutrientsDefaults$', micronutrient.createDefaults),
    url(r'^micronutrientsAll$', micronutrient.deleteAll),

    url(r'^admin/', admin.site.urls)
]
