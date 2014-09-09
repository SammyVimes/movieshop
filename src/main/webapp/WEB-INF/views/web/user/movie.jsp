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
            <div class="width-5 sm-width-12">
                <img class="movie-cover" src="${movie.coverUri}">
            </div>
            <div class="offset-1 width-6">
                <c:if test="${movie.trailerUri ne null}">

                    <div class="row">
                        <div class="width-12">

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
                <div class="row row-skip">
                    <c:choose>
                        <%--@elvariable id="owned" type="java.lang.Boolean"--%>
                        <c:when test="${owned}">
                            <div class="easy-panel success">
                                У вас уже есть этот фильм
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buyLink"><c:url value="/web/app/personal/user/shop/buy?id=${movie.id}"/></c:set>
                            <a href="${buyLink}" class="btn btn-outline btn-success btn-lg btn-block fa fa-shopping-cart"> Купить</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row row-skip">
                <div class="width-6 tab" id="actors-tab">Актерский состав</div>
                <div class="width-6 tab active" id="comments-tab">Отзывы</div>
            </div>
            <div class="row">
                <div class="width-12 hidden" id="actors">
                    ${actors}
                </div>
                <div class="width-12" id="comments">
                    <%--@elvariable id="_comment" type="ru.danilov.movieshop.core.entity.comment.Comment"--%>
                    <c:forEach items="${comments}" var="_comment">
                        <div class="comment">
                            <div class="row row-skip">
                                <div class="width-1"><b>${_comment.user.login}</b></div>
                                <div class="width-6">${_comment.date}</div>
                            </div>
                            <div class="row row-skip">
                                <div class="width-7">${_comment.comment}</div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>


</div>

<script>
    $(document).ready(function() {
       var $actors = $({type: "id", value: "actors"});
       var $comments = $({type: "id", value: "comments"});
       var $actorsTabBtn = $({type: "id", value: "actors-tab"});
       var $commentsTabBtn = $({type: "id", value: "comments-tab"});
       $actorsTabBtn.click(function() {
           $actors.removeClass("hidden");
           $commentsTabBtn.removeClass("active");
           if (!$comments.hasClass("hidden")) {
               $comments.addClass("hidden");
           }
           if (!$actorsTabBtn.hasClass("active")) {
               $actorsTabBtn.addClass("active");
           }
       });
       $commentsTabBtn.click(function() {
           $comments.removeClass("hidden");
           $actorsTabBtn.removeClass("active");
           if (!$actors.hasClass("hidden")) {
               $actors.addClass("hidden");
           }
           if (!$commentsTabBtn.hasClass("active")) {
               $commentsTabBtn.addClass("active");
           }
       });
    });
</script>