<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 09.05.2020
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>usuwanie ksiazki</title>
</head>
<body>
<table>
        <h1> czy na pewno chcesz usunąc ponższą ksiązke</h1>

        title: <c:out value="${oneBook.title}"/>
        rating: <c:out value="${oneBook.rating}"/>
        description: <c:out value="${oneBook.description}"/>
        publisher: <c:out value="${oneBook.publisher.name}"/>

        <td> <a href = "<c:url value = "/bookForm/deleteConfirm/true/${oneBook.id}"/>">TAK</a></td>
        <td> <a href = "<c:url value = "/bookForm/deleteConfirm/false/${oneBook.id}"/>">NIE</a></td>

</table>
</body>
</html>
