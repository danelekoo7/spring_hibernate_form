<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 10.05.2020
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>validation</title>
</head>
<body>
<h2>ksiazki</h2>
<c:forEach items="${book}" var="v">
    <tr>
        <td><c:out value="${v.getPropertyPath()}: "/></td>
        <td><c:out value="${v.getMessage()}"/></td>
        <br>
    </tr>
</c:forEach>
<h2>autorzy</h2>
<c:forEach items="${author}" var="v">
    <tr>
        <td><c:out value="${v.getPropertyPath()}: "/></td>
        <td><c:out value="${v.getMessage()}"/></td>
        <br>
    </tr>
</c:forEach>
<h2>wydawcy</h2>
<c:forEach items="${publisher}" var="v">
    <tr>
        <td><c:out value="${v.getPropertyPath()}: "/></td>
        <td><c:out value="${v.getMessage()}"/></td>
        <br>
    </tr>
</c:forEach>
</body>
</html>
