<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 05.09.2014
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="cssBaseUrl"><c:url value="/css/"/></c:set>
    <link rel="stylesheet" href="${cssBaseUrl}main.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}login.css" type="text/css">
    <title>Авторизация</title>
</head>
<body>
    <div class="container">
        <div class="offset-3 login-panel width-4">
            Hello guyz
            <div class="form-element">
                <input id="login" placeholder="Логин" class="form-input" name="login" type="text">
            </div>
            <div class="form-element">
                <input id="password" placeholder="Пароль" class="form-input" name="password" type="password">
            </div>
            <div class="btn btn-outline btn-success">Авторизация</div>
        </div>
    </div>
</body>
</html>
