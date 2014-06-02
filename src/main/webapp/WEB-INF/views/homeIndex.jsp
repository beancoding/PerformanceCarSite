<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html ng-app="FastFoursApp">

	<head>
		<title>Home</title>
		<script src="<c:url value='/angularlibs/angular.js'/>" ></script>
		<script type="text/javascript" src="<c:url value='/libs/js/tree-factory.js'/>" ></script>
		<script type="text/javascript" src="<c:url value='/libs/js/tree-controller.js'/>" ></script>
		<script type="text/javascript" src="<c:url value='/jstree/libs/jquery.js'/>" ></script>
		<script type="text/javascript" src="<c:url value='/jstree/jstree.js'/>" ></script>
		<link href="<c:url value='/jstree/themes/default/style.css'/>" rel="stylesheet" type="text/css"/>
	</head>
	
	<body style="background-color:lightgray">
	
		<h1>Hello world!</h1>
	
		<div ng-controller="TreeController">
			<div id="treeItems01"></div>
		</div>
		
	</body>

</html>
