from django.conf.urls import url, include
from django.contrib import admin
from django.shortcuts import render

urlpatterns = [
    url(r'^api/', include('api.urls')),
    url(r'^', lambda req: render(req, 'index.html')),
    url(r'^admin/', admin.site.urls),
]
