from django.conf.urls import url
from django.contrib import admin
from api.controller import user

urlpatterns = [
    url(r'^detail/(?P<id>\d+)$', user.detail, name='detail'),
    url(r'^all$', user.all, name='all'),
    url(r'^create$', user.create, name='create'),
    url(r'^signin$', user.signin, name='create'),
    url(r'^signout$', user.signout, name='create'),
    url(r'^admin/', admin.site.urls),
]
