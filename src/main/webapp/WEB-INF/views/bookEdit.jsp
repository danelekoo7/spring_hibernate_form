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
    <title>edytowanie ksiazki</title>
</head>
<body>
<form:form method="post" modelAttribute="bookEdit">
    title: <form:input path="title"/>
    rating: <form:input path="rating"/>
    description: <form:input path="description"/>

    <form:select path="publisher" itemLabel="name" itemValue="id" items="${publishers}"/>

    <input type="submit">
</form:form>
</body>
</html>
