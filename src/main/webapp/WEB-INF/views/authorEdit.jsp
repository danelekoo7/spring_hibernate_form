<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 09.05.2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>edytowanie autora</title>
</head>
<body>
<form:form method="post" modelAttribute="author">
    first Name : <form:input path="firstName"/>
    <form:errors path="firstName"/>
    last Name: <form:input path="lastName"/>
    <form:errors path="lastName"/>
    pesel: <form:input path="pesel"/>
    <form:errors path="pesel"/>
    email: <form:input path="email"/>
    <form:errors path="email"/>
    yearOfBirth: <form:input path="yearOfBirth"/>
    <form:errors path="yearOfBirth"/>

    <input type="submit">
</form:form>
</body>
</html>
