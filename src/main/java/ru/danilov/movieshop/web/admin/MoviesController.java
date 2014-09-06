package ru.danilov.movieshop.web.admin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MoviesController extends BaseController {

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("search")) {
            searchForMovie(request, response);
        } else {
            mainView(request, response);
        }
    }

    public void mainView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/admin.moviesList.tiles");
        modelAndView.process(request, response);
    }

    public void searchForMovie(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        JSONObject jsonObject = new JSONObject();
        List<Movie> movies = movieManager.search(query);
        JSONArray moviesJsonArray = new JSONArray();
        jsonObject.put("movies", moviesJsonArray);
        if (movies != null) {
            for (Movie movie : movies) {
                JSONObject object = new JSONObject();
                object.put("title", movie.getTitle());
                object.put("id", movie.getId());
                object.put("coverUri", movie.getCoverUri());
                moviesJsonArray.add(object);
            }
        }
        sendJSONResponse(jsonObject, request, response);
    }

}
