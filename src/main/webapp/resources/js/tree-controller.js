//var treeMain = angular.module('FastFoursApp',[]);
app.controller('TreeController', function ($scope, $http, TreeFactory) {
	
	function onItemSelected(itemId, year){
		
		var root = getRoot();
		$http.get(root + "/models/get/" + itemId + "?year=" + year).success(function(data, status){
			
			$scope.modelData = data;
		});
	}
	
	$scope.getItems = function($scope, TreeFactory) {

		var tree = TreeFactory.create();
		tree.onItemSelectedListener = onItemSelected;
	};
	$scope.getItems($scope, TreeFactory);
});

function getRoot() {
	
	var paths = location.pathname.split("/");
	return paths.length === 4 ? "/" + paths[1] : "";
}

