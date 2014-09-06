/**
 * Created by Semyon on 07.09.2014.
 */

$(document).ready(function() {

    $("#search_btn").click(function() {
       var query = $("#search").val();
        $.ajax({
            url: "/movieshop/web/app/admin/movies/search?query=" + query,
            dataType : "json"
        }).done(function(data) {
           alert(data);
        }).fail(function(message) {
            alert(data);
        });
    });

});
