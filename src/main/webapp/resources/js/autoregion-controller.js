angular.module("FastFoursApp",[])
	   .controller("AutoRegionController", function($scope, $http){
		   
			var paths = location.pathname.split("/");
			var root = '';
			if(paths.length === 4)
				root = "/" + paths[1];
		   
		   $scope.continent = null;
		   $scope.getCountries = function(){
			   
			   if($scope.continent) {
				   
				   $http.get(root + '/countries/bycontinent/' + $scope.continent).success(function(data, status) {
					     
					   $scope.countries = data;
				   });
			   }
		   };
		   
		   $scope.displayCountries = function(){
			   
			   return $scope.countries ? "inline" : "none";
		   };
	   });

