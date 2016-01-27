/*global angular */

/**
 * Services that persists and retrieves todos from localStorage or a backend API
 * if available.
 *
 * They both follow the same API, returning promises for all changes to the
 * model.
 */
angular.module('todomvc')
	.factory('todoStorage', function ($http, $injector) {
		'use strict';

		// Detect if an API backend is present. If so, return the API module, else
		// hand off the localStorage adapter
		return $http.get('http://localhost:8766/api/todos')
			.then(function () {
				return $injector.get('api');
			}, function () {
				return $injector.get('localStorage');
			});
	})

	.factory('api', function ($resource) {
		'use strict';

		var store = {
			todos: [],

			api: $resource('http://localhost:8766/api/todos/:id', null,
				{
					update: { method:'PUT' }
				}
			),

			clearCompleted: function () {
				var originalTodos = store.todos.links.slice(0);

				var incompleteTodos = store.todos.links.filter(function (todo) {
					return !todo.completed;
				});

				angular.copy(incompleteTodos, store.todos.links);

				return store.api.delete(function () {
					}, function error() {
						angular.copy(originalTodos, store.todos.links);
					});
			},

			delete: function (todo) {
				var originalTodos = store.todos.links.slice(0);

				store.todos.links.splice(store.todos.links.indexOf(todo), 1);
				return store.api.delete({ id: todo.id },
					function () {
					}, function error() {
						angular.copy(originalTodos, store.todos.links);
					});
			},

			get: function () {
				return store.api.query(function (resp) {
					angular.copy(resp, store.todos.links);
				});
			},

			insert: function (todo) {
				var originalTodos = store.todos.links.slice(0);

				return store.api.save(todo,
					function success(resp) {
						todo.id = resp.id;
						store.todos.links.push(todo);
					}, function error() {
						angular.copy(originalTodos, store.todos.links);
					})
					.$promise;
			},

			put: function (todo) {
				return store.api.update({ id: todo.id }, todo)
					.$promise;
			}
		};

		return store;
	})

	.factory('localStorage', function ($q) {
		'use strict';

		var STORAGE_ID = 'todos-angularjs';

		var store = {
			todos: [],

			_getFromLocalStorage: function () {
				return JSON.parse(localStorage.getItem(STORAGE_ID) || '[]');
			},

			_saveToLocalStorage: function (todos) {
				localStorage.setItem(STORAGE_ID, JSON.stringify(todos));
			},

			clearCompleted: function () {
				var deferred = $q.defer();

				var incompleteTodos = store.todos.links.filter(function (todo) {
					return !todo.completed;
				});

				angular.copy(incompleteTodos, store.todos.links);

				store._saveToLocalStorage(store.todos.links);
				deferred.resolve(store.todos.links);

				return deferred.promise;
			},

			delete: function (todo) {
				var deferred = $q.defer();

				store.todos.links.splice(store.todos.links.indexOf(todo), 1);

				store._saveToLocalStorage(store.todos.links);
				deferred.resolve(store.todos.links);

				return deferred.promise;
			},

			get: function () {
				var deferred = $q.defer();

				angular.copy(store._getFromLocalStorage(), store.todos.links);
				deferred.resolve(store.todos.links);

				return deferred.promise;
			},

			insert: function (todo) {
				var deferred = $q.defer();

				store.todos.links.push(todo);

				store._saveToLocalStorage(store.todos.links);
				deferred.resolve(store.todos.links);

				return deferred.promise;
			},

			put: function (todo, index) {
				var deferred = $q.defer();

				store.todos.links[index] = todo;

				store._saveToLocalStorage(store.todos.links);
				deferred.resolve(store.todos.links);

				return deferred.promise;
			}
		};

		return store;
	});
