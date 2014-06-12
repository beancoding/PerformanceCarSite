var app = angular.module('FastFoursApp',[]);

app.factory('TreeFactory',function(){
	
	function createMthd(){
		
		var util = angular;
		
		var tree = {};
		
		i = 1;
		
		var paths = location.pathname.split("/");
		var root = '';
		if(paths.length === 4)
			root = "/" + paths[1];
		
		$('#treeItems01').on("changed.jstree", function (e, data) {
	
							  if(tree.onItemSelectedListener != null && data.node.original && data.node.original.level === "Model")
								  tree.onItemSelectedListener(data.selected[0], data.node.parent);
						})
						.jstree({
							'core' : {
							  'data' : {
							    'url' : function (node) {
							    	
							    	if(!node.original)
							    		node.original = {level:'none'};
							    	return root + '/makes/getall/' + node.original.level + '?parent=' + node.parent;
							    },
							    'data' : function (node) {
							    	return { 'id' : node.id };
							    },
							    'success' : function(data,stat,res,parent){
							    	
							    	util.forEach(data, function(d){
							    		
							    		d.text = d.label;
							    		d.id = d.label;
							    		var isFile = d.level === "Model";
							    		d.children = !isFile;
							    		d.type = isFile ? "file" : "default";
							    		i++;
							    	});
							    	return data;
							    }
							  }
							},
							"types" : {
							    "file" : {
							      "icon" : root + "/libs/images/document.png",
							    }
							  },
							  "plugins" : [ "types" ]
						});
			return tree;
	}
	return {
		create:createMthd
	};
});