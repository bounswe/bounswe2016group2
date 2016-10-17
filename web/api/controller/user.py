import json

from _mysql_exceptions import IntegrityError as MysqlIntegrityError

from django.contrib.auth import authenticate, login, logout
from django.db import IntegrityError as DjangoIntegrityError
from django.http import Http404
from django.http import JsonResponse
from django.contrib.auth.models import User
from django.utils.datastructures import MultiValueDictKeyError
from django.views.decorators.csrf import csrf_exempt

from api.service import user as UserService

@csrf_exempt
def all(req):
    users = list(User.objects.values())
    return JsonResponse(users, safe=False)

@csrf_exempt
def detail(req, id):
    user = UserService.get(id)
    if not user:
        raise Http404
    return JsonResponse(user, safe=False)

@csrf_exempt
def create(req):
    try:
        user = User.objects.create_user(username=req.POST['email'], password=req.POST['password'])
        user.save()
        return JsonResponse({'message': 'user created'})
    except MultiValueDictKeyError:
        return JsonResponse({'message': 'email and password required'})
    except (DjangoIntegrityError, MysqlIntegrityError) :
        return JsonResponse({'message': 'email is taken'})

@csrf_exempt
def delete(req):
    try:
        id = req.POST['id']
        User.objects.get(id=id).delete()
        return JsonResponse({'message':'deleted'})
    except Exception:
        raise


@csrf_exempt
def signin(req):
    user = authenticate(username=req.POST['email'], password=req.POST['password'])
    if user is not None:
        login(req, user)
        return JsonResponse({'message':'login succesful'})
    else:
        return JsonResponse({'message':'invalid credentials'})

@csrf_exempt
def signout(req):
    logout(req)



