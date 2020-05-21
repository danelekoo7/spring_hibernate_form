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
    <title>publishers all</title>
</head>
<body>
<table border='1' style='border-collapse:collapse'>
    <h2><a href="<c:url value="/publishers/add"/>">Dodaj nowego wydawcę</a></h2>
    <tbody>
    <th>name</th>
    <th>nip</th>
    <th>regon</th>
    <th>lista książek</th>
    <c:forEach items="${publishers}" var="publisher">
        <tr>
            <td><c:out value="${publisher.name}"/></td>
            <td><c:out value="${publisher.nip}"/></td>
            <td><c:out value="${publisher.regon}"/></td>
            <td><c:forEach items="${publisher.books}" var="book">
                <c:out value="${book.title}    "/>
            </c:forEach>
            </td>
            <td><a href="<c:url value="/publishers/form/edit/${publisher.id}"/>">Edytuj</a></td>
            <td><a href="<c:url value = "/publishers/form/delete/${publisher.id}"/>">Usun</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
