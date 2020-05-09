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
    <title>dodawanie ksiazki</title>
</head>
<body>
<form:form method="POST" modelAttribute="book">
    <form:input path="title"/>
    <form:input path="rating"/>
    <form:input path="description"/>

    <form:select path="publisher" itemLabel="name" itemValue="id" items="${publishers}"/>

    <input type="submit">
</form:form>
</body>
</html>
