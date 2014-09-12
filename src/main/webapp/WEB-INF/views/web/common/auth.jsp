<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
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
    <c:set var="cssBaseUrl">${pageContext.request.contextPath}/css/</c:set>
    <link rel="stylesheet" href="${cssBaseUrl}main.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}login.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}panels.css" type="text/css">
    <title>Авторизация</title>
</head>
<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sAuth = resBound.getString("s_auth");
    String sReg = resBound.getString("s_reg");
    pageContext.setAttribute("sAuth", sAuth);
    pageContext.setAttribute("sReg", sReg);
%>
<body>
<div class="container">
    <div class="offset-3 login-panel width-4">
        <h3>Авторизация</h3>
        <%--@elvariable id="error" type="java.lang.String"--%>
        <c:if test="${error ne null}">
            <div class="easy-panel error">${error}</div>
        </c:if>
        <c:set var="actionUrl"><c:url value="/web/auth/doAuth"/></c:set>
        <form action="${actionUrl}">
            <div class="form-element">
                <input id="login" placeholder="Логин" class="form-input" name="login" type="text">
            </div>
            <div class="form-element">
                <input id="password" placeholder="Пароль" class="form-input" name="password" type="password">
            </div>
            <button class="btn btn-outline btn-success" type="submit">${sAuth}</button>
            <c:set var="registerLink"><c:url value="/web/register"/></c:set>
            <a class="btn btn-outline btn-normal" href="${registerLink}">${sReg}</a>
        </form>
    </div>
</div>
</body>
</html>
