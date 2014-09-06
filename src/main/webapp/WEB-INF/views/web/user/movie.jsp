<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>

<div class="jumbotron jumbotron-green jumbotron-ad hidden-print">
    <div class="container">
        <h1><i class="fa fa-play"></i>&nbsp; ${movie.title}</h1>
    </div>
</div>



<div class="container">

    <div class="movie-container">

        <div class="row">
            <div class="width-6">
                <img src="${movie.coverUri}">
            </div>
            <div class="width-5">
                <div class="row">
                    <div class="width-12">
                        <h2>
                            <b>${movie.title}</b>
                            <c:if test="${movie.localizedTitle ne null}">
                                <div class="light-text">${movie.localizedTitle}</div>
                            </c:if>
                        </h2>
                    </div>
                </div>
                <div class="row">
                    <div class="width-12 description">
                        <b>Описание: </b>${movie.description}
                    </div>
                </div>
                <div class="row">
                    <div class="width-12 price">
                        <b>Цена: </b>${movie.price}
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>