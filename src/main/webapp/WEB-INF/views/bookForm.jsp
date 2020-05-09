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
    title: <form:input path="title"/>
    rating: <form:input path="rating"/>
    description: <form:input path="description"/>
<br>
   publisher: <form:select path="publisher" itemLabel="name" itemValue="id" items="${publishers}"/>
   author: <form:select path="authors" itemLabel="lastName"  itemValue="id" items="${authors}"/>

    <input type="submit">
</form:form>
</body>
</html>
