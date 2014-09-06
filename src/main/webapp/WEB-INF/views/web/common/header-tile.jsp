<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-header">
    <div class="container">
        <div class="brand movieshop-brand">
            Movie Shop
        </div>
        <div class="controls">
            <c:set var="catalogLink"><c:url value="/web/app/catalog"/></c:set>
            <a href="${catalogLink}" class="control">
                <i class="fa fa-shopping-cart"></i>
            </a>
            <a href="#" class="control">
                <i class="fa fa-user"></i>
            </a>
            <c:set var="logoutLink"><c:url value="/web/app/main/logout"/></c:set>
            <a href="${logoutLink}" class="control">
                <i class="fa fa-power-off"></i>
            </a>
        </div>
    </div>
</div>