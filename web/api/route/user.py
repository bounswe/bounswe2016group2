from django.conf.urls import url
from django.contrib import admin
from api.controller import user

urlpatterns = [
    url(r'^detail/(?P<id>\d+)$', user.detail),
    url(r'^all$', user.all),
    url(r'^create$', user.create),
    url(r'^delete/(?P<id>\d+)$', user.delete),
    url(r'^signin$', user.signin),
    url(r'^signout$', user.signout),
    url(r'^changePassword$', user.changePassword),
    url(r'^admin/', admin.site.urls),
]
