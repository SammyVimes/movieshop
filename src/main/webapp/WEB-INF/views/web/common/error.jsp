<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<fmt:setBundle basename="ru.danilov.res"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="cssBaseUrl"><c:url value="/css/"/></c:set>
    <link rel="stylesheet" href="${cssBaseUrl}main.css" type="text/css">
    <title><fmt:message key="s_error"/></title>
</head>
<body>

<div class="jumbotron jumbotron-green">
    <div class="container">
        <h1><fmt:message key="s_error"/>: ${error}</h1>
        <a href="javascript:history.back()"><h3><fmt:message key="s_back"/></h3></a>
    </div>
</div>

</body>
</html>
