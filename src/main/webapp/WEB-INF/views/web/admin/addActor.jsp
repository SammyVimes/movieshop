<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 14.09.2014
  Time: 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="jumbotron">
    <div class="container">
        <div class="container">
            <h1><i class="fa fa-plus"></i>&nbsp; Добавление актёра.</h1>
        </div>
    </div>
</div>

<div class="page-wrapper">
    <div class="container">

        <%--@elvariable id="error" type="java.lang.String"--%>
        <c:if test="${error ne null}">
            <div class="easy-panel error">
                    ${error}
            </div>
        </c:if>

        <div class="panel panel-normal">
            <div class="panel-heading">
                <div class="panel-title">
                    Форма изменения фильма
                </div>
            </div>
            <div class="panel-body">
                <c:set var="action"><c:url value="/web/app/personal/admin/actors/addActor"/></c:set>
                <form action="${action}" method="post">
                    <div class="form-element width-12">
                        <label for="name" class="width-2">Имя</label>
                        <input class="form-input width-6" name="name" id="name">
                    </div>
                    <div class="form-element width-12">
                        <label for="sex" class="width-2">Мужчина?</label>
                        <input type="checkbox" name="sex" id="sex">
                    </div>
                    <button type="submit" class="btn btn-outline btn-success">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</div>