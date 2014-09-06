<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 05.09.2014
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h3>Авторизация</h3>
            <%--@elvariable id="error" type="java.lang.String"--%>
            <c:if test="${error ne null}">
                <div class="" style="color: red">${error}</div>
            </c:if>
            <c:set var="actionUrl"><c:url value="/web/auth/doAuth"/></c:set>
            <form action="${actionUrl}">
                <div class="form-element">
                    <input id="login" placeholder="Логин" class="form-input" name="login" type="text">
                </div>
                <div class="form-element">
                    <input id="password" placeholder="Пароль" class="form-input" name="password" type="password">
                </div>
                <button class="btn btn-outline btn-success" type="submit">Авторизация</button>
                <c:set var="registerLink"><c:url value="#"/></c:set>
                <a class="btn btn-outline btn-normal" href="${registerLink}">Регистрация</a>
            </form>
        </div>
    </div>
</body>
</html>
