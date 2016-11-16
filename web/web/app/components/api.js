class Api {

  static path(relativePath) {
    return '/api/' + relativePath
  }

  static get(url) {
    return new Promise((resolve, reject) => {
      let status = null
      fetch(this.path(url), {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        }
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

  static post(url, data) {
    return new Promise((resolve, reject) => {
      let status = null
      fetch(this.path(url), {
        method: 'POST',
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

  static addFood(data) {
		return this.post('foods', data);
	}

	static addIngredientToFood(data) {
		return this.post('foods/1/ingredients/1', data);
	}

	static searchIngredient(query) {
		return this.get('ingredientSearch?query=' + query);
	}

  static searchFood(query) {
		return this.get('foodSearch?query=' + query);
	}

	static deleteFood(data){
		return this.delete('foods/' + data);
	}
}
