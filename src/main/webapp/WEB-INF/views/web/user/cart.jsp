<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <c:choose>
            <c:when test="${isEmpty}">
                <h1><i class="fa fa-shopping-cart"></i>&nbsp; Ваша корзина пуста</h1>
            </c:when>
            <c:otherwise>
                <h1><i class="fa fa-shopping-cart"></i>&nbsp; Ваша корзина</h1>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="container">

    <div class="" style="padding-top: 20px;">
        <%--@elvariable id="movies" type="java.util.List"--%>
        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
        <c:forEach items="${movies}" var="movie">

            <div class="row row-skip">
                <div class="width-2">
                    <img src="${movie.coverUri}" class="search-cover">
                </div>
                <div class="width-3">
                    <div class="title">${movie.title}</div>
                </div>
                <div class="width-3">
                    <c:set var="editLink"><c:url value="/web/app/personal/admin/movies/editMovie?id="/></c:set>
                    <a href="" class="btn btn-outline btn-red">Удалить</a>
                </div>
            </div>

        </c:forEach>

        <c:if test="${not isEmpty}">
            <c:set var="movieLink"><c:url value="/web/app/personal/user/shop/buy"/></c:set>
            <a href="${movieLink}" class="btn btn-outline btn-success">Купить</a>
        </c:if>

    </div>

</div>