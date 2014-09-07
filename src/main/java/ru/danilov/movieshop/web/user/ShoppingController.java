package ru.danilov.movieshop.web.user;

import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.core.entity.user.UserSettings;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semyon on 07.09.2014.
 */
public class ShoppingController extends BaseController {

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("buy")) {
            buyMovie(request, response);
        } else if (requestURI.contains("owned")) {
            ownedMovies(request, response);
        } else {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

    }

    private void ownedMovies(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/user.owned.tiles");
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        User user = null;
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            user = authData.getUser();
        }
        UserSettings userSettings = userManager.getUserSettings(user);
        if (userSettings == null) {
            userSettings = new UserSettings();
            userSettings.setMoney(4000.0);
            userSettings.setUser(user);
            userSettings.setMovies(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        List<Movie> movieList = userSettings.getMovies();
        modelAndView.putObject("movies", movieList);
        modelAndView.process(request, response);
    }

    private void buyMovie(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String movieIdString = request.getParameter("id");
        if (movieIdString == null || movieIdString.isEmpty()) {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
            return;
        }
        Long movieId = null;
        try {
            movieId = Long.valueOf(movieIdString);
        } catch (NumberFormatException e) {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
            return;
        }
        Movie movie = movieManager.getMovieById(movieId);
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        User user = null;
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            user = authData.getUser();
        }
        UserSettings userSettings = userManager.getUserSettings(user);
        if (userSettings == null) {
            userSettings = new UserSettings();
            userSettings.setMoney(4000.0);
            userSettings.setUser(user);
            userSettings.setMovies(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        List<Movie> movieList = userSettings.getMovies();
        for (Movie _movie : movieList) {
            if (_movie.getId() == movie.getId()) {
                ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
                modelAndView.process(request, response);
                return;
            }
        }
        movieList.add(movie);
        userManager.update(userSettings);
        response.sendRedirect("/movieshop/web/app/main");
    }

}
