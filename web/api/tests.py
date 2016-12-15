from django.test import TestCase
from django.contrib.auth.models import User

from api.model.ingredient import Ingredient
from api.model.food import Food
from api.model.foodRate import FoodRate
from api.model.foodComment import FoodComment
from api.model.restaurant import Restaurant
from api.model.restaurantRate import RestaurantRate
from api.model.restaurantComment import RestaurantComment


class Test(TestCase):

    def setUpIngredients(self):
        Ingredient.objects.create(
            name='Rice flour',
            measureValue=12,
            measureUnit='ml',
            defaultValue=83,
            defaultUnit='g',
            energy=305,
            protein=5,
            carb=67,
            sugar=1,
            fibre=0.3,
            fat=0,
            saturatedFat=8,
            cholesterol=0.3,
            calcium=0,
            iron=63.0,
            sodium=29.0,
            potassium=82.0,
            magnesium=0.1,
            phosphorus=0.02,
            thiamin=3.2,
            riboflavin=3,
            niacin=0,
            folate=1
        )
        Ingredient.objects.create(
            name='Soy flour',
            measureValue=12,
            measureUnit='ml',
            defaultValue=53,
            defaultUnit='g',
            energy=174,
            protein=25,
            carb=20,
            sugar=11,
            fibre=9.2,
            fat=1,
            saturatedFat=0.1,
            cholesterol=0,
            calcium=127,
            iron=4.9,
            sodium=11.0,
            potassium=1259,
            magnesium=153,
            phosphorus=356,
            thiamin=0.4,
            riboflavin=0.13,
            niacin=7.4,
            folate=161
        )
        Ingredient.objects.create(
            name='Wheat bran',
            measureValue=12,
            measureUnit='ml',
            defaultValue=4,
            defaultUnit='g',
            energy=8,
            protein=1,
            carb=2,
            sugar=0,
            fibre=1.6,
            fat=0,
            saturatedFat=0,
            cholesterol=0,
            calcium=3,
            iron=0.4,
            sodium=0,
            potassium=43,
            magnesium=22,
            phosphorus=37,
            thiamin=0,
            riboflavin=0.02,
            niacin=0.7,
            folate=3
        )
        Ingredient.objects.create(
            name='Bread',
            measureValue=12,
            measureUnit='ml',
            defaultValue=64,
            defaultUnit='g',
            energy=170,
            protein=6,
            carb=35,
            sugar=1,
            fibre=4.7,
            fat=2,
            saturatedFat=0.3,
            cholesterol=0,
            calcium=10,
            iron=2,
            sodium=340,
            potassium=109,
            magnesium=44.0,
            phosphorus=115,
            thiamin=0.2,
            riboflavin=0.05,
            niacin=3.4,
            folate=22
        )
        Ingredient.objects.create(
            name='Biscuit',
            measureValue=1,
            measureUnit='count',
            defaultValue=51,
            defaultUnit='g',
            energy=186,
            protein=3,
            carb=25,
            sugar=2,
            fibre=0.7,
            fat=8,
            saturatedFat=1.3,
            cholesterol=1,
            calcium=25,
            iron=1.7,
            sodium=537,
            potassium=114,
            magnesium=9.0,
            phosphorus=219,
            thiamin=0.2,
            riboflavin=0.15,
            niacin=2.4,
            folate=58
        )
        Ingredient.objects.create(
            name='Granola bar',
            measureValue=1,
            measureUnit='count',
            defaultValue=25,
            defaultUnit='g',
            energy=118,
            protein=3,
            carb=16,
            sugar=0,
            fibre=1.3,
            fat=5,
            saturatedFat=0.6,
            cholesterol=0,
            calcium=15,
            iron=0.7,
            sodium=74,
            potassium=84,
            magnesium=24.0,
            phosphorus=69.0,
            thiamin=0.1,
            riboflavin=0.03,
            niacin=1.1,
            folate=6
        )
        Ingredient.objects.create(
            name='Chocolate chip',
            measureValue=1,
            measureUnit='count',
            defaultValue=20,
            defaultUnit='g',
            energy=98,
            protein=1,
            carb=13,
            sugar=7,
            fibre=0.6,
            fat=5,
            saturatedFat=1.5,
            cholesterol=0,
            calcium=7,
            iron=0.7,
            sodium=59,
            potassium=30,
            magnesium=10,
            phosphorus=23,
            thiamin=0,
            riboflavin=0.05,
            niacin=0.7,
            folate=20
        )

    def setUpUsers(self):
        User.objects.create(
            first_name='Kagan',
            last_name='Sari',
            username='kagannsari@gmail.com',
            email='kagannsari@gmail.com'
        )
        User.objects.create(
            first_name='Murat',
            last_name='Aclan',
            username='aclan@hotmail.com',
            email='aclan@hotmail.com'
        )
        User.objects.create(
            first_name='Ahmet',
            last_name='McDonalds',
            username='info@mcdonalds.com',
            email='info@mcdonalds.com'
        )
        User.objects.create(
            first_name='Ahmet',
            last_name='BurgerKing',
            username='info@burgerking.com',
            email='info@burgerking.com'
        )

    def setUpRestaurants(self):
        Restaurant.objects.create(
            user=User.objects.get(username='info@mcdonalds.com'),
            name='McDonalds Bebek',
            description='McDonalds gibisi yok'
        )
        Restaurant.objects.create(
            user=User.objects.get(username='info@burgerking.com'),
            name='Burger King Hisarustu',
            description='Ates seni cagriyo'
        )
        Restaurant.objects.create(
            user=User.objects.get(username='kagannsari@gmail.com'),
            name='Mutfak',
            description=None
        )

    def setUpFoods(self):
        burgerking = Restaurant.objects.get(name='Burger King Hisarustu')
        Food.objects.create(name='Big King Menu', restaurant=burgerking)

    def setUp(self):
        self.setUpIngredients()
        self.setUpUsers()
        self.setUpRestaurants()
        self.setUpFoods()

    def test(self):
        burgerking = Restaurant.objects.get(name='Burger King Hisarustu')
        bigking = Food.objects.get(name='Big King Menu')

        self.assertEqual(burgerking, bigking.restaurant)
