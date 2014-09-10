<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%
    Locale locale = request.getLocale();
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sYourMovies = resBound.getString("s_your_movies");
    pageContext.setAttribute("sYourMovies", sYourMovies);
%>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <h1><i class="fa fa-rocket"></i>&nbsp; ${sYourMovies}</h1>
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
                    <img src="${movie.coverUri}">

                    <div class="newstextblock">
                        <span>${movie.title}</span>
                    </div>
                </div>
            </a>

        </c:forEach>

    </div>

</div>