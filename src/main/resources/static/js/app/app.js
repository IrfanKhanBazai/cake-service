'use strict'

var demoApp = angular.module('demo', [ 'ui.bootstrap', 'cake.controllers',
		'cake.services' ]);
demoApp.constant("CONSTANTS", {
	getCakes : "/cake-manager/cakes",
	addCake : "/cake-manager/cakes"
});