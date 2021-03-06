<%@ page import="ru.danilov.movieshop.core.entity.movie.MovieGenre" %>
<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%--@elvariable id="query" type="java.lang.String"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sPopularSide = resBound.getString("s_popular_side");
    pageContext.setAttribute("sPopularSide", sPopularSide);
    List<MovieGenre> genres = Arrays.asList(MovieGenre.values());
    request.setAttribute("genres", genres);
%>

<div id="sidebar" class="sidebar" role="navigation">
    <div class="sidebar-nav">
        <ul class="nav" id="side-menu">

            <li>
                <c:set var="searchSidebarLink"><c:url value="/web/app/catalog/search"/></c:set>
                <form action="${searchSidebarLink}" method="get">
                    <div class="input-group" style="padding: 10px;">
                        <input class="form-input" id="search" name="query" value="${query}">
                        <div class="input-group-btn">
                            <button class="btn btn-normal btn-outline" type="submit"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </form>
            </li>

            <li>
                <c:set var="popular"><c:url value="/web/app/catalog/popular"/></c:set>
                <a class="menu-link" href="${popular}"><i class="fa fa-star fa-fw"></i> ${sPopularSide}</a>
            </li>

            <c:set var="genreLink"><c:url value="/web/app/catalog/genre?genre="/></c:set>
            <c:forEach items="${genres}" var="genre">
                <li>
                    <a class="menu-link" href="${genreLink}${genre.name}"><i
                            class="fa ${genre.cssClass} fa-fw"></i> <%=resBound.getString(((MovieGenre) pageContext.getAttribute("genre")).getProp())%>
                    </a>
                </li>
            </c:forEach>

        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>