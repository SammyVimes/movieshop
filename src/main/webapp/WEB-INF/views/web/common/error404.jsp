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
    String s_404 = resBound.getString("s_404");
    pageContext.setAttribute("s_404", s_404);
%>

<div class="jumbotron jumbotron-green">
    <div class="container">
        <h1>${s_404}</h1>
        <c:set var="mainPageLink"><c:url value="/web/app/main"/></c:set>
        <a href="${mainPageLink}"><h3>GET TO THE CHOPPA!</h3></a>
    </div>
</div>

</body>
</html>
