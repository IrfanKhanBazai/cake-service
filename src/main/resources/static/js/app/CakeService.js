'use strict'

angular.module('cake.services', []).factory('CakeService',
		[ "$http", "CONSTANTS", function($http, CONSTANTS) {
			var service = {};			
			service.getCakes = function() {
			return $http.get(CONSTANTS.getCakes);
				
			}	
			service.addCake = function(cakeDto) {
				return $http.post(CONSTANTS.addCake, cakeDto);
			}
			return service;
		} ]);