from django.contrib.auth.models import User

def get(id):
    return User.objects.get(id=id)

def all():
    return User.objects.all()

def create(email, password, firstName='', lastName=''):
    return User.objects.create_user(email=email, username=email, password=password, first_name=firstName, last_name=lastName)

def delete(id):
    user = get(id)
    user.delete()
    return user

def toDict(user):
    return {
        'firstName': user.first_name,
        'lastName': user.last_name,
        'fullName': user.first_name + ' ' + user.last_name,
        'email': user.email
    }

def listToDict(users):
    return [toDict(user) for user in users]

def checkIsLoggedIn(user):
    if(user.is_anonymous): raise PermissionError
