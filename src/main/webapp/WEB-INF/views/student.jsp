<%--
  Created by IntelliJ IDEA.
  User: daniel
  Date: 09.05.2020
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>student</title>
</head>
<body>
<form:form method="POST" modelAttribute="student">
    First Name: <form:input path="firstName"/>
    <br>
    Last Name: <form:input path="lastName"/>
    <br>
    gender Male: <form:radiobutton path="gender" value="male"/>
    <br>
    gender Female: <form:radiobutton path="gender" value="female"/>
    <br>
    country: <form:select path="country" items="${countryItems}"/>
    <br>
    notes: <form:textarea path="notes" rows="5" cols="30"/>
    <br>
    mailinglist: <form:checkbox path="mailingList"/>
    <br>

    programming Skills: <form:select path="programmingSkills" >
        <form:options items="${programmingSkills}"/>
        </form:select>
    <br>
    hobbies: <form:checkboxes items="${hobbies}" path="hobbies"/>

    <input type="submit">
</form:form>

</body>
</html>
