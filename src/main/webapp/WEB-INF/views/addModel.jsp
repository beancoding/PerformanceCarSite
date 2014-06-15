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
	<link href="<c:url value='/libs/css/main.css'/>" rel="stylesheet" type="text/css"/>
</head>

<c:url var="carbkgrnd" value="/libs/images/car-bkgnd.jpg"/>
<body style="background-image:url('${carbkgrnd}');">

	<div style="width:1330px">
	
	<h1><a href='<c:url value="/home/index"/>'>Performance Cars Data</a></h1>

	<c:url var="addModelUrl" value="/models/add" />
	<f:form action="${addModelUrl}" method="POST" modelAttribute="vehicleModel" enctype="multipart/form-data">

		<div ng-controller="ModelController">

			<div id="content" style="top:73px">
				<div id="contentBody">
					<f:errors class='err'/>
				
					<c:choose>
						<c:when test="${not empty makes}">
							<table>
								<tr>
									<td>Make:</td>
									<td><f:select path="selectedMake" ng-change="getModels()" ng-model="selectedMake">
										<f:options items="${makes}" itemLabel="name" itemValue="name" />
									</f:select></td>
								</tr>
							
							<tr>
								<td>Existing model:</td>
								<td><input ng-disabled="!(modelsRelatedToMake && modelsRelatedToMake.length)" type="radio" id="existingModel" name="existingModel" value="true" ng-click="toggle($event)" ng-checked="!newModel" /> 
								<select ng-disabled="newModel" name="modelName" id="modelName" ng-model="selectedModel">
									<option ng-repeat="m in modelsRelatedToMake" value="{{m.name}}">{{m.name}}</option>
								</select></td>
							</tr>
							
							<tr>
								<td>New model:</td>
								<td><f:radiobutton path="existingModel" value="false" ng-click="toggle($event)" ng-checked="newModel" /></td>
							</tr>
							
							<tr>
								<td>Model name:</td>
								<td><f:input ng-disabled="!newModel" path="modelName" /><f:errors class='err' path="modelName"/></td>
							</tr>
							
							<tr>
								<td>Engine size:</td>
								<td><f:input path="engineSize" />litres <f:errors class='err' path="engineSize"/></td>
							</tr>
							
							<tr>
								<td>Engine type:</td>
								<td>
									<f:select path="engineType">
										<f:options items="${engines}" />
									</f:select>
								</td>
							</tr>
		
							<tr>
								<td>Engine aspiration:</td>
								<td>
									<f:select path="engineAspiration">
										<f:options items="${aspiration}" />
									</f:select>
								</td>
							</tr>
							
							<tr><td>Quarter mile time:</td><td><f:input path="quarterMileTime" />seconds</td></tr>
							<tr><td>0-100kph time:</td><td><f:input path="timeToOneHundred" />seconds</td></tr>
		
							<tr>
								<td colspan="2">
									Date of manufacture&nbsp;-&nbsp;
									<f:input path="century" type="number" min="19" max="20" />century
									<f:input path="decade" type="number" min="0" max="9" />decade
									<f:input path="year" type="number" min="0" max="9" />year
								</td>
							</tr>
		
							<tr><td colspan="2"><input type="file" name="carPic" id="carPic" /></td></tr>
							<tr><td><input type="submit" value="submit"></td></tr>
							
							</table>						
						</c:when>
						<c:otherwise>
							There are no makes in the database add one first. <a href='<c:url value="/makes/add"/>'>Add make.</a>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
			
		</div>
	</f:form>
	
	</div>
</body>

</html>