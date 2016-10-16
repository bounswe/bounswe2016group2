from django.http import JsonResponse

def all(request):
    return JsonResponse({'foo': 'bar'})
