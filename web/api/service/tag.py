import urllib
import json


def search(q):
    url = 'https://en.wikipedia.org/w/api.php?action=opensearch&format=json&search=' + q
    strData = urllib.request.urlopen(url).read().decode('UTF-8')
    searchData = json.loads(strData)

    tags = searchData[1]
    descriptions = searchData[2]

    data = []
    for i, tag in enumerate(tags):
        data.append({
            'name': tags[i],
            'description': descriptions[i]
        })

    return data
