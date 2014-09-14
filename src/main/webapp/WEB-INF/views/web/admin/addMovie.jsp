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
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="jumbotron">
    <div class="container">
        <div class="container">
            <h1><i class="fa fa-plus"></i>&nbsp; Добавление фильма.</h1>
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
                            <textarea id="description" rows="10" cols="45" class="form-input width-6"
                                      name="description"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="width-2">
                            <label>Актёрский состав</label>
                        </div>
                        <div class="width-9" id="actors">

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
                    <c:forEach items="${actors}" var="actor">
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

        $({type: "class", value: "actor"}).click(function () {

            var selected = false;
            var view = null;

            return function () {
                var $this = $(this);

                function select() {
                    $this.addClass("selected");
                    var actorId = $this.find({type: "class", value: "actor-id"}).val();
                    var actorName = $this.find({type: "class", value: "actor-name"}).val();
                    view = createTemplate(actorName, actorId);
                    var removeBtn = view.find({type: "class", value: "remove-actor"});
                    removeBtn.click(function () {
                        deselect();
                    });
                    selected = true;
                }

                function deselect() {
                    $this.removeClass("selected");
                    selected = false;
                    view.removeSelf();
                }

                if (!selected) {
                    select();
                } else {
                    deselect();
                }

            }

        }());
    });
</script>