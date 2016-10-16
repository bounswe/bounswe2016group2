from django.conf.urls import url
from django.contrib import admin

from api.controller import user

urlpatterns = [
    url(r'^all', user.all),
    url(r'^admin/', admin.site.urls),
]
