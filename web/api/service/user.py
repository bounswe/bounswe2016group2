from rest_framework.authtoken.models import Token


def deleteToken(user):
    try:
        token = Token.objects.get(user=user)
        if token:
            token.delete()
    except Exception:
        pass


def refreshToken(user):
    deleteToken(user)
    return Token.objects.create(user=user)
