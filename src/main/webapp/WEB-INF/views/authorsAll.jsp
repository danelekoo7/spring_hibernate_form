<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 09.05.2020
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>authors all</title>
</head>
<body>
<table>
    <h2><a href="<c:url value="/authors/add"/>">Dodaj nowego Autora</a></h2>
    <tbody>
    <th>imie</th>
    <th>nazwisko</th>
    <th>pesel</th>
    <th>email</th>
    <th>rok urodzenia</th>
    <th>lista książek</th>
    <c:forEach items="${authors}" var="author">
        <tr>
            <td><c:out value="${author.firstName}"/></td>
            <td><c:out value="${author.lastName}"/></td>
            <td><c:out value="${author.pesel}"/></td>
            <td><c:out value="${author.email}"/></td>
            <td><c:out value="${author.yearOfBirth}"/></td>
            <td><c:forEach items="${author.books}" var="book">
                <c:out value="${book.title}    "/>
            </c:forEach>
            </td>
            <td><a href="<c:url value="/author/form/edit/${author.id}"/>">Edytuj</a></td>
            <td><a href="<c:url value = "/author/form/delete/${author.id}"/>">Usun</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
