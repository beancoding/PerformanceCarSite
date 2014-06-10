<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add model</title>
	<script src="<c:url value='/jquery/jquery.js' />" type="text/javascript"></script>
	<script src="<c:url value='/angularlibs/angular.js' />" type="text/javascript"></script>
	<script src="<c:url value='/libs/js/model-controller.js' />" type="text/javascript"></script>
</head>

<body>
	<c:url var="addModelUrl" value="/models/add" />
	<f:form action="${addModelUrl}" method="POST" modelAttribute="vehicleModel" enctype="multipart/form-data">

		<div ng-controller="ModelController">

			<f:select path="selectedMake" ng-change="getModels()" ng-model="selectedMake">
				<f:options items="${makes}" itemLabel="name" itemValue="name" />
			</f:select>
			
			<div>
				Existing model:<input ng-disabled="!(modelsRelatedToMake && modelsRelatedToMake.length)" type="radio" id="useExistingModel" name="useExistingModel" value="true" ng-click="toggle($event)" ng-checked="!newModel" /> 
				<select ng-disabled="newModel" name="modelName" id="modelName" ng-model="selectedModel">
					<option ng-repeat="m in modelsRelatedToMake" value="{{c.name}}">{{c.name}}</option>
				</select>
			</div>
			New model:
			<f:radiobutton path="useExistingModel" value="false" ng-click="toggle($event)" ng-checked="newModel" />
			<div>
				name:<f:input ng-disabled="!newModel" path="modelName" />
				<br />
			</div>
			size:
			<f:input path="engineSize" /><f:errors path="engineSize"/>
			<br />
			<f:select path="engineType">
				<f:options items="${engines}" />
			</f:select>
			<br />
			<f:select path="engineAspiration">
				<f:options items="${aspiration}" />
			</f:select>
			<br /> 
			quarterMile<f:input path="quarterMileTime" />
			<br /> 
			0-100<f:input path="timeToOneHundred" />
			<br /> 
			century: <f:input path="century" type="number" min="19" max="20" />
			decade: <f:input path="decade" type="number" min="0" max="9" />
			year: <f:input path="year" type="number" min="0" max="9" />
			<br /> 
			<input type="file" name="carPic" id="carPic" />
			<input type="submit" value="submit">
	</f:form>
</body>

</html>