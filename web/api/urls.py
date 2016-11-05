from django.conf.urls import url
from django.contrib import admin

from api.controller import micronutrient
from api.controller import user

urlpatterns = [
    # USER ROUTES
    url(r'^users/signup$', user.signup),
    url(r'^users/signin$', user.signin),
    url(r'^users/signout$', user.signout),
    url(r'^users/me$', user.me),
    url(r'^users$', user.users),
    url(r'^users/(?P<userId>[0-9]+)$', user.user),
    # MICRONUTRIENT ROUTES
    url(r'^micronutrients$', micronutrient.micronutrients),
    url(r'^micronutrients/(?P<micronutrientId>[0-9]+)$', micronutrient.micronutrient),
    url(r'^micronutrients/(?P<slug>[\w-]+)$', micronutrient.slug),
    url(r'^micronutrientsDefaults$', micronutrient.createDefaults),
    url(r'^micronutrientsAll$', micronutrient.deleteAll),
    # ADMIN ROUTES
    url(r'^admin/', admin.site.urls)
]
