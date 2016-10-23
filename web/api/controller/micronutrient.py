from _mysql_exceptions import IntegrityError as MysqlIntegrityError

from django.db import IntegrityError as DjangoIntegrityError
from django.forms import model_to_dict
from django.http import HttpResponse
from django.http import JsonResponse
from django.utils.datastructures import MultiValueDictKeyError
from django.views.decorators.csrf import csrf_exempt

from api.service import micronutrient as MicroService

@csrf_exempt
def all(req):
    micros = list(MicroService.all())
    return JsonResponse(micros, safe=False)

@csrf_exempt
def create(req):
    try:
        micro = MicroService.create(req.POST['name'])
        return JsonResponse(model_to_dict(micro), safe=False)
    except MultiValueDictKeyError:
        return JsonResponse({'message': 'name required'})
    except (DjangoIntegrityError, MysqlIntegrityError) :
        return JsonResponse({'message': 'This micronutrient already exists'})

@csrf_exempt
def delete(req):
    try:
        if 'id' in req.POST:
            MicroService.deleteById(req.POST['id'])
        elif 'slug' in req.POST:
            MicroService.deleteBySlug(req.POST['id'])
        return JsonResponse({'message':'deleted'})
    except Exception:
        raise

@csrf_exempt
def deleteAll(req):
    MicroService.deleteAll()
    return HttpResponse()
