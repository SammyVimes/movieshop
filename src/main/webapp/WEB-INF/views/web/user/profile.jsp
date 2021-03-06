<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 10.09.2014
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--@elvariable id="locale" type="java.util.Locale"--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="ru.danilov.res"/>

<jsp:useBean id="profile" scope="request" class="ru.danilov.movieshop.core.entity.user.User"/>

<div class="jumbotron jumbotron-green hidden-print">
    <div class="container">
        <h1><i class="fa fa-user"></i>&nbsp; <fmt:message key="s_profile"/>
            <jsp:getProperty name="profile" property="login"/>
        </h1>

        <p>
            <%--@elvariable id="money" type="java.lang.Double"--%>
            <c:if test="${money ne null}">
                <fmt:message key="s_left"/>: <fmt:formatNumber type="number" value="${money}" maxFractionDigits="2"/>
            </c:if>
        </p>
    </div>
</div>

<%--@elvariable id="user" type="ru.danilov.movieshop.core.entity.user.User"--%>
<div class="container">
    <div class="row">
        <div class="width-3">
            <c:set var="avatarUri"><c:url value="/content/?filePath="/></c:set>
            <img class="avatar" src="${avatarUri}${profile.avatarURL}">
        </div>
    </div>
    <c:if test="${money ne null}">
        <div class="row">
            <div class="width-12">
                <c:set var="formAction"><c:url value="/web/app/user/profile/uploadAvatar"/></c:set>
                <form action="${formAction}" method="POST" enctype="multipart/form-data">
                    <input type="file" name="avatar" id="avatar">
                    <button type="submit" class="btn btn-outline btn-normal">Загрузить аватар</button>
                </form>
            </div>
        </div>
    </c:if>
    <div class="row">
        <%--@elvariable id="comment" type="ru.danilov.movieshop.core.entity.comment.Comment"--%>
        <c:forEach items="${comments}" var="comment">
            <div class="comment">
                <div class="row row-skip">
                    <c:set var="movieLink"><c:url value="/web/app/catalog/movie?id=${comment.movie.id}"/></c:set>
                    <div class="width-3"><b><fmt:message key="s_movie"/>: <a href="${movieLink}"
                                                          class="movie-link">${comment.movie.title}</a></b>
                    </div>
                    <div class="width-6"><fmt:formatDate value="${comment.date}"/></div>
                </div>
                <div class="row row-skip">
                    <div class="width-7">${comment.comment}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>