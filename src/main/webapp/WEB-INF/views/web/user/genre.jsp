<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 15.09.2014
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="ru.danilov.movieshop.core.entity.movie.MovieGenre" %>
<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sGenreTitle = resBound.getString("s_genre");
    String sGenre = resBound.getString(((MovieGenre) request.getAttribute("genre")).getProp());
    pageContext.setAttribute("sGenre", sGenre);
    pageContext.setAttribute("sGenreTitle", sGenreTitle);
%>

<div class="jumbotron jumbotron-red jumbotron-ad hidden-print">
    <div class="container">
        <h1><i class="fa fa-search"></i>&nbsp; ${sGenreTitle}: ${sGenre}</h1>
    </div>
</div>

<tiles:insertAttribute name="sidebar"/>
<div class="sidebar-helper">

    <div class="container">
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