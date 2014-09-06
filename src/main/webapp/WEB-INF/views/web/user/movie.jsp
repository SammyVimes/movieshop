<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="m" uri="http://www.danilov.ru/moneytaglib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                        <h2 style="margin-top: -4px;">
                            <b>${movie.title}</b>
                            <c:if test="${movie.localizedTitle ne null}">
                                <div class="light-text">${movie.localizedTitle}</div>
                            </c:if>
                        </h2>
                    </div>
                </div>
                <c:if test="${movie.trailerUri ne null}">

                    <div class="row">
                        <div class=width-12">

                            <iframe id="ytplayer" type="text/html" width="450" height="300"
                                    src="${movie.trailerUri}?controls=0&theme=light"
                                    frameborder="0"></iframe>
                        </div>

                    </div>

                </c:if>

                <div class="row row-skip">
                    <div class="width-12 description row-skip">
                        <b>Описание: </b>${movie.description}
                    </div>
                </div>
                <div class="row">
                    <div class="width-12 price row-skip">
                        <b>Цена: </b><m:money amount="${movie.price}" currency="${movie.currency}"/>
                    </div>
                </div>
                <br/>
                <div class="row">
                    <a href="#" class="btn btn-outline btn-success btn-lg btn-block fa fa-shopping-cart"> Купить</a>
                </div>
            </div>
        </div>
    </div>


</div>