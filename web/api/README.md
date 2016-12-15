## Headers

Content-Type: "application/json"

Authorization: "Token {Token}"

***

## api/search?query=String
##### GET
Search food, restaurant and ingredient

  **Response**

    200

      {
        "foods": [Food],
        "restaurants": [Restaurant],
        "ingredients": [ingredient]
      }

***

## api/searchFood
##### GET
Search food with filtering options

  **Request**

    {
      "query": String,
      "minEnergy": Float,
      "maxEnergy": Float,
      "minProteinVal": Float,
      "maxProteinVal": Float,
      "minCarbVal": Float,
      "maxCarbVal": Float,
      "minFatVal": Float,
      "maxFatVal": Float,
      "minProteinPerc": Float,
      "maxProteinPerc": Float,
      "minCarbPerc": Float,
      "maxCarbPerc": Float,
      "minFatPerc": Float,
      "maxFatPerc": Float,
      "ingredients": [id],
      "tags": [{name:String}],
      "diets": [id],
      "tags"(optional): String
    }

  **Response**

    200

      [Food]


    400 Bad Request

      { field: [error] }      

***

## api/searchTag?query=String
##### GET
Search semantic tag

  **Response**

    200

      {
        "foods": [Food],
        "restaurants": [Restaurant],
        "ingredients": [ingredient]
      }

***

## api/users/signup
##### POST
Create user and return

  **Request**

    {
      "email": String,
      "password": String,
      "first_name"(optional): String,
      "last_name"(optional): String
    }

  **Response**

    200

      {
        "email": String,
        "password": String,
        "first_name"(optional): String,
        "last_name"(optional): String
      }


    400 Bad Request

    { field: [error] }

***

## api/users/signin
##### POST
authenticate user with email and password, return token

  **Request**

    {
      "email": String,
      "password": String
    }

  **Response**

    200

      {
        "token": Token
      }



    400 Bad Request

      {}

***

## api/users/signout
##### GET
delete user token from database

  **Response**

    200

      { "field": [error] }

***

## api/users/me
##### GET
get current user if authenticated

  **Response**

    200

    {
      "email": String,
      "password": String,
      "first_name" (optional): String,
      "last_name" (optional): String,
      "foods": [Food],
      "restaurants": [Restaurant],
      "diets": [Diet],
      "foodComments": [FoodComment],
      "foodRates": [FoodRate],
      "restaurantComments": [RestaurantComment],
      "restaurantRates": [RestaurantRate],
      "isServer": Boolean
    }



    400 Bad Request

      { field: [error] }

***

## api/users
##### GET
retrieve all users

  **Response**

    200

      [
        {
          "email": String,
          "password": String,
          "first_name" (optional): String,
          "last_name" (optional): String
        }
      ]

***

## api/users/{userId}
##### GET,PUT,DELETE
retrieve, modify or delete single user by id

  **Request**

    {
      "email": String,
      "first_name"(optional): String,
      "last_name"(optional): String
    }

  **Response**

    200

      User



    400 Bad Request

      { field: [error] }

***

## api/ingredients
##### GET,POST
Get all ingredients, or create a new one

  **Request**

    {
      "weight":       Float("g"),
      "energy":       Float("kcal"),
      "protein":      Float("g"),
      "carb" (optional):         Float("g"),
      "fat" (optional):          Float("g"),
      "saturatedFat" (optional): Float("g"),
      "sugar" (optional):        Float("g"),
      "fibre" (optional):        Float("g"),
      "cholesterol" (optional):  Float("mg"),
      "calcium" (optional):      Float("mg"),
      "iron" (optional):         Float("mg"),
      "sodium" (optional):       Float("mg"),
      "potassium" (optional):    Float("mg"),
      "magnesium" (optional):    Float("mg"),
      "phosphorus" (optional):   Float("mg"),
      "thiamin" (optional):      Float("mg"),
      "riboflavin" (optional):   Float("mg"),
      "niacin" (optional):       Float("NE"),
      "folate" (optional):       Float("DFE")
    }

  **Response**

    200

      Ingredient



    400 Bad Request

      { field: [error] }

***

## api/ingredients/{ingredientId}
##### GET,PUT,DELETE
Retrive, modify or delete single ingredient by id

  **Request**

    Ingredient

  **Response**

    200

      Inredient



    400 Bad Request

      { field: [error] }

    404 Not Found

***

## api/ingredients/{slug}
##### GET,DELETE
Retrive or delete single ingredient by slug

  **Response**

    200

      Ingredient



    400 Bad Request

      { field: [error] }

    404 Not Found

***

## api/foods
##### GET,POST
Get all foods, or create a new one

  **Request**

    {
      "name": String,
      "slug": String
    }

  **Response**

    200

      Food

    400 Bad Request

      { field: [error] }

***

## api/foods/{foodId}
##### GET,PUT,DELETE
Retrive, modify or delete single food by id

  **Request**

    Food

  **Response**

    200

      Food


    400 Bad Request

      { field: [error] }

***

## api/foods/{slug}
##### GET,DELETE
Retrive or delete single food by slug

  **Request**

    Food

  **Response**

    200

      Food

    204 No Content

    400 Bad Request

      { field: [error] }

***

## api/foodSearch?query=String
##### GET
Search food

  **Response**

    200

      [Food]


    400 Bad Request

      { field: [error] }

***

## api/foodMocks
##### POST
Add default foods in mocks to database

    **Request**

    **Response**

      400 Bad Request

        { field: [error] }

***

## api/foods/{foodId}/ingredients/{ingredientId}
##### POST,GET,PUT,DELETE
Create, retrive, modify or delete the existence of ingredient in food

  **Request**

    {
      weight: Float
    }

  **Response**

    200

      {weight: Float}

    204 No Content

    400 Bad Request

      { field: [error] }

    404 Not Found
