from api.models import Micronutrient
import api.mock.nutrient as MicronutrientMock

def all():
    return Micronutrient.objects.values()

def create(name):
    micro = Micronutrient(name=name)
    micro.save()
    return micro

def createDefaults():
    allNames = MicronutrientMock.getAllNames()
    for name in allNames:
        try:
            create(name)
        except Exception:
            print(name, ' already exists')

def deleteById(id):
    Micronutrient.objects.get(id=id).delete()

def deleteBySlug(slug):
    Micronutrient.objects.get(slug=slug).delete()

def deleteAll():
    Micronutrient.objects.all().delete()
