<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="cssBaseUrl"><c:url value="/css/"/></c:set>
    <link rel="stylesheet" href="${cssBaseUrl}main.css" type="text/css">
    <title>404</title>
</head>
<body>

<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String s_error = resBound.getString("s_error");
    pageContext.setAttribute("s_error", s_error);
    String s_back = resBound.getString("s_back");
    pageContext.setAttribute("s_back", s_back);
%>

<div class="jumbotron jumbotron-green">
    <div class="container">
        <h1>${s_error}: ${error}</h1>
        <a href="javascript:history.back()"><h3>${s_back}</h3></a>
    </div>
</div>

</body>
</html>
