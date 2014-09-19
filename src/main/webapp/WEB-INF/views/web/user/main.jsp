<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script src="//api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>

<%--@elvariable id="locale" type="java.util.Locale"--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="ru.danilov.res"/>

<div class="jumbotron jumbotron-ad hidden-print">
    <div class="container">
        <h1><i class="fa fa-video-camera"></i>&nbsp; Movie Shop</h1>
        <p>It's the shop. Of movies.</p>
    </div>
</div>

<div class="container">

    <c:set var="changeLangLink"><c:url value="/web/app/main/setLang?lang="/></c:set>
    <div class="row row-skip">
        <div class="width-12">
            <a class="btn btn-outline btn-normal" href="${changeLangLink}FR">French</a>
            <a class="btn btn-outline btn-normal" href="${changeLangLink}RU">Русский</a>
            <a class="btn btn-outline btn-normal" href="${changeLangLink}EN">English</a>
        </div>
    </div>

    <style>
        #map {
            width: 100%;
            height: 500px;
        }
    </style>
    <div class="container">
        <h3><fmt:message key="t_festivals"/></h3>

        <div id="map"></div>
    </div>

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

    ymaps.ready(init);

    function init () {
        myMap = new ymaps.Map('map', {
            center: [55.91, 37.73],
            zoom: 13
        });

        var festivals = ymaps.geoQuery({
            type: 'FeatureCollection',
            features: [
                {
                    type: 'Feature',
                    properties: {
                        balloonContent: '<b>Кинокиммерия</b>'
                    },
                    geometry: {
                        type: 'Point',
                        coordinates: [46.636608, 32.621786]
                    }
                },
                {
                    type: 'Feature',
                    properties: {
                        balloonContent: '<b>Отражение</b>'
                    },
                    geometry: {
                        type: 'Point',
                        coordinates: [55.989587, 37.219891]
                    }
                },
                {
                    type: 'Feature',
                    properties: {
                        balloonContent: '<b>Potential</b>'
                    },
                    geometry: {
                        type: 'Point',
                        coordinates: [59.926339, 30.376763]
                    }
                },
                {
                    type: 'Feature',
                    properties: {
                        balloonContent: '<b>Ale Kino</b>'
                    },
                    geometry: {
                        type: 'Point',
                        coordinates: [52.407879, 16.918473]
                    }
            }
            ]
        }).addToMap(myMap);


        function findClosestObjects(position) {
            var festival = festivals.getClosestTo(position);
            festival.balloon.open();
            myMap.setCenter(festival.geometry._tk);
        }

        ymaps.geolocation.get({
            provider: 'yandex',
            mapStateAutoApply: true
        }).then(function (result) {
            findClosestObjects(result.geoObjects.position);
        });

    }
</script>