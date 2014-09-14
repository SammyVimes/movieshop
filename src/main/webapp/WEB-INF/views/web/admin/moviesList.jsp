<%--
  Created by IntelliJ IDEA.
  User: Semyon
  Date: 06.09.2014
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="container">

    <div class="row">
        <div class="width-12 form-element input-group" style="padding: 10px;">
            <input class="form-input" id="search">
            <span class="input-addon btn btn-normal btn-outline" id="search_btn"><i class="fa fa-search"></i></span>
        </div>
    </div>

    <div id="search-results">

    </div>

</div>

<jsp:include page="movieTemplate.jsp"/>

<script>

    function setCookie(c_name, value, exdays) {
        var exdate = new Date();
        exdate.setDate(exdate.getDate() + exdays);
        var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
        document.cookie = c_name + "=" + c_value;
    }

    function getCookie(c_name) {
        var i, x, y, ARRcookies = document.cookie.split(";");
        for (i = 0; i < ARRcookies.length; i++) {
            x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
            y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);
            x = x.replace(/^\s+|\s+$/g, "");
            if (x == c_name) {
                return unescape(y);
            }
        }
    }


    $(document).ready(function () {
        function sort(query) {
            query = query.toLowerCase();
            var res = searchResults.find({type: "class", value: "row"});
            res.each(function (e) {
                var $e = $(e);
                var title = $e.find({type: "class", value: "title"}).elements[0].innerHTML.toLowerCase();
                if (title.indexOf(query) == -1 && query != "") {
                    $e.addClass("hidden");
                } else {
                    $e.removeClass("hidden");
                }
            });
        }

        var searchInput = $({type: "id", value: "search"});
        searchInput.val(getCookie("query"));
        var searchResults = $({type: "id", value: "search-results"});
        searchInput.each(function (e) {
            e.onkeyup = function (event) {
                var query = searchInput.val();
                setCookie("query", query, 1000000);
                sort(query);
            }
        });
    });
</script>