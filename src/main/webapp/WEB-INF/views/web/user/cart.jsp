<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://www.danilov.ru/moneytaglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="locale" type="java.util.Locale"--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="ru.danilov.res"/>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <c:choose>
            <c:when test="${isEmpty}">
                <h1><i class="fa fa-shopping-cart"></i>&nbsp; <fmt:message key="s_your_cart"/> <fmt:message
                        key="s_is_empty"/></h1>
            </c:when>
            <c:otherwise>
                <h1><i class="fa fa-shopping-cart"></i>&nbsp; <fmt:message key="s_your_cart"/></h1>
            </c:otherwise>
        </c:choose>
    </div>
</div>


<div class="container">

    <div class="" style="padding-top: 20px;">
        <%--@elvariable id="movies" type="java.util.List"--%>
        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
        <c:set var="fullPrice" value="0"/>
        <c:forEach items="${movies}" var="movie">

            <c:set var="movieUrl"><c:url value="/web/app/catalog/movie?id=${movie.id}"/></c:set>
            <div class="row row-skip card">
                <div class="width-2">
                    <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
                    <a href="${movieUrl}"><img class="search-cover" src="${coverUri}${movie.coverUri}"></a>
                </div>
                <div class="width-3">
                    <div class="title"><a class="underline-link" href="${movieUrl}">${movie.title}</a></div>
                </div>
                <div class="width-2">
                    <div class="price"><fmt:message key="s_price"/>: <m:money amount="${movie.price}"
                                                                              currency="${movie.currency}"/></div>
                </div>
                <div class="width-3">
                    <c:set var="removeLink"><c:url value="/web/app/personal/user/shop/remove?id=${movie.id}"/></c:set>
                    <a href="${removeLink}" class="btn btn-outline btn-red"><fmt:message key="s_delete"/></a>
                </div>
                <c:choose>
                    <c:when test="${movie.currency eq 'US_DOLLARS'}">
                        <c:set var="fullPrice" value="${fullPrice + (movie.price * 35)}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="fullPrice" value="${fullPrice + movie.price}"/>
                    </c:otherwise>
                </c:choose>
            </div>

        </c:forEach>
        <div class="row row-skip">
            <div class="width-12">
                <div class="price"><fmt:message key="s_full_price"/>: ${fullPrice} руб.</div>
            </div>
        </div>
        <c:if test="${not isEmpty}">
            <c:set var="movieLink"><c:url value="/web/app/personal/user/shop/buy"/></c:set>
            <a href="${movieLink}" class="btn btn-block btn-outline btn-success"><fmt:message key="s_buy"/></a>
        </c:if>

    </div>

</div>