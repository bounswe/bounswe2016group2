from django.core.exceptions import ValidationError
from django.forms import model_to_dict
from django.http import Http404
from django.http import HttpResponse
from django.http import HttpResponseNotFound
from django.http import JsonResponse
from django.utils.datastructures import MultiValueDictKeyError
from django.views.decorators.csrf import csrf_exempt

from api.service import micronutrient as MicroService
from api.service.response import JsonResponseBadRequest

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
