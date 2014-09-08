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
                <a class="menu-link" href="${popular}"><i class="fa fa-star fa-fw"></i> Популярное</a>
            </li>

            <li>
                <a class="menu-link" href="#" ><i class="fa fa-flash fa-fw"></i> Новинки</a>
            </li>

            <li>
                <a class="menu-link" href="#" ><i class="fa  fa-money fa-fw"></i> Скидки</a>
            </li>

        </ul>
    </div>
    <!-- /.sidebar-collapse -->
</div>