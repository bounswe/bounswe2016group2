from django.conf.urls import url, include
from django.conf.urls.static import static
from django.contrib import admin
from django.shortcuts import render

from web import settings

urlpatterns = [
    url(r'^api/', include('api.urls')),
    url(r'^$', lambda req: render(req, 'index.html')),
    url(r'^addFood/', lambda req: render(req, 'addFood.html')),
    url(r'^admin/', admin.site.urls),
]