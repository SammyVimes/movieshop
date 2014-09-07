package ru.danilov.movieshop.web.admin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.core.entity.movie.MovieManagerException;
import ru.danilov.movieshop.core.money.Currency;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieManager.class);

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("search")) {
            searchForMovie(request, response);
        } else if (request.getRequestURI().contains("addMovie")) {
            addMovieGetView(request, response);
        } else if (request.getRequestURI().contains("editMovie")) {
            editMovieGetView(request, response);
        } else {
            mainView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("addMovie")) {
            addMoviePost(request, response);
        } else if (requestURI.contains("editMovie")) {
            editMoviePost(request, response);
        } else {

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

    public void addMovieGetView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/admin.addMovie.tiles");
        modelAndView.process(request, response);
    }

    public void editMovieGetView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/admin.editMovie.tiles");
        String idString = request.getParameter("id");
        Long id = Long.valueOf(idString);
        Movie movie = movieManager.getMovieById(id);
        modelAndView.putObject("movie", movie);
        modelAndView.process(request, response);
    }

    public void addMoviePost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String localizedTitle = request.getParameter("localizedTitle");
        String coverURL = request.getParameter("coverURL");
        String trailerURL = request.getParameter("trailerURL");
        String priceString = request.getParameter("price");
        String popularString = request.getParameter("popular");
        String currencyString = request.getParameter("currency");
        String description = request.getParameter("description");
        Double price = null;
        if (!priceString.isEmpty()) {
            try {
                price = Double.valueOf(priceString);
            } catch (NumberFormatException e) {
                LOGGER.error("Failed to parse price: " + e.getMessage());
            }
        }

        Currency currency = null;
        if (!currencyString.isEmpty()) {
            try {
                currency = Currency.valueOf(currencyString);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Failed to parse currency: " + e.getMessage());
            }
        }

        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDescription(description);
        if (!localizedTitle.isEmpty()) {
            movie.setLocalizedTitle(localizedTitle);
        }
        movie.setCoverUri(coverURL);
        if (!trailerURL.isEmpty()) {
            movie.setTrailerUri(trailerURL);
        }
        if (popularString != null && popularString.equals("on")) {
            movie.setPopular(true);
        } else {
            movie.setPopular(false);
        }
        movie.setPrice(price);
        movie.setCurrency(currency);
        try {
            movieManager.createMovie(movie);
        } catch (MovieManagerException e) {
            LOGGER.error("Failed to save movie: " + e.getMessage());
            ModelAndView modelAndView = new ModelAndView("/admin.addMovie.tiles");
            modelAndView.putObject("error", e.getMessage());
            modelAndView.process(request, response);
        }
        response.sendRedirect("/movieshop/web/app/personal/admin/movies/editMovie?id=" + movie.getId());
    }

    public void editMoviePost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String idString = request.getParameter("id");
        String localizedTitle = request.getParameter("localizedTitle");
        String coverURL = request.getParameter("coverURL");
        String trailerURL = request.getParameter("trailerURL");
        String priceString = request.getParameter("price");
        String popularString = request.getParameter("popular");
        String currencyString = request.getParameter("currency");
        String description = request.getParameter("description");
        Double price = null;
        if (!priceString.isEmpty()) {
            try {
                price = Double.valueOf(priceString);
            } catch (NumberFormatException e) {
                LOGGER.error("Failed to parse price: " + e.getMessage());
            }
        }

        Currency currency = null;
        if (!currencyString.isEmpty()) {
            try {
                currency = Currency.valueOf(currencyString);
            } catch (IllegalArgumentException e) {
                LOGGER.error("Failed to parse currency: " + e.getMessage());
            }
        }

        Long id = Long.valueOf(idString);
        Movie movie = movieManager.getMovieById(id);
        Movie clone = movie.getClone();
        clone.setTitle(title);
        clone.setDescription(description);
        if (!localizedTitle.isEmpty()) {
            clone.setLocalizedTitle(localizedTitle);
        }
        clone.setCoverUri(coverURL);
        if (!trailerURL.isEmpty()) {
            clone.setTrailerUri(trailerURL);
        }
        if (popularString != null && popularString.equals("on")) {
            clone.setPopular(true);
        } else {
            clone.setPopular(false);
        }
        clone.setPrice(price);
        clone.setCurrency(currency);
        ModelAndView modelAndView = new ModelAndView("/admin.editMovie.tiles");
        modelAndView.putObject("movie", clone);
        try {
            movieManager.updateMovie(clone);
            modelAndView.putObject("success", "Информация обновлена");
        } catch (MovieManagerException e) {
            LOGGER.error("Failed to update movie: " + e.getMessage());
            modelAndView.putObject("error", e.getMessage());
        }
        modelAndView.process(request, response);
    }

}
