'use strict'

var module = angular.module('cake.controllers', []);
module.controller("CakeController", [ "$scope", "CakeService",
		function($scope, CakeService) {

			
				$scope.cakeDto = {
						name : null,
						description : null,
						image: null
					};
			//$scope.searchCakes = function() {
				
				CakeService.getCakes().then(function(value) {
					$scope.allCakes= value.data;
				}, function(reason) {
					console.log("error occured");
				}, function(value) {
					console.log("no callback");
				});
				
		  
				
				$scope.addCake = function() {
					
					CakeService.addCake($scope.cakeDto).then(function(response) {
						$scope.successMsg="Cake successfully added";
						$scope.errorMsg="";
						console.log(response.status);
						CakeService.getCakes().then(function(value) {
							$scope.allCakes= value.data;
						}, function(reason) {
							console.log("error occured");
						}, function(value) {
							console.log("no callback");
						});

						$scope.cakeDto = {
								name : null,
								description : null,
								image: null
							};
					}, function(response) {
						$scope.errorMsg="Cake already exist therefore cannot be added";
						$scope.successMsg="";
						
					}, function(value) {
						console.log("no callback");
					}); 
				}
				
			//}
		} ]);