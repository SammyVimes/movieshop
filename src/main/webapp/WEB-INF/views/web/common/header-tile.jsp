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
        <c:set var="brandLink"><c:url value="/web/app/main"/></c:set>
        <a href="${brandLink}" class="brand movieshop-brand">
            Movie Shop
        </a>
        <div class="controls">
            <%--@elvariable id="user" type="ru.danilov.movieshop.core.entity.user.User"--%>
            <c:if test="${user.userRole eq 'ADMIN'}">
                <c:set var="searchLink"><c:url value="/web/app/personal/admin/movies/"/></c:set>
                <a href="${searchLink}" class="control">
                    <i class="fa fa-search"></i>
                </a>
                <c:set var="addNewActor"><c:url value="/web/app/personal/admin/actors/addActor"/></c:set>
                <a href="${addNewActor}" class="control">
                    <i class="fa fa-group"></i>
                </a>
                <c:set var="addNew"><c:url value="/web/app/personal/admin/movies/addMovie"/></c:set>
                <a href="${addNew}" class="control">
                    <i class="fa fa-plus"></i>
                </a>
            </c:if>
            <c:if test="${user.userRole eq 'USER'}">
                <c:set var="owned"><c:url value="/web/app/personal/user/shop/owned"/></c:set>
                <a href="${owned}" class="control">
                    <i class="fa fa-folder-o"></i>
                </a>
            </c:if>
                <c:set var="catalogUrl"><c:url value="/web/app/catalog"/></c:set>
                <a href="${catalogUrl}" class="control">
                    <i class="fa fa-book"></i>
                </a>
            <c:choose>
                <c:when test="${user ne null}">
                    <c:set var="cart"><c:url value="/web/app/personal/user/shop/cart"/></c:set>
                    <a href="${cart}" class="control">
                        <i class="fa fa-shopping-cart"></i>
                    </a>
                    <c:set var="profileLink"><c:url value="/web/app/user/profile/showProfile?id=${user.id}"/></c:set>
                    <a href="${profileLink}" class="control">
                    <i class="fa fa-user"></i>
                    </a>
                    <c:set var="logoutLink"><c:url value="/web/app/main/logout"/></c:set>
                    <a href="${logoutLink}" class="control">
                        <i class="fa fa-power-off"></i>
                    </a>
                </c:when>
                <c:otherwise>
                    <c:set var="loginLink"><c:url value="/web/auth"/></c:set>
                    <a href="${loginLink}" class="control">
                        <i class="fa fa-plug"></i>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>