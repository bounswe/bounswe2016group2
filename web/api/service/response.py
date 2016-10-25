from django.http import HttpResponse
from django.http import JsonResponse

class JsonResponseBadRequest(JsonResponse):
    required = ['This field is required']
    taken = ['This value is taken']
    status_code = 400
    def __init__(self, data, *args, **kwargs):
        JsonResponse.__init__(self, dict(data), *args, **kwargs)

class HttpResponseUnauthorized(HttpResponse):
    status_code = 401