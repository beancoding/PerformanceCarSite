<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="FastFoursApp">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="<c:url value='/angularlibs/angular.js'/>"></script>
	<script src="<c:url value='/libs/js/autoregion-controller.js'/>"></script>
	<title>Add car</title>
</head>
<body>

	<c:url var="addCarUrl" value='/makes/add' />
	<f:form modelAttribute="make" action="${addCarUrl}" method="POST">
	
		<f:errors/>
		<div ng-controller="AutoRegionController">
		
			<table>
		
			<tr>
				<td>Continent:</td>
				<td>
					<f:select path="continentId" ng-model="continent" ng-change="getCountries()">
						<f:options items="${continents}" itemLabel="name" itemValue="name"/>
					</f:select>
				</td>
			</tr>
			
			<tr>
				<td><span ng-style="{'display':displayCountries()}">Country:</span></td>
				<td>
					<select id="countriesId" name="countriesId" ng-style="{'display':displayCountries()}">
						<option ng-repeat="c in countries" value="{{c.name}}">{{c.name}}</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td>Make:</td>
				<td>
					<f:input path="makeName"/>
					<f:errors path="makeName"></f:errors>
				</td>
			</tr>
			
			</table>
			
		</div>
		<input type="submit" value="submit"/>
	</f:form>
</body>
</html>