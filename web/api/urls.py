from django.conf.urls import url, include
from django.contrib import admin

urlpatterns = [
    url(r'^user/', include('api.route.user')),
    url(r'^micronutrient/', include('api.route.micronutient')),
    url(r'^admin/', admin.site.urls)
]
