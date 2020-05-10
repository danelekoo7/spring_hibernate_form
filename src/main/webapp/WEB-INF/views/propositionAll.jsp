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
    <title>proposition all</title>
</head>
<body>
<table>
    <tbody>
    <th>Title</th>
    <th>rating</th>
    <th>description</th>
    <th>publisher</th>
    <c:forEach items="${books}" var="book">
        <tr>
            <td><c:out value="${book.title    }"/></td>
            <td><c:out value="${book.rating}"/></td>
            <td><c:out value="${book.description}"/></td>
            <td><c:out value="${book.proposition}"/></td>
<%--            <td><c:out value="${book.publisher.name}"/></td>--%>
<%--            <td><c:forEach items="${authors}" var="author">--%>
<%--                <c:out value="${author.firstName    }"/>--%>

<%--            </c:forEach>--%>
<%--            </td>--%>
            <td><a href="<c:url value="/bookForm/edit/${book.id}"/>">Edytuj</a></td>
            <td><a href="<c:url value = "/bookForm/delete/${book.id}"/>">Usun</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
