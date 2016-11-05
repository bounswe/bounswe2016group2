from django.core.exceptions import ValidationError
from django.forms import model_to_dict
from django.http import Http404
from django.http import HttpResponse
from django.http import HttpResponseNotFound
from django.http import JsonResponse
from django.utils.datastructures import MultiValueDictKeyError
from django.views.decorators.csrf import csrf_exempt

from rest_framework.parsers import JSONParser
from rest_framework.renderers import JSONRenderer

from api.service import micronutrient as MicroService
from api.service.response import JsonResponseBadRequest
from api.model.micronutrient import Micronutrient, MicronutrientSerializer


@csrf_exempt
def micronutrients(req):
    if req.method == 'GET':
        micros = Micronutrient.objects.all()
        serializer = MicronutrientSerializer(micros, many=True)
        return JsonResponse(serializer.data, status=200, safe=False)
    elif req.method == 'POST':
        data = JSONParser().parse(req)
        serializer = MicronutrientSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            return JsonResponse(serializer.data, status=201)
        return JsonResponse(serializer.errors, status=400)


@csrf_exempt
def micronutrient(req, micronutrientId):
    try:
        micro = Micronutrient.objects.get(id=micronutrientId)
    except Micronutrient.DoesNotExists:
        return HttpResponse(status=404)

    if req.method == 'GET':
        ser = MicronutrientSerializer(micro)
        return JsonResponse(ser.data)

    elif req.method == 'PUT':
        data = JSONParser().parse(req)
        ser = MicronutrientSerializer(micro, data)
        if ser.is_valid():
            ser.save()
            return JsonResponse(ser.data, status=200)
        else:
            return JsonResponse(ser.errors, status=400)

    elif req.method == 'DELETE':
        micro.delete()
        return JsonResponse(ser.data, status=204)


@csrf_exempt
def all(req):
    micros = list(MicroService.all())
    return JsonResponse(micros, safe=False)

@csrf_exempt
def create(req):
    try:
        micro = MicroService.create(req.POST['name'])
        return JsonResponse(model_to_dict(micro), safe=False)
    except ValidationError as e:
        e = dict(e)
        if 'slug' in dict(e):
            e = {'name': e['slug']}
        return JsonResponseBadRequest(e)
    except MultiValueDictKeyError:
        return JsonResponseBadRequest({'name': JsonResponseBadRequest.required})

@csrf_exempt
def createDefaults(req):
    MicroService.createDefaults()
    return HttpResponse()

@csrf_exempt
def deleteById(req, id):
    try:
        MicroService.deleteById(id)
        return HttpResponse()
    except Http404:
        return HttpResponseNotFound()

@csrf_exempt
def deleteBySlug(req, slug):
    try:
        MicroService.deleteBySlug(slug)
        return HttpResponse()
    except Http404:
        return HttpResponseNotFound()

@csrf_exempt
def deleteAll(req):
    MicroService.deleteAll()
    return HttpResponse()
