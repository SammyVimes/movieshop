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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%--@elvariable id="locale" type="java.util.Locale"--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="ru.danilov.res"/>
<head>
    <c:set var="cssBaseUrl">${pageContext.request.contextPath}/css/</c:set>
    <link rel="stylesheet" href="${cssBaseUrl}main.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}login.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}panels.css" type="text/css">
    <title><fmt:message key="t_auth"/></title>
</head>

<body>
<div class="container">
    <div class="offset-3 login-panel width-4">
        <h3><fmt:message key="s_auth"/></h3>
        <%--@elvariable id="error" type="java.lang.String"--%>
        <c:if test="${error ne null}">
            <div class="easy-panel error">${error}</div>
        </c:if>
        <c:set var="actionUrl"><c:url value="/web/auth/doAuth"/></c:set>
        <form action="${actionUrl}">
            <div class="form-element">
                <label for="login"><fmt:message key="s_login"/></label>
                <input id="login" class="form-input" name="login" type="text">
            </div>
            <div class="form-element">
                <label for="password"><fmt:message key="s_password"/></label>
                <input id="password" class="form-input" name="password" type="password">
            </div>
            <button class="btn btn-outline btn-success" type="submit"><fmt:message key="s_auth"/></button>
            <c:set var="registerLink"><c:url value="/web/register"/></c:set>
            <a class="btn btn-outline btn-normal" href="${registerLink}"><fmt:message key="s_reg"/></a>
        </form>
    </div>
</div>
</body>
</html>
