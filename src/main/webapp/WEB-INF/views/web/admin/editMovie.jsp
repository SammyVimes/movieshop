<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 07.09.2014
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron">
    <div class="container">
        <div class="container">
            <h1><i class="fa fa-plus"></i>&nbsp; Изменение информации о фильме.</h1>
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

        <%--@elvariable id="success" type="java.lang.String"--%>
        <c:if test="${success ne null}">
            <div class="easy-panel success">
                    ${success}
            </div>
        </c:if>

        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>

        <div class="panel panel-normal">
            <div class="panel-heading">
                <div class="panel-title">
                    Форма изменения фильма
                </div>
            </div>
            <div class="panel-body">
                <c:set var="formLink"><c:url value="/web/app/personal/admin/movies/editMovie"/></c:set>
                <form action="${formLink}" method="post">
                    <input class="hidden" name="id" value="${movie.id}">
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="title" class="width-2">Название</label>
                            <input id="title" class="form-input width-6" type="text" name="title" value="${movie.title}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="localizedTitle" class="width-2">Название на русском языке (если есть)</label>
                            <input id="localizedTitle" class="form-input width-6" type="text" name="localizedTitle" value="${movie.localizedTitle}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="coverURL" class="width-2">URL картинки</label>
                            <input id="coverURL" class="form-input width-6" type="text" name="coverURL" value="${movie.coverUri}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="trailerURL" class="width-2">URL трейлера</label>
                            <input id="trailerURL" class="form-input width-6" type="text" name="trailerURL" value="${movie.trailerUri}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="price" class="width-2">Цена</label>
                            <input id="price" class="form-input width-6" type="text" name="price" value="${movie.price}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="popular" class="width-2">Поместить в "популярные"</label>
                            <c:choose>
                                <c:when test="${movie.popular}">
                                    <input type="checkbox" id="popular" name="popular" checked>
                                </c:when>
                                <c:otherwise>
                                    <input type="checkbox" id="popular" name="popular">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="currency" class="width-2">Валюта</label>
                            <select class="form-input width-6" id="currency" name="currency">
                                <c:choose>
                                    <c:when test="${movie.currency == 'RUS_RUBLES'}">
                                        <option selected value="RUS_RUBLES">Рубли</option>
                                        <option value="US_DOLLARS">Доллары</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="RUS_RUBLES">Рубли</option>
                                        <option selected value="US_DOLLARS">Доллары</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="description" class="width-2">Описание</label>
                            <textarea id="description" rows="10" cols="45" class="form-input width-6" name="description">${movie.description}</textarea>
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