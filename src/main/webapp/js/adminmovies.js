/**
 * Created by Semyon on 07.09.2014.
 */

$(document).ready(function() {

    var $searchResults = $("#search-results");

    $("#search_btn").click(function() {
       var query = $("#search").val();
        $.ajax({
            url: "/movieshop/web/app/personal/admin/movies/search?query=" + query,
            dataType : "json"
        }).done(function(data) {
           showResults(data["movies"]);
        }).fail(function(message) {
            alert(data);
        });
    });

    function showResults(movies) {
        $searchResults.empty();
        for (var i = 0; i < movies.length; i++) {
            var movie = movies[i];
            var clone = $("#template").clone();
            clone.removeClass("hidden");
            clone.removeAttr("id");
            clone.find(".search-cover").attr("src", movie.coverUri);
            clone.find(".title").text(movie.title);
            var $editLink = clone.find(".edit-link");
            var oldVal = $editLink.attr("href");
            $editLink.attr("href", oldVal + movie.id);
            $searchResults.append(clone);
        }
    }

});
