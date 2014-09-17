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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="ru.danilov.movieshop.core.entity.movie.Movie" %>
<%@ page import="ru.danilov.movieshop.web.util.UTF8ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%
    Locale locale = (Locale) request.getAttribute("locale");
    ResourceBundle resBound = new UTF8ResourceBundle("ru.danilov.res", locale);
    String sAddToCart = resBound.getString("s_add_to_cart");
    String sAlreadyGot = resBound.getString("s_already_got");
    String sActors = resBound.getString("s_actors");
    String sSend = resBound.getString("s_send");
    String sComments = resBound.getString("s_comments");
    String sNewComment = resBound.getString("s_new_comment");
    String sPrice = resBound.getString("s_price");
    String sDescription = resBound.getString("s_description");
    pageContext.setAttribute("sAddToCart", sAddToCart);
    pageContext.setAttribute("sAlreadyGot", sAlreadyGot);
    pageContext.setAttribute("sActors", sActors);
    pageContext.setAttribute("sComments", sComments);
    pageContext.setAttribute("sSend", sSend);
    pageContext.setAttribute("sNewComment", sNewComment);
    pageContext.setAttribute("sPrice", sPrice);
    pageContext.setAttribute("sDescription", sDescription);
    Movie movie = (Movie) request.getAttribute("movie");
    String sGenreTitle = resBound.getString("s_genre");
    String sGenre = resBound.getString(movie.getGenre().getProp());
    pageContext.setAttribute("sGenre", sGenre);
    pageContext.setAttribute("sGenreTitle", sGenreTitle);
%>

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
                <c:set var="coverUri"><c:url value="/content/?filePath="/></c:set>
                <img class="movie-cover" src="${coverUri}${movie.coverUri}">
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
                        <b>${sDescription}: </b>${movie.description}
                    </div>
                </div>
                <div class="row row-skip">
                    <div class="width-12 description row-skip">
                        <b>${sGenreTitle}: </b>${sGenre}
                    </div>
                </div>
                <div class="row">
                    <div class="width-12 price row-skip">
                        <b>${sPrice}: </b><m:money amount="${movie.price}" currency="${movie.currency}"/>
                    </div>
                </div>
                <br/>

                <div class="row row-skip">
                    <c:choose>
                        <%--@elvariable id="owned" type="java.lang.Boolean"--%>
                        <c:when test="${owned}">
                            <div class="easy-panel success">
                                    ${sAlreadyGot}
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:set var="buyLink"><c:url
                                    value="/web/app/personal/user/shop/addToCart?id=${movie.id}"/></c:set>
                            <a href="${buyLink}"
                               class="btn btn-outline btn-success btn-lg btn-block fa fa-shopping-cart"> ${sAddToCart}</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="width-5 tab active" id="comments-tab">${sComments}</div>
            <div class="width-5 tab" id="actors-tab">${sActors}</div>
        </div>
        <div class="row">
            <div class="row tab-content">
                <div class="width-12 hidden" id="actors">
                    <c:set var="actorShowUrl"><c:url value="/web/app/catalog/showActor?id="/></c:set>
                    <%--@elvariable id="actors" type="java.util.List"--%>
                    <%--@elvariable id="actor" type="ru.danilov.movieshop.core.entity.actor.Actor"--%>
                    <c:forEach items="${actors}" var="actor">
                        <div class="row">
                            <a href="${actorShowUrl}${actor.id}" class="underline-link offset-1 width-4"><h4>
                                <b>${actor.name}</b></h4></a>
                        </div>
                    </c:forEach>
                </div>
                <div class="width-12" id="comments-tab-content">
                    <div class="row row-skip">
                        <div class="width-12">
                            <div class="form-element">
                                <label for="comment-input"><b>${sNewComment}</b></label>
                                <textarea class="form-input width-12" id="comment-input" name="comment"></textarea>
                                <input class="hidden" name="movieId" value="${movie.id}">
                                <button type="button" class="btn btn-outline btn-normal width-3"
                                        id="add-comment">${sSend}</button>
                            </div>
                        </div>
                    </div>
                    <div id="comments">
                        <c:set var="avatarUri"><c:url value="/content/?filePath="/></c:set>
                        <%--@elvariable id="_comment" type="ru.danilov.movieshop.core.entity.comment.Comment"--%>
                        <c:forEach items="${comments}" var="_comment">
                            <div class="comment">
                                <div class="row">
                                    <div class="width-1">
                                        <img class="avatar" src="${avatarUri}${_comment.user.avatarURL}">
                                    </div>
                                    <div class="width-9">
                                        <div class="row">
                                            <div class="width-2">
                                                <b>
                                                    <c:set var="userlink"><c:url
                                                            value="/web/app/user/profile/showProfile?id=${_comment.user.id}"/></c:set>
                                                    <a href="${userlink}" class="user-link">${_comment.user.login}</a>
                                                </b>
                                            </div>
                                            <div class="width-6"><fmt:formatDate value="${_comment.date}"/></div>
                                        </div>
                                        <div class="row">
                                            <div class="width-7">${_comment.comment}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>


<div class="comment hidden" id="template">
    <div class="row">
        <div class="width-1">
            <img class="avatar" src="${avatarUri}${user.avatarURL}">
        </div>
        <div class="width-9">
            <div class="row">
                <div class="width-2">
                    <b>
                        <c:set var="userlink"><c:url
                                value="/web/app/user/profile/showProfile?id=${user.id}"/></c:set>
                        <a href="${userlink}" class="user-link">${user.login}</a>
                    </b>
                </div>
                <div class="width-6 comment-date"></div>
            </div>
            <div class="row">
                <div class="width-7 comment-text"></div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var $actors = $({type: "id", value: "actors"});
        var $commentsTabContent = $({type: "id", value: "comments-tab-content"});
        var $actorsTabBtn = $({type: "id", value: "actors-tab"});
        var $commentsTabBtn = $({type: "id", value: "comments-tab"});
        $actorsTabBtn.click(function () {
            $actors.removeClass("hidden");
            $commentsTabBtn.removeClass("active");
            if (!$commentsTabContent.hasClass("hidden")) {
                $commentsTabContent.addClass("hidden");
            }
            if (!$actorsTabBtn.hasClass("active")) {
                $actorsTabBtn.addClass("active");
            }
        });
        $commentsTabBtn.click(function () {
            $commentsTabContent.removeClass("hidden");
            $actorsTabBtn.removeClass("active");
            if (!$actors.hasClass("hidden")) {
                $actors.addClass("hidden");
            }
            if (!$commentsTabBtn.hasClass("active")) {
                $commentsTabBtn.addClass("active");
            }
        });

        var $comments = $({type: "id", value: "comments"});

        function createFromTemplate(text) {
            var clone = $({type: "id", value: "template"}).clone();
            clone.find({type: "class", value: "comment-text"}).text(text);
            var date = new Date();
            clone.find({type: "class", value: "comment-date"}).text(date.format("dd.mm.yyyy"));
            clone.removeClass("hidden");
            $comments.appendAsFirst(clone);
        }

        function addComment(text) {
            var request = new AjaxRequest({
                url: "/movieshop/web/app/personal/user/shop/addComment?movieId=${movie.id}&comment=" + text,
                method: "post",
                dataType: "json",
                contentType: "application/json"
            }).done(function (data) {
                        if (data["success"]) {
                            createFromTemplate(text);
                        } else {
                            window.location.href = "/movieshop/web/auth"
                        }
                    });
        }

        $({type: "id", value: "add-comment"}).click(function () {
            var text = $({type: "id", value: "comment-input"}).val();
            addComment(text);
        });

    });
</script>