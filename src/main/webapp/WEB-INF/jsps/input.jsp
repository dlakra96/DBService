<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form method="post" action="/input" modelAttribute="new_quote">

 <p> <b>  User Name:-  "${new_quote.userName}"</b> </p>
<p>
<form:select path="quote">
<form:options items="${quotesList}"/>
</form:select>
</p>
<input type="submit" value="Submit">  
</form:form>
</body>
</html>