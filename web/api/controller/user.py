from django.contrib.auth import authenticate, login, logout
from django.core.exceptions import ObjectDoesNotExist
from django.db import IntegrityError as DjangoIntegrityError
from django.http import HttpResponse
from django.http import HttpResponseNotFound
from django.http import JsonResponse
from django.utils.datastructures import MultiValueDictKeyError
from django.utils.text import slugify
from django.views.decorators.csrf import csrf_exempt

from api.service import user as UserService
from api.service.response import JsonResponseBadRequest, HttpResponseUnauthorized


@csrf_exempt
def all(req):
    users = UserService.all()
    return JsonResponse(users, safe=False)

@csrf_exempt
def detail(req, id):
    try:
        user = UserService.get(id)
    except ObjectDoesNotExist:
        return HttpResponseNotFound()
    return JsonResponse(UserService.toDict(user), safe=False)

@csrf_exempt
def create(req):
    try:
        user = UserService.create(req.POST['email'], req.POST['password'], req.POST.get('firstName', ''), req.POST.get('lastName', ''))
        user.save()
        return JsonResponse(UserService.toDict(user))
    except MultiValueDictKeyError as e:
        return JsonResponseBadRequest({slugify(e): JsonResponseBadRequest.required})
    except ValueError as e:
        return JsonResponseBadRequest({'email': JsonResponseBadRequest.required})
    except DjangoIntegrityError:
        return JsonResponseBadRequest({'email': JsonResponseBadRequest.taken})

@csrf_exempt
def delete(req, id):
    try:
        user = UserService.delete(id)
        return JsonResponse(UserService.toDict(user), safe=False)
    except ObjectDoesNotExist:
        return HttpResponseNotFound()


@csrf_exempt
def signin(req):
    try:
        user = authenticate(username=req.POST['email'], password=req.POST['password'])
        if user is not None:
            login(req, user)
            return JsonResponse(UserService.toDict(user), safe=False)
        else:
            return HttpResponseUnauthorized()
    except MultiValueDictKeyError as e:
        return JsonResponseBadRequest({slugify(e): JsonResponseBadRequest.required})

@csrf_exempt
def signout(req):
    logout(req)
    return HttpResponse()



