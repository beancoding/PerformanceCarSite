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
	
		<div style="margin:auto;width:330px">
			
			<h1>Performance Cars Data</h1>
		</div>
		
		<div ng-controller="TreeController" >
			<div id="treeItems01" style="float:left;width:245px"></div>
			<div style="position:absolute;top:50px;left:165px;float:right;width:960px;height:300px;border-radius: 10px;-moz-border-radius: 10px;background-color:rgba(255,255,255,0.3)">
				<div ng-style="{display:(modelData && modelData.modelYearPK)?'block':'none'}" style="margin-left:10px;margin-top:10px">
					<span>{{modelData.model.make.name}}</span><br/>
					<span>{{modelData.model.name}}</span><br/>
				</div>
			</div>
		</div>
		
	</body>

</html>
