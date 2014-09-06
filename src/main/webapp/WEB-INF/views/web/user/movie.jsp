<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">

    <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>

    <div class="movie-container">
        <div class="width-4">
            <img src="${movie.coverUri}">
        </div>

        <div class="width-8">
            <div class="row">
                <div class="width-3">Описание: ${movie.description}</div>
            </div>
            <div class="row">
                <div class="width-3">Цена: ${movie.price}</div>
            </div>
        </div>
    </div>

</div>