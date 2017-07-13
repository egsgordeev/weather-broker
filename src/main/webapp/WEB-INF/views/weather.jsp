<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>wb</title>
</head>
<body>
    <h1>WEATHER BROKER</h1>
    <h2>Get familiar with the weather</h2>
    <form:form action="#" th:action = "" modelAttribute="localWeather" method="POST" >
        <p>Type in a city: <form:input path="city" /></p>
        <p><input type="submit" value = "submit"></p>
    </form:form>
</body>
</html>


