class Api {


  static path(relativePath) {
    return '/api/' + relativePath
  }

  static get(url) {
    return new Promise((resolve, reject) => {
      let status = null
      let requestData = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
      }
      if (token) {
        requestData.headers.Authorization = 'Token ' + token
      }
      fetch(this.path(url), requestData).then((res) => {
        status = res.status
        return res
      }).then((res) => {
        return res.json()
      }).then((data) => {
        if (status < 400) {
          resolve(data)
        } else {
          reject({status: status, data: data})
        }
      })
    })
  }

  static post(url, data) {
    return new Promise((resolve, reject) => {
      let status = null
      let requestData = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
      }
      if (token) {
        requestData.headers.Authorization = 'Token ' + token
      }
      fetch(this.path(url), requestData).then((res) => {
        status = res.status
        return res
      }).then((res) => {
        return res.json()
      }).then((data) => {
        if (status < 400) {
          resolve(data)
        } else {
          reject({status: status, data: data})
        }
      })
    })
  }

	static delete(url, data) {
		return new Promise((resolve, reject) => {
			let status = null
			fetch(this.path(url), {
				method: 'DELETE',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(data)
			}).then((res) => {
				status = res.status
				return res
			}).then((res) => {
				return res.json()
			}).then((data) => {
				if (status < 400) {
					resolve(data)
				} else {
					reject({status: status, data: data})
				}
			})
		})
	}

  // USER ROUTES
  /**
   * {email: String, password: String}
   */
  static signin(credentials) {
    return this.post('users/signin', credentials)
  }
  /**
   * {email: String, password: String, first_name: String, last_name: String}
   */
  static signup(data) {
    return this.post('users/signup', data)
  }
  static me(data) {
    return this.get('users/me', data)
  }
  static consumptionHistory(){
    return this.get('users/me/history');
  }

  // INGREDIENT ROUTES
  static searchIngredient(query) {
    return this.get('ingredientSearch?query=' + query);
  }
  static getIngredients() {
    return this.get('ingredients');
  }
  static getIngredient(id){
    return this.get('ingredients/' + id);
  }
  static ingredientAte(id, data){
    return this.post('ingredients/' + id + "/ate", data);
  }

  // FOOD ROUTES
  static addFood(data) {
		return this.post('foods', data);
	}
  static getFoods(id){
    return this.get('foods');
  }
  static getFood(id){
    return this.get('foods/' + id);
  }
  static search(query) {
		return this.get('search?query=' + query);
	}
  static advancedSearch(filter) {
		return this.post('searchFood', filter);
	}
  static addIngredientToFood(foodId, ingId, data) {
		return this.post('foods/' + foodId + '/ingredients/' + ingId, data);
	}
  static foodAte(id, data){
    return this.post('foods/' + id + "/ate", data);
  }
  static deleteFood(data){
		return this.delete('foods/' + data);
	}
  static searchTag(query){
		return this.get('searchTag?query=' + query);
	}

  // INCLUSION ROUTES
  static addRestaurant(data) {
		return this.post('restaurants', data);
	}
  // RESTAURANT ROUTES

  static getRestaurant(id){
    return this.get('restaurants/' + id);
  }
  static addFoodToRestaurant(restaurantId, foodId) {
    return this.post('restaurants/' + restaurantId + '/foods/' + foodId);
  }

  // DIET ROUTES
  static createDiet(data){
    return this.post('diets', data);
  }
  static getMyDiets(){
    return this.get('myDiets');
  }
  static getDiets(){
    return this.get('diets');
  }
  static addDiet(dietId){
    return this.post('myDiets/' + dietId)
  }
}
