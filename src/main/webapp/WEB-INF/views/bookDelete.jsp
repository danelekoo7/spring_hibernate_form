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

    title: <c:out value="${book.title}"/><br>
    rating: <c:out value="${book.rating}"/><br>
    description: <c:out value="${book.description}"/><br>
    publisher: <c:out value="${book.publisher.name}"/><br>
    authors: <c:forEach items="${book.authors}" var="author">
    <c:out value="${author.firstName}"/>
</c:forEach>
    <br>
    <td><a href="<c:url value = "/bookForm/deleteConfirm/true/${book.id}"/>">TAK</a></td>
    <td><a href="<c:url value = "/bookForm/deleteConfirm/false/${book.id}"/>">NIE</a></td>

</table>
</body>
</html>
