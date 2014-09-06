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

<tiles:insertAttribute name="sidebar"/>

<div class="container">

    <%--@elvariable id="popular" type="java.util.List"--%>
    <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
    <c:forEach items="${popular}" var="movie">

        <div class="item width-4 news">
            <img src="${movie.coverUri}">
            <a href="/item/27"></a>
            <div class="newstextblock">
                <span>${movie.title}</span>
                впервые оффлайн
            </div>
        </div>

    </c:forEach>

</div>