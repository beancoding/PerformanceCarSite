angular.module("FastFoursApp",[])
	   .controller("ModelController", function($scope, $http){
		  
		   var root = getRoot();
			
		   $scope.newModel = true;
		   
		   $scope.toggle = function(e) {
			 
			   if("true" == e.target.value)
				   $scope.newModel = false;
			   else
				   $scope.newModel = true;
		   };
		   
		   $scope.getModels = function() {
			   
			   $http.get(root + "/models/getAll/" + $scope.selectedMake, {cache:true}).success(function(data, status){
				   
				  $scope.modelsRelatedToMake = data;
				   removeBlankItem("#modelName");
			   });
		   };
		   
		   var firstItem = angular.element("#selectedMake")
		   						  .children()
		   						  .first();
		   if(firstItem) {
			   
			   $scope.selectedMake = firstItem.text();
			   $scope.getModels();
		   }
	   });

	function getRoot() {
		
		var paths = location.pathname.split("/");
		return paths.length === 4 ? "/" + paths[1] : "";
	}
	
	angular.element(document).ready(function() {

		angular.bootstrap(document, ['FastFoursApp']);
		removeBlankItem("#selectedMake");
    });
	
	function removeBlankItem(id){
		
		angular.element(id)
 		  	   .children()
 		  	   .filter(function(){ return angular.element(this).text() === ""; })
 		  	   .remove();
	}