<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 08.09.2014
  Time: 0:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron jumbotron-red">
    <div class="container">
        <div class="container">
            <h1><i class="fa fa-rocket"></i> Регистрация</h1>
        </div>
    </div>
</div>

<div class="page-wrapper">

    <div class="container">

        <%--@elvariable id="error" type="java.lang.String"--%>
        <c:if test="${error ne null}">
            <div class="easy-panel error">${error}</div>
        </c:if>

        <c:set var="formLink"><c:url value="/web/register"/></c:set>
        <form action="${formLink}" method="post">

            <div class="row">
                <div class="form-element width-12">
                    <label for="login" class="width-2">Логин</label>
                    <input id="login" class="form-input width-6" type="text" name="login">
                </div>
            </div>

            <div class="row">
                <div class="form-element width-12">
                    <label for="password" class="width-2">Пароль</label>
                    <input id="password" class="form-input width-6" type="text" name="password">
                </div>
            </div>

            <div class="row">
                <div class="form-element width-12">
                    <label for="repeat-password" class="width-2">Повторить пароль</label>
                    <input id="repeat-password" class="form-input width-6" type="text" name="repeat-password">
                </div>
            </div>

            <button type="submit" class="btn btn-outline btn-normal">Зарегистрироваться</button>

        </form>

    </div>

</div>
