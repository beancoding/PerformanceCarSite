angular.module("FastFoursApp",[])
	   .controller("ModelController", function($scope, $http){
		  
		   var root = getRoot();
			
		   $scope.newModel = true;
		   
		   $scope.toggle = function(e) {
			 
			   if("true" == e.srcElement.value)
				   $scope.newModel = false;
			   else
				   $scope.newModel = true;
		   };
		   
		   $scope.getModels = function() {
			   
			   $http.get(root + "/models/getAll/" + $scope.selectedMake, {cache:true}).success(function(data, status){
				   
				  $scope.modelsRelatedToMake = data;
			   });
		   };
	   });

	function getRoot() {
		
		var paths = location.pathname.split("/");
		return paths.length === 4 ? "/" + paths[1] : "";
	}
	
	angular.element(document).ready(function() {

		angular.bootstrap(document, ['FastFoursApp']);
		angular.element("#selectedMake")
			   .children()
			   .filter(function(){ return angular.element(this).text() === ""; })
			   .remove();
    });