def calculateRate(restaurant):
    total = 0
    count = 0
    if len(restaurant['rates']) == 0:
        count = 1
    for rate in restaurant['rates']:
        total += rate['score']
        count += 1
    restaurant.pop('rates', None)
    restaurant['rate'] = total / count
