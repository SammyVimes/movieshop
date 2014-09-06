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
                <div class="form-element input-group" style="padding: 10px;">
                    <input class="form-input" id="search">
                    <span class="input-addon btn btn-normal btn-outline"><i class="fa fa-search"></i></span>
                </div>
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