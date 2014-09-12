<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 10.09.2014
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sProfile = resBound.getString("s_profile");
    String sLeft = resBound.getString("s_left");
    String sMovie = resBound.getString("s_movie");
    pageContext.setAttribute("sProfile", sProfile);
    pageContext.setAttribute("sLeft", sLeft);
    pageContext.setAttribute("sMovie", sMovie);
%>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <h1><i class="fa fa-user"></i>&nbsp; ${sProfile} ${profile.login}</h1>

        <p>
            <%--@elvariable id="money" type="java.lang.Double"--%>
            <c:if test="${money ne null}">
                ${sLeft}: ${money}
            </c:if>
        </p>
    </div>
</div>

<%--@elvariable id="user" type="ru.danilov.movieshop.core.entity.user.User"--%>
<div class="container">
    <div class="row">
        <div class="width-3">
            <img src="">
        </div>
        <div class="width-3">
        </div>
    </div>
    <div class="row">
        <%--@elvariable id="comment" type="ru.danilov.movieshop.core.entity.comment.Comment"--%>
        <c:forEach items="${comments}" var="comment">
            <div class="comment">
                <div class="row row-skip">
                    <c:set var="movieLink"><c:url value="/web/app/catalog/movie?id=${comment.movie.id}"/></c:set>
                    <div class="width-3"><b>${sMovie}: <a href="${movieLink}"
                                                          class="movie-link">${comment.movie.title}</a></b>
                    </div>
                    <div class="width-6">${comment.date}</div>
                </div>
                <div class="row row-skip">
                    <div class="width-7">${comment.comment}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>