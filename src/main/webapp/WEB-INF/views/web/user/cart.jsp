<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://www.danilov.ru/moneytaglib" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sCart = resBound.getString("s_your_cart");
    String sCartEmpty = resBound.getString("s_is_empty");
    String sDelete = resBound.getString("s_delete");
    String sPrice = resBound.getString("s_price");
    String sBuy = resBound.getString("s_buy");
    pageContext.setAttribute("sCart", sCart);
    pageContext.setAttribute("sCartEmpty", sCartEmpty);
    pageContext.setAttribute("sDelete", sDelete);
    pageContext.setAttribute("sBuy", sBuy);
    pageContext.setAttribute("sPrice", sPrice);
%>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <c:choose>
            <c:when test="${isEmpty}">
                <h1><i class="fa fa-shopping-cart"></i>&nbsp; ${sCart} ${sCartEmpty}</h1>
            </c:when>
            <c:otherwise>
                <h1><i class="fa fa-shopping-cart"></i>&nbsp; ${sCart}</h1>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div class="container">

    <div class="" style="padding-top: 20px;">
        <%--@elvariable id="movies" type="java.util.List"--%>
        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
        <c:forEach items="${movies}" var="movie">

            <c:set var="movieUrl"><c:url value="/web/app/catalog/movie?id=${movie.id}"/></c:set>
            <div class="row row-skip card" >
                <div class="width-2">
                    <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
                    <a href="${movieUrl}"><img class="search-cover" src="${coverUri}${movie.coverUri}"></a>
                </div>
                <div class="width-3">
                    <div class="title"><a class="underline-link" href="${movieUrl}">${movie.title}</a></div>
                </div>
                <div class="width-2">
                    <div class="price">${sPrice}: <m:money amount="${movie.price}" currency="${movie.currency}"/></div>
                </div>
                <div class="width-3">
                    <c:set var="removeLink"><c:url value="/web/app/personal/user/shop/remove?id=${movie.id}"/></c:set>
                    <a href="${removeLink}" class="btn btn-outline btn-red">${sDelete}</a>
                </div>
            </div>

        </c:forEach>

        <c:if test="${not isEmpty}">
            <c:set var="movieLink"><c:url value="/web/app/personal/user/shop/buy"/></c:set>
            <a href="${movieLink}" class="btn btn-block btn-outline btn-success">${sBuy}</a>
        </c:if>

    </div>

</div>