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
    <title>dodawanie autora</title>
</head>
<body>
<form:form method="POST" modelAttribute="author">
    imie: <form:input path="firstName"/>
    <form:errors path="firstName"/>
    nazwisko: <form:input path="lastName"/>
    <form:errors path="lastName"/>
    pesel: <form:input path="pesel"/>
    <form:errors path="pesel"/>
    email: <form:input path="email"/>
    <form:errors path="email"/>
    rok urodzenia: <form:input path="yearOfBirth"/>
    <form:errors path="yearOfBirth"/>
    <br>
    ksiązki: <form:select path="books" itemLabel="title" itemValue="id" multiple="true" items="${books}"/>
    <form:errors path="books"/>

    <input type="submit">
</form:form>
</body>
</html>
