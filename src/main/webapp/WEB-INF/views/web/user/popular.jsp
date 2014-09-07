<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="jumbotron jumbotron-red jumbotron-ad hidden-print">
    <div class="container">
        <h1><i class="fa fa-star"></i>&nbsp; Самое популярное</h1>
    </div>
</div>

<tiles:insertAttribute name="sidebar"/>
<div class="sidebar-helper">

    <div class="">
        <%--@elvariable id="popular" type="java.util.List"--%>
        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
        <c:forEach items="${popular}" var="movie">

            <c:set var="movieLink"><c:url value="/web/app/catalog/movie?id=${movie.id}"/></c:set>
            <a href="${movieLink}">
                <div class="item item-width news">
                    <img src="${movie.coverUri}">

                    <div class="newstextblock">
                        <span>${movie.title}</span>
                    </div>
                </div>
            </a>

        </c:forEach>

    </div>

</div>