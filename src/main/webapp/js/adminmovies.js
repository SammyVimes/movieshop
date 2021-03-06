/**
 * Created by Semyon on 07.09.2014.
 */

$(document).ready(function() {

    var $searchResults = $({type: "id", value: "search-results"});

    $({type: "id", value: "search_btn"}).click(function() {
       var query = $({type: "id", value: "search"}).val();
        new AjaxRequest({
            url: "/movieshop/web/app/personal/admin/movies/search?query=" + query,
            dataType: "json",
            contentType: "application/json"
        }).done(function(data) {
           showResults(data["movies"]);
        }).error(function(error) {
            alert(error);
        });
    });

    function showResults(movies) {
        $searchResults.empty();
        for (var i = 0; i < movies.length; i++) {
            var movie = movies[i];
            var clone = $({type: "id", value: "template"}).clone();
            clone.removeClass("hidden");
            clone.removeAttr("id");
            var searchCover = clone.find({type: "class", value: "search-cover"});
            searchCover.attr("src", searchCover.attr("src") + movie.coverUri);
            clone.find({type: "class", value: "title"}).text(movie.title);
            var $editLink = clone.find({type: "class", value: "edit-link"});
            var $deleteLink = clone.find({type: "class", value: "btn-delete"});
            $deleteLink.click(function ($deleteLink, movie, clone) {
                return function () {
                    var url = "/movieshop/web/app/personal/admin/movies/deleteMovie?id=" + movie.id;
                    new AjaxRequest({
                        url: url,
                        dataType: "json",
                        contentType: "application/json"
                    }).done(function (data) {
                            clone.removeSelf();
                        }).error(function (error) {
                            alert(error);
                        });
                }
            }($deleteLink, movie, clone));
            var oldVal = $editLink.attr("href");
            $editLink.attr("href", oldVal + movie.id);
            $searchResults.append(clone);
        }
    }

});
