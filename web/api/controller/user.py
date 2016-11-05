from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout, update_session_auth_hash
from django.http import HttpResponse
from django.http import JsonResponse
from django.utils.datastructures import MultiValueDictKeyError
from django.utils.text import slugify
from django.views.decorators.csrf import csrf_exempt

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.model.user import UserSerializer
from api.service import user as UserService
from api.service.response import JsonResponseBadRequest, HttpResponseUnauthorized


@api_view(['POST'])
def signup(req):
    if ('email' in req.POST):
        req.POST['username'] = req.POST['email']
    serializer = UserSerializer(data=req.data)
    if serializer.is_valid():
        serializer.save()
        return Response(serializer.data, status=status.HTTP_201_CREATED)
    else:
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['POST'])
def signin(req):
    try:
        user = authenticate(username=req.POST.get('email', ''), password=req.POST.get('password'))
        if user is not None:
            login(req, user)
            return JsonResponse(UserService.toDict(user), safe=False)
        else:
            return HttpResponse(status.HTTP_400_BAD_REQUEST)
    except MultiValueDictKeyError as e:
        return JsonResponseBadRequest({slugify(e): JsonResponseBadRequest.required})


@api_view(['GET', 'POST'])
def signout(req):
    logout(req)
    return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['GET'])
def users(req):
    users = User.objects.all()
    ser = UserSerializer(users, many=True)
    return Response(ser.data)


@api_view(['GET', 'PUT', 'DELETE'])
def user(req, userId):
    try:
        user = User.objects.get(id=userId)
    except User.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = UserSerializer(user)
        return Response(serializer.data)

    elif req.method == 'PUT':
        serializer = UserSerializer(user, data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif req.method == 'DELETE':
        user.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@csrf_exempt
def changePassword(req):
    try:
        UserService.checkIsLoggedIn(req.user)
        oldPassword = req.POST['old_password']
        newPassword = req.POST['new_password']
        if req.user.check_password(oldPassword):
            user = req.user
            user.set_password(newPassword)
            user.save()
            update_session_auth_hash(req, user)
            return JsonResponse(UserService.toDict(user), safe=False)
        else:
            return JsonResponseBadRequest({'old_password': JsonResponseBadRequest.invalid})
    except MultiValueDictKeyError as e:
        return JsonResponseBadRequest({slugify(e): JsonResponseBadRequest.required})
    except PermissionError as e:
        return HttpResponseUnauthorized()
