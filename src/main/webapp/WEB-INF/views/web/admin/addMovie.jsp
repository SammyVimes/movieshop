<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 07.09.2014
  Time: 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron">
    <div class="container">
        <div class="container">
            <h1><i class="fa fa-plus"></i>&nbsp; Добавление фильма.</h1>
        </div>
    </div>
</div>

<%--@elvariable id="error" type="java.lang.String"--%>
<c:if test="${error ne null}">
    ${error}
</c:if>

<div class="page-wrapper">
    <div class="container">

        <div class="panel panel-normal">
            <div class="panel-heading">
                <div class="panel-title">
                    Форма нового фильма
                </div>
            </div>
            <div class="panel-body">
                <c:set var="formLink"><c:url value="/web/app/personal/admin/movies/addMovie"/></c:set>
                <form action="${formLink}" method="post">
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="title" class="width-2">Название</label>
                            <input id="title" class="form-input width-6" type="text" name="title">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="localizedTitle" class="width-2">Название на русском языке (если есть)</label>
                            <input id="localizedTitle" class="form-input width-6" type="text" name="localizedTitle">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="coverURL" class="width-2">URL картинки</label>
                            <input id="coverURL" class="form-input width-6" type="text" name="coverURL">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="trailerURL" class="width-2">URL трейлера</label>
                            <input id="trailerURL" class="form-input width-6" type="text" name="trailerURL">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="price" class="width-2">Цена</label>
                            <input id="price" class="form-input width-6" type="text" name="price">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="popular" class="width-2">Поместить в "популярные"</label>
                            <input type="checkbox" id="popular" name="popular">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="currency" class="width-2">Валюта</label>
                            <select class="form-input width-6" id="currency" name="currency">
                                <option value="RUS_RUBLES">Рубли</option>
                                <option value="US_DOLLARS">Доллары</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="description" class="width-2">Описание</label>
                            <textarea id="description" rows="10" cols="45" class="form-input width-6" name="description"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="width-1">
                            <button type="submit" class="btn btn-outline btn-success">Сохранить</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>