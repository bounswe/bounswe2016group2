import datetime

import api.service.constants as Constants

from api.model.ateFood import AteFood
from api.model.restaurant import Restaurant
from api.serializer.ateFood import AteFoodDetailSerializer


def getAnalyticTemplate():

    microFields = (
        'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
        'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate'
    )

    template = {
        'count': 0,
        'weight': 0,
        'energy': 0,
        'rate': 1,
        'protein': {
            'weight': 0,
            'rate': 0
        },
        'carb': {
            'weight': 0,
            'rate': 0
        },
        'fat': {
            'weight': 0,
            'rate': 0
        },
        'others': {
        }
    }

    for field in microFields:
        template['others'][field] = 0

    return template


def calculateGeneralDetails(food):
    food['details'] = getAnalyticTemplate()

    macroFields = ('protein', 'carb', 'fat')
    microFields = (
        'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
        'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate')

    for inclusion in food['inclusions']:
        ingredient = inclusion['ingredient']

        food['details']['weight'] += inclusion['value']
        food['details']['energy'] += ingredient['energy']

        for macroField in macroFields:
            food['details'][macroField]['weight'] += ingredient[macroField]
        for microField in microFields:
            food['details']['others'][microField] += ingredient[microField]


def calculateDetails(food):
    food['weight'] = 0

    for inclusion in food['inclusions']:
        ingredient = inclusion['ingredient']
        ratio = inclusion['value'] / ingredient['defaultValue']

        relativeFields = (
            'measureValue', 'energy', 'protein', 'carb', 'fat',
            'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
            'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate')

        for field in relativeFields:
            ingredient[field] *= ratio

    calculateGeneralDetails(food)
    return food


def calculateUserHistory(userId, startDate, endDate):
    total = getAnalyticTemplate()  # total values
    total['ateFoods'] = []
    daily = {}  # daily values
    ateFoods = AteFood.objects.filter(user=userId, created__gte=startDate, created__lte=endDate)
    ateFoodSerializer = AteFoodDetailSerializer(ateFoods, many=True)
    ateFoodArr = ateFoodSerializer.data

    microFields = (
        'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
        'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate')

    for ateFood in ateFoodArr:
        # initialize a new object on dict if given day is not found
        dateStr = datetime.datetime.strptime(ateFood['created'], "%Y-%m-%dT%H:%M:%S.%fZ").strftime('%d-%m-%Y')
        if dateStr not in daily:
            daily[dateStr] = getAnalyticTemplate()
            daily[dateStr]['ateFoods'] = []
            daily[dateStr]['date'] = dateStr

        food = ateFood['food']
        calculateDetails(food)

        # add nutritional values to both total and daily stats
        for analytic in [total, daily[dateStr]]:
            analytic['ateFoods'].append(ateFood)
            analytic['weight'] += food['details']['weight']
            analytic['energy'] += food['details']['energy']
            analytic['protein']['weight'] += food['details']['protein']['weight']
            analytic['carb']['weight'] += food['details']['carb']['weight']
            analytic['fat']['weight'] += food['details']['fat']['weight']
            for microField in microFields:
                analytic['others'][microField] += food['details']['others'][microField]

    return {
        'total': total,
        'daily': daily.values()
    }


def calculateServerHistory(userId, startDate, endDate):
    restaurants = Restaurant.objects.filter(user=userId)
    analytics = []
    for restaurant in restaurants:
        total = getAnalyticTemplate()  # total values
        total['ateFoods'] = []
        daily = {}  # daily values
        ateFoods = AteFood.objects.filter(food__restaurant=restaurant.id, created__gte=startDate, created__lte=endDate)
        ateFoodSerializer = AteFoodDetailSerializer(ateFoods, many=True)
        ateFoodArr = ateFoodSerializer.data

        microFields = (
            'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
            'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate')

        for ateFood in ateFoodArr:
            # initialize a new object on dict if given day is not found
            dateStr = datetime.datetime.strptime(ateFood['created'], "%Y-%m-%dT%H:%M:%S.%fZ").strftime('%d-%m-%Y')
            if dateStr not in daily:
                daily[dateStr] = getAnalyticTemplate()
                daily[dateStr]['ateFoods'] = []
                daily[dateStr]['date'] = dateStr

            food = ateFood['food']
            calculateDetails(food)

            # add nutritional values to both total and daily stats
            for analytic in [total, daily[dateStr]]:
                analytic['ateFoods'].append(ateFood)
                analytic['count'] += ateFood['value']
                analytic['weight'] += food['details']['weight']
                analytic['energy'] += food['details']['energy']
                analytic['protein']['weight'] += food['details']['protein']['weight']
                analytic['carb']['weight'] += food['details']['carb']['weight']
                analytic['fat']['weight'] += food['details']['fat']['weight']
                for microField in microFields:
                    analytic['others'][microField] += food['details']['others'][microField]

        analytics.append({
            'restaurant': {
                'id': restaurant.id,
                'name': restaurant.name
            },
            'total': total,
            'daily': daily.values()
        })

    return analytics
