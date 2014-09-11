<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<div class="container">

    <div class="row">
        <div class="width-12 form-element input-group" style="padding: 10px;">
            <input class="form-input" id="search">
            <span class="input-addon btn btn-normal btn-outline" id="search_btn"><i class="fa fa-search"></i></span>
        </div>
    </div>

    <div id="search-results">

    </div>

</div>

<div class="hidden row row-skip" id="template">
    <div class="width-2">
        <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
        <img class="search-cover" src="${coverUri}${movie.coverUri}">
    </div>
    <div class="width-3">
        <div class="title"></div>
    </div>
    <div class="width-3">
        <c:set var="editLink"><c:url value="/web/app/personal/admin/movies/editMovie?id="/></c:set>
        <a class="btn btn-outline btn-normal edit-link" href="${editLink}">${sEdit}</a>
        <a href="" class="btn btn-outline btn-red">${sDelete}</a>
    </div>
</div>
