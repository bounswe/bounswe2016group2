'use strict';

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

var Api = function () {
  function Api() {
    _classCallCheck(this, Api);
  }

  _createClass(Api, null, [{
    key: 'path',
    value: function path(relativePath) {
      return '/api/' + relativePath;
    }
  }, {
    key: 'get',
    value: function get(url) {
      var _this = this;

      return new Promise(function (resolve, reject) {
        var status = null;
        fetch(_this.path(url), {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json'
          }
        }).then(function (res) {
          status = res.status;
          return res;
        }).then(function (res) {
          return res.json();
        }).then(function (data) {
          if (status < 400) {
            resolve(data);
          } else {
            reject({ status: status, data: data });
          }
        });
      });
    }
  }, {
    key: 'post',
    value: function post(url, data) {
      var _this2 = this;

      return new Promise(function (resolve, reject) {
        var status = null;
        var requestData = {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        };
        if (token) {
          requestData.headers.Authorization = 'Token ' + token;
        }
        fetch(_this2.path(url), requestData).then(function (res) {
          status = res.status;
          return res;
        }).then(function (res) {
          return res.json();
        }).then(function (data) {
          if (status < 400) {
            resolve(data);
          } else {
            reject({ status: status, data: data });
          }
        });
      });
    }
  }, {
    key: 'delete',
    value: function _delete(url, data) {
      var _this3 = this;

      return new Promise(function (resolve, reject) {
        var status = null;
        fetch(_this3.path(url), {
          method: 'DELETE',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        }).then(function (res) {
          status = res.status;
          return res;
        }).then(function (res) {
          return res.json();
        }).then(function (data) {
          if (status < 400) {
            resolve(data);
          } else {
            reject({ status: status, data: data });
          }
        });
      });
    }

    /**
     * {email: String, password: String}
     */

  }, {
    key: 'signin',
    value: function signin(credentials) {
      return this.post('users/signin', credentials);
    }

    /**
     * {email: String, password: String, first_name: String, last_name: String}
     */

  }, {
    key: 'signup',
    value: function signup(data) {
      return this.post('users/signup', data);
    }
  }, {
    key: 'addFood',
    value: function addFood(data) {
      return this.post('foods', data);
    }
  }, {
    key: 'addIngredientToFood',
    value: function addIngredientToFood(foodId, ingId, data) {
      return this.post('foods/' + foodId + '/ingredients/' + ingId, data);
    }
  }, {
    key: 'searchIngredient',
    value: function searchIngredient(query) {
      return this.get('ingredientSearch?query=' + query);
    }
  }, {
    key: 'getIngredients',
    value: function getIngredients() {
      return this.get('ingredients');
    }
  }, {
    key: 'searchFood',
    value: function searchFood(query) {
      return this.get('foodSearch?query=' + query);
    }
  }, {
    key: 'deleteFood',
    value: function deleteFood(data) {
      return this.delete('foods/' + data);
    }
  }, {
    key: 'getFood',
    value: function getFood(id) {
      return this.get('foods/' + id);
    }
  }, {
    key: 'getIngredient',
    value: function getIngredient(id) {
      return this.get('ingredients/' + id);
    }
  }, {
    key: 'foodAte',
    value: function foodAte(id, data) {
      return this.post('foods/' + id + "/ate", data);
    }
  }, {
    key: 'ingredientAte',
    value: function ingredientAte(id, data) {
      return this.post('ingredients/' + id + "/ate", data);
    }
  }, {
    key: 'consumptionHistory',
    value: function consumptionHistory(data) {
      return this.get('users/history');
    }
  }, {
    key: 'createDiet',
    value: function createDiet(data) {
      return this.post('diets', data);
    }
  }]);

  return Api;
}();