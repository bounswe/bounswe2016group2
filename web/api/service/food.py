import api.service.constants as Constants


def createDefaults():
    pass


def calculateDetails(food):
    food['weight'] = 0
    food['details'] = {'value': 0}

    for inclusion in food['inclusions']:
        ingredient = inclusion['ingredient']
        ratio = inclusion['value'] / ingredient['defaultValue']

        relativeFields = (
            'measureValue', 'energy', 'protein', 'carb', 'fat',
            'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
            'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate')

        for field in relativeFields:
            ingredient[field] *= ratio
            if field not in food['details']:
                food['details'][field] = 0
            food['details'][field] += ingredient[field]

        food['details']['value'] += inclusion['value']
    return food
