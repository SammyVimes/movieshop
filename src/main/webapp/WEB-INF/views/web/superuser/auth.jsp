<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 15.09.2014
  Time: 2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="cssBaseUrl">${pageContext.request.contextPath}/css/</c:set>
    <link rel="stylesheet" href="${cssBaseUrl}main.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}login.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}panels.css" type="text/css">
    <title>Авторизация</title>
</head>
<body>
<div class="container">
    <fmt:setBundle basename="ru.danilov.res"/>
    <div class="offset-3 login-panel width-4">
        <h1><fmt:message key="s_auth"/></h1>

        <form action="j_security_check" method="post" name="loginForm">
            <div class="form-element">
                <label for="login"><fmt:message key="s_login"/></label>
                <input id="login" class="form-input" name="j_username" type="text">
            </div>
            <div class="form-element">
                <label for="password"><fmt:message key="s_password"/></label>
                <input id="password" class="form-input" name="j_password" type="password">
            </div>
            <button class="btn btn-outline btn-success" type="submit"><fmt:message key="s_auth"/></button>
        </form>
    </div>
</div>
</body>
</html>
