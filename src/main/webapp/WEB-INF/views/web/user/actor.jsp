<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 14.09.2014
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sFem = resBound.getString("s_films_with_fem");
    String sMal = resBound.getString("s_films_with_mal");
    pageContext.setAttribute("sFem", sFem);
    pageContext.setAttribute("sMal", sMal);
%>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <h1>
            <%--@elvariable id="actor" type="ru.danilov.movieshop.core.entity.actor.Actor"--%>
            <c:choose>
                <c:when test="${actor.sex eq 'true'}">
                    <i class="fa fa-male"></i>&nbsp;${sMal} ${actor.name}
                </c:when>
                <c:otherwise>
                    <i class="fa fa-female"></i>&nbsp;${sFem} ${actor.name}
                </c:otherwise>
            </c:choose>
        </h1>
    </div>
</div>

<div class="container">

    <div class="">
        <%--@elvariable id="movies" type="java.util.List"--%>
        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
        <c:forEach items="${movies}" var="movie">

            <c:set var="movieLink"><c:url value="/web/app/catalog/movie?id=${movie.id}"/></c:set>
            <a href="${movieLink}">
                <div class="item item-width news">
                    <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
                    <img src="${coverUri}${movie.coverUri}">

                    <div class="newstextblock">
                        <span>${movie.title}</span>
                    </div>
                </div>
            </a>

        </c:forEach>

    </div>

</div>