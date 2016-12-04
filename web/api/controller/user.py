from django.contrib.auth.models import User
from django.contrib.auth import authenticate
from django.http import HttpResponse
from django.utils.datastructures import MultiValueDictKeyError

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.serializer.user import UserSerializer, UserReadSerializer
from api.model.ateFood import AteFood
from api.serializer.ateFood import AteFoodDetailSerializer
from api.model.ateIngredient import AteIngredient
from api.serializer.ateIngredient import AteIngredientDetailSerializer
from api.service import user as UserService


@api_view(['POST'])
def signup(req):
    """
    Create user and return
    """
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
    """
    authenticate user with email and password, return token
    """
    serializer = UserSerializer(data=req.data)
    data = serializer.initial_data
    print(req.data)
    if 'email' in data and 'password' in data:
        user = authenticate(
            username=serializer.initial_data['email'],
            password=serializer.initial_data['password']
        )
        if user is not None:
            token = UserService.refreshToken(user)
            return Response({'token': token.key})
    return Response({}, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'POST'])
def signout(req):
    """
    delete user token from database
    """
    if req.user:
        UserService.deleteToken(req.user)
    return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['GET', 'POST'])
def me(req):
    """
    get current user if authenticated
    """
    user = User.objects.get(id=req.user.id)
    serializer = UserReadSerializer(user)
    return Response(serializer.data)


@api_view(['GET'])
def users(req):
    """
    retrieve all users
    """
    users = User.objects.all()
    ser = UserSerializer(users, many=True)
    return Response(ser.data)


@api_view(['GET', 'PUT', 'DELETE'])
def user(req, userId):
    """
    retrieve, modify or delete single user by id
    """
    try:
        user = User.objects.get(id=userId)
    except User.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        serializer = UserReadSerializer(user)
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


@api_view(['POST'])
def changePassword(req):
    """
    change user password
    """
    try:
        oldPassword = req.POST['old_password']
        newPassword = req.POST['new_password']
        if req.user.check_password(oldPassword):
            user = req.user
            user.set_password(newPassword)
            serializer = UserSerializer(user)
            if serializer.is_valid():
                serializer.save()
                return HttpResponse(status=status.HTTP_204_NO_CONTENT)
            else:
                return Response(user.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
            return HttpResponse(status=status.HTTP_401_UNAUTHORIZED)
    except MultiValueDictKeyError as e:
        return HttpResponse()
    except PermissionError as e:
        return HttpResponse(status=status.HTTP_401_UNAUTHORIZED)


@api_view(['GET'])
def history(req):
    """
    get current user's eaten food and ingredient history
    """
    ateFoods = AteFood.objects.filter(user=req.user.id)
    ateIngredients = AteIngredient.objects.filter(user=req.user.id)
    foodSerializer = AteFoodDetailSerializer(ateFoods, many=True)
    ingredientSerializer = AteIngredientDetailSerializer(ateIngredients, many=True)
    return Response({
        'foods': foodSerializer.data,
        'ingredients': ingredientSerializer.data
    })
