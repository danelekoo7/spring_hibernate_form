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
    <title>edytowanie wydawcy</title>
</head>
<body>
<form:form method="post" modelAttribute="publisher">
    nazwa: <form:input path="name"/>
    <form:errors path="name"/>
    nip: <form:input path="nip"/>
    <form:errors path="nip"/>
    regon: <form:input path="regon"/>
    <form:errors path="regon"/>

    <input type="submit">
</form:form>
</body>
</html>
