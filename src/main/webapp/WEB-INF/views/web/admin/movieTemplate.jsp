<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 15.09.2014
  Time: 1:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>

<%
    Locale locale = request.getLocale();
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sDelete = resBound.getString("s_delete");
    String sEdit = resBound.getString("s_edit");
    pageContext.setAttribute("sDelete", sDelete);
    pageContext.setAttribute("sEdit", sEdit);
%>

<div class="hidden row row-skip" id="template">
    <div class="width-2 sm-width-2">
        <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
        <img class="search-cover" src="${coverUri}${movie.coverUri}">
    </div>
    <div class="width-3 sm-width-3">
        <div class="title"></div>
    </div>
    <div class="width-3 sm-width-3">
        <c:set var="editLink"><c:url value="/web/app/personal/admin/movies/editMovie?id="/></c:set>
        <a class="btn btn-outline btn-normal edit-link" href="${editLink}">${sEdit}</a>
        <a href="" class="btn btn-outline btn-red">${sDelete}</a>
    </div>
</div>