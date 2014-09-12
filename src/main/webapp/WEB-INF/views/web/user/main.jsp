<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="//api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>

<div class="jumbotron jumbotron-ad hidden-print">
    <div class="container">
        <h1><i class="fa fa-video-camera"></i>&nbsp; Movie Shop</h1>
        <p>It's the shop. Of movies.</p>
    </div>
</div>

<div class="container">

    <c:set var="changeLangLink"><c:url value="/web/app/main/setLang?lang="/></c:set>
    <a href="${changeLangLink}FR">French</a>
    <a href="${changeLangLink}RU">Русский</a>
    <a href="${changeLangLink}EN">English</a>

    <style>
        #map {
            width: 500px;
            height: 500px;
        }
    </style>
    <div id="map"></div>

    <div class="">
        <%--@elvariable id="movies" type="java.util.List"--%>
        <%--@elvariable id="movie" type="ru.danilov.movieshop.core.entity.movie.Movie"--%>
        <c:forEach items="${movies}" var="movie">

            <c:set var="movieLink"><c:url value="/web/app/catalog/movie?id=${movie.id}"/></c:set>
            <a href="${movieLink}">
                <div class="item item-width news">
                    <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
                    <img src="${coverUri}${movie.coverUri}">

                    <div class="newstextblock">
                        <span>${movie.title}</span>
                    </div>
                </div>
            </a>

        </c:forEach>

    </div>

</div>

<script>
    var myMap;

    // Дождёмся загрузки API и готовности DOM.
    ymaps.ready(init);

    function init () {
        // Создание экземпляра карты и его привязка к контейнеру с
        // заданным id ("map").
        myMap = new ymaps.Map('map', {
            // При инициализации карты обязательно нужно указать
            // её центр и коэффициент масштабирования.
            center: [55.91, 37.73], // Москва
            zoom: 13
        });

        myGeoObject = new ymaps.GeoObject({
            // Описание геометрии.
            geometry: {
                type: "Point",
                coordinates: [55.91, 37.73]
            },
            // Свойства.
            properties: {
                // Контент метки.
                iconContent: 'Welcome to Мытищи',
                hintContent: 'NOW GO BACK'
            }
        }, {
            // Опции.
            // Иконка метки будет растягиваться под размер ее содержимого.
            preset: 'islands#blackStretchyIcon',
            // Метку можно перемещать.
            draggable: true
        });
        myMap.geoObjects.add(myGeoObject);
    }
</script>