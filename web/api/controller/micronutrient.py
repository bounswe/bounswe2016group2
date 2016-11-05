from django.http import HttpResponse
from django.utils.text import slugify

from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response

from api.service import micronutrient as MicroService
from api.model.micronutrient import Micronutrient, MicronutrientSerializer


@api_view(['GET', 'POST'])
def micronutrients(req):
    """
    Get all micronutrients, or create a new one
    """
    if req.method == 'GET':
        micros = Micronutrient.objects.all()
        ser = MicronutrientSerializer(micros, many=True)
        return Response(ser.data)

    elif req.method == 'POST':
        if 'name' in req.POST:
            req.POST['slug'] = slugify(req.POST['name'])
        serializer = MicronutrientSerializer(data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET', 'PUT', 'DELETE'])
def micronutrient(req, micronutrientId):
    """
    Retrive, modify or delete single micronutrient by id
    """
    try:
        micro = Micronutrient.objects.get(id=micronutrientId)
    except Micronutrient.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if 'name' in req.POST:
        req.POST['slug'] = slugify(req.POST['name'])

    if req.method == 'GET':
        ser = MicronutrientSerializer(micro)
        return Response(ser.data)

    elif req.method == 'PUT':
        serializer = MicronutrientSerializer(micro, data=req.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif req.method == 'DELETE':
        micro.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['GET', 'DELETE'])
def slug(req, slug):
    """
    Retrive or delete single micronutrient by slug
    """
    try:
        micro = Micronutrient.objects.get(slug=slug)
    except Micronutrient.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if req.method == 'GET':
        ser = MicronutrientSerializer(micro)
        return Response(ser.data)

    elif req.method == 'DELETE':
        micro.delete()
        return HttpResponse(status=status.HTTP_204_NO_CONTENT)


@api_view(['POST'])
def createDefaults(req):
    """
    Add default micronutrients in mocks to database
    """
    MicroService.createDefaults()
    return HttpResponse(status=status.HTTP_201_CREATED)


@api_view(['DELETE'])
def deleteAll(req):
    """
    Flush micronutrient table, delete all
    """
    MicroService.deleteAll()
    return HttpResponse(status=status.HTTP_204_NO_CONTENT)
