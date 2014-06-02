//var treeMain = angular.module('FastFoursApp',[]);
app.controller('TreeController', function ($scope, TreeFactory) {
	
	function onItemSelected(itemId){
		console.log("The selected item id is "+itemId);
	}
	
	$scope.getItems = function($scope, TreeFactory) {

		var tree = TreeFactory.create();
		tree.onItemSelectedListener = onItemSelected;
	};
	$scope.getItems($scope, TreeFactory);
});

