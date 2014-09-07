package ru.danilov.movieshop.web.user;

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
public class CatalogController extends BaseController {

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("/catalog/popular")) {
            popularMovies(request, response);
        } else if(request.getRequestURI().contains("/catalog/movie")) {
            showMovie(request, response);
        } else {
            catalogView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

    }

    public void catalogView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user.catalog.tiles").forward(request, response);
    }

    public void popularMovies(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView("/user.catalog.popular.tiles");
        List<Movie> popularMovies = movieManager.getPopularMovies();
        mav.putObject("popular", popularMovies);
        mav.process(request, response);
    }

    public void showMovie(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String movieIdString = request.getParameter("id");
        Long movieId = Long.valueOf(movieIdString);
        ModelAndView mav = new ModelAndView("/user.catalog.movie.tiles");
        Movie movie = movieManager.getMovieById(movieId);
        mav.putObject("movie", movie);
        mav.process(request, response);
    }

}
