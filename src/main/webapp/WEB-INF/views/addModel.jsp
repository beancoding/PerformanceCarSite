<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add model</title>
	</head>
	
	<body>
		<c:url var="addModelUrl" value="/models/add"/>
		<f:form action="${addModelUrl}" method="POST" modelAttribute="vehicleModel">
			name:<f:input path="modelName"/><br/>
			size:<f:input path="engineSize" /><br/>
			<f:select path="engineType">
				<f:options items="${engines}" />
			</f:select>
			<br/>
			<f:select path="engineAspiration">
				<f:options items="${aspiration}" />
			</f:select>
			<br/>
			quarterMile<f:input path="quarterMileTime"/><br/>
			0-100<f:input path="timeToOneHundred"/><br/>
			<f:select path="make">
				<f:options items="${makes}" itemValue="name" itemLabel="name" />
			</f:select>
			<br/>
			century:<f:input path="century" type="number" min="19" max="20" />
			decade:<f:input path="decade" type="number" min="0" max="9" />
			year:<f:input path="year" type="number" min="0" max="9" />
			<input type="submit" value="submit">
		</f:form>
	</body>
	
</html>