<%@ page import="ru.danilov.movieshop.core.entity.movie.MovieGenre" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
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

<%
    List<MovieGenre> genres = Arrays.asList(MovieGenre.values());
    request.setAttribute("genres", genres);
%>

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
                            <input id="title" class="form-input width-6" type="text" name="title"
                                   value="${movie.title}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="localizedTitle" class="width-2">Название на русском языке (если есть)</label>
                            <input id="localizedTitle" class="form-input width-6" type="text" name="localizedTitle"
                                   value="${movie.localizedTitle}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="coverURL" class="width-2">URL картинки</label>
                            <input id="coverURL" class="form-input width-6" type="text" name="coverURL"
                                   value="${movie.coverUri}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="trailerURL" class="width-2">URL трейлера</label>
                            <input id="trailerURL" class="form-input width-6" type="text" name="trailerURL"
                                   value="${movie.trailerUri}">
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="genre" class="width-2">Жанр</label>
                            <select id="genre" class="form-input width-6" name="genre">
                                <c:forEach items="${genres}" var="genre">
                                    <option value="${genre.name}">${genre.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-element width-12">
                            <label for="price" class="width-2">Цена</label>
                            <input id="price" class="form-input width-6" type="text" name="price"
                                   value="${movie.price}">
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
                            <textarea id="description" rows="10" cols="45" class="form-input width-6"
                                      name="description">${movie.description}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="width-2">
                            <label>Актёрский состав</label>
                        </div>
                        <%--@elvariable id="actor" type="ru.danilov.movieshop.core.entity.actor.Actor"--%>
                        <%--@elvariable id="movieActors" type="java.util.List"--%>
                        <div class="width-9" id="actors">
                            <c:forEach items="${movieActors}" var="actor">
                                <div class="width-4">
                                    <div class="actor">
                                        <div class="form-element input-group">
                                            <input disabled class="actor-name form-input" value="${actor.name}">

                                            <div class="input-group-btn">
                                                <button class="btn btn-red btn-outline remove-actor" type="button"><i
                                                        class="fa fa-trash"></i></button>
                                            </div>
                                        </div>
                                        <input class="hidden actor-id" name="actor-id" value="${actor.id}">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row">
                        <div class="width-5">
                            <button type="submit" class="btn btn-outline btn-success">Сохранить</button>
                            <button type="button" id="actor-picker" class="btn btn-outline btn-normal">Выбор актёров
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>


<div class="hidden" id="template">
    <div class="width-4">
        <div class="actor">
            <div class="form-element input-group">
                <input disabled class="actor-name form-input">

                <div class="input-group-btn">
                    <button class="btn btn-red btn-outline remove-actor" type="button"><i class="fa fa-trash"></i>
                    </button>
                </div>
            </div>
            <input class="hidden actor-id" name="actor-id">
        </div>
    </div>
</div>

<div class="modal fade" id="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">Выбор актёров</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <c:forEach items="${allActors}" var="actor">
                        <div class="width-4">
                            <div class="actor">
                                <input disabled class="actor-name form-input" value="${actor.name}">
                                <input class="hidden actor-id" value="${actor.id}">
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<style>

    .actor {
        -webkit-transition: all 1s; /* Safari 3.1 to 6.0 */
        transition: all 1s;
        padding: 15px;
    }

    .actor.selected {
        background-color: red;
    }

</style>


<script>
    $(document).ready(function () {

        var genre = '${movie.genre}';
        var options = $({type: "id", value: "genre"}).find({type: "tag", value: "option"});
        var genreOption = options.find({type: "attr", value: {attr: "value", value: genre}});
        genreOption.attr("selected", "selected");

        var createTemplate = function () {
            var actors = $({type: "id", value: "actors"});
            return function (name, id) {
                var clone = $({type: "id", value: "template"}).clone();
                clone.removeAttr("id");
                clone.removeClass("hidden");
                clone.find({type: "class", value: "actor-id"}).val(id);
                clone.find({type: "class", value: "actor-name"}).val(name);
                actors.append(clone);
                return clone;
            }
        }();

        var modal = new Modal("modal");
        var actorPicker = $({type: "id", value: "actor-picker"});
        actorPicker.click(function () {
            modal.open();
        });
        var actors = $({type: "id", value: "actors"}).find({type: "class", value: "actor"});
        var modalActors = modal.getProtoQueryObject().find({type: "class", value: "actor"});

        var selectables = [];

        modalActors.each(function (e) {
            var selectable = $(e);
            var actorId = selectable.find({type: "class", value: "actor-id"}).val();
            var actorName = selectable.find({type: "class", value: "actor-name"}).val();
            selectable.select = function () {
                selectable.addClass("selected");
                selectable.view = createTemplate(actorName, actorId);
                var removeBtn = selectable.view.find({type: "class", value: "remove-actor"});
                removeBtn.click(function () {
                    selectable.deselect();
                });
                selectable.selected = true;
            };
            selectable.deselect = function () {
                selectable.removeClass("selected");
                selectable.selected = false;
                selectable.view.removeSelf();
            };
            selectable.click(function () {
                if (selectable.selected) {
                    selectable.deselect();
                } else {
                    selectable.select();
                }
            });
            var _actors = actors.elements;
            for (var i = 0; i < _actors.length; i++) {
                var e2 = _actors[i];
                var existedActor = $(e2);
                var e2Id = existedActor.find({type: "class", value: "actor-id"}).val();
                if (e2Id == actorId) {
                    var __noneed__ = function (existedActor) {
                        var removeBtn = existedActor.find({type: "class", value: "remove-actor"});
                        removeBtn.click(function () {
                            if (existedActor.selectable) {
                                existedActor.selectable.deselect();
                            }
                        });
                        existedActor.selectable = selectable;
                        selectable.view = existedActor.parent();
                        selectable.selected = true;
                        selectable.addClass("selected");
                    }(existedActor);
                    _actors.splice(i, 1);
                    break;
                }
            }
        });
    });
</script>