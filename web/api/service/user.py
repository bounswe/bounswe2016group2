from django.contrib.auth.models import User

def get(id):
    try:
        user = User.objects.filter(id=id).values().first()
        if not user: raise 404
        return user
    except Exception:
        raise
