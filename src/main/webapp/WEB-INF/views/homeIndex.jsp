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
		<link href="<c:url value='/libs/css/main.css'/>" rel="stylesheet" type="text/css"/>
	</head>
	
	<c:url var="carbkgrnd" value="/libs/images/car-bkgnd.jpg"/>
	<body style="background-image:url('${carbkgrnd}');">
	
		<div style="width:1330px">
		<h1>Performance Cars Data</h1>
		
		<div ng-controller="TreeController" >
		
			<div id="sidebar">
				<div id="treeItems01"></div>
				<a href="<c:url value='/models/add' />">Add model</a><br/>
				<a href="<c:url value='/makes/add' />">Add make</a>
			</div>		
			
			<div id="content">
				<div ng-style="{display:(modelData && modelData.modelYearPK)?'block':'none'}" id="contentBody">
					<div>
						<span>{{modelData.modelYearPK.year}}&nbsp;{{modelData.model.make.name}}&nbsp;{{modelData.model.name}}</span>
					</div>
					<ul>
						<li>{{modelData.engineSize}}&nbsp;litres&nbsp;{{modelData.engineType}}&nbsp;{{modelData.engineAspirationTag}}</li>
						<li ng-hide="modelData.quarterMileTime===null">{{modelData.quarterMileTime}}&nbsp;second quarter mile</li>
						<li ng-hide="modelData.timeToOneHundred===null">{{modelData.timeToOneHundred}}&nbsp;seconds from 0 to 100kph</li>
					</ul>
					<img ng-hide="modelData.filePath===null||modelData.filePath===''" style="max-width:960px;max-height:960px" alt="carpic" src="{{modelData.filePath}}"/>
				</div>
			</div>
			
		</div>
		
		</div>
	</body>

</html>
