<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<%--@elvariable id="locale" type="java.util.Locale"--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="ru.danilov.res"/>
<head>
    <tiles:useAttribute id="title" name="title" classname="java.lang.String"/>
    <title><fmt:message key="${title}"/></title>

    <c:set var="cssBaseUrl">${pageContext.request.contextPath}/css/</c:set>
    <link rel="stylesheet" href="${cssBaseUrl}items.css" type="text/css">
    <link rel="stylesheet" href="${cssBaseUrl}movie.css" type="text/css">
    <c:set var="libBaseUrl">${pageContext.request.contextPath}/lib/</c:set>
    <link rel="stylesheet" href="${libBaseUrl}css/font-awesome.css" type="text/css">

    <tiles:useAttribute id="jsList" name="jsList" classname="java.util.List"/>
    <c:forEach items="${jsList}" var="jsRawLink">
        <c:set var="jsLink">${pageContext.request.contextPath}${jsRawLink}</c:set>
        <script src="${jsLink}">void(0)</script>
    </c:forEach>


    <tiles:useAttribute id="cssList" name="cssList" classname="java.util.List"/>
    <c:forEach items="${cssList}" var="cssRawLink">
        <c:set var="cssLink">${pageContext.request.contextPath}${cssRawLink}</c:set>
        <link rel="stylesheet" href="${cssLink}" type="text/css">
    </c:forEach>

</head>

<body>
<tiles:insertAttribute name="header"/>
<tiles:insertAttribute name="page-content"/>
</body>

</html>