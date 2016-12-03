import api.service.constants as Constants


def createDefaults():
    pass


def calculateGeneralDetails(food):
    food['details'] = {
        'weight': 0,
        'energy': 0,
        'rate': 1,
        'protein': {
            'weight': 0,
            'energy': 0,
            'rate': 0,
        },
        'carb': {
            'weight': 0,
            'energy': 0,
            'rate': 0,
        },
        'fat': {
            'weight': 0,
            'energy': 0,
            'rate': 0,
        },
        'other': {
            'weight': 0,
            'energy': 0,
            'rate': 0,
            'details': {}
        }
    }

    macroFields = ('protein', 'carb', 'fat')
    # microFields = (
    #     'saturatedFat', 'sugar', 'fibre', 'cholesterol', 'calcium', 'iron', 'sodium', 'potassium',
    #     'magnesium', 'phosphorus', 'thiamin', 'riboflavin', 'niacin', 'folate')

    for inclusion in food['inclusions']:
        ingredient = inclusion['ingredient']

        food['details']['weight'] += inclusion['value']
        food['details']['energy'] += ingredient['energy']

        for macroField in macroFields:
            food['details'][macroField]['weight'] += ingredient[macroField]
            # food['details'][macroField]['energy'] = ingredient[macroField]['energy']
        # for microField in microFields:
        #     food['details']['other']['weight'] += ingredient[microField]
        #     # food['details']['other']['energy'] = ingredient[microField]['energy']
        #     if microField not in food['details']['other']['details']:
        #         food['details']['other']['details'][microField] = 0
        #     food['details']['other']['details'][microField] += ingredient[microField]
    details = food['details']
    details['other']['weight'] = (
        details['weight'] - details['protein']['weight'] - details['carb']['weight'] - details['fat']['weight']
    )


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
