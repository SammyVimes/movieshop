package ru.danilov.movieshop.web.user;

import ru.danilov.movieshop.core.aspect.HttpLoggable;
import ru.danilov.movieshop.core.aspect.RequiredParams;
import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.actor.Actor;
import ru.danilov.movieshop.core.entity.actor.ActorManager;
import ru.danilov.movieshop.core.entity.comment.Comment;
import ru.danilov.movieshop.core.entity.comment.CommentManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieGenre;
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
import java.util.List;

/**
 * Created by Semyon on 06.09.2014.
 */
public class CatalogController extends BaseController {

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private CommentManager commentManager = ServiceContainer.getService(CommentManager.class);

    private ActorManager actorManager = ServiceContainer.getService(ActorManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if (request.getRequestURI().contains("/catalog/popular")) {
            popularMovies(request, response);
        } else if(request.getRequestURI().contains("/catalog/movie")) {
            showMovie(request, response);
        } else if(request.getRequestURI().contains("/catalog/search")) {
            search(request, response);
        } else if (request.getRequestURI().contains("/catalog/showActor")) {
            showActor(request, response);
        } else if (request.getRequestURI().contains("/catalog/genre")) {
            genres(request, response);
        } else {
            catalogView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

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

    public void search(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView("/user.catalog.search.tiles");
        String query = request.getParameter("query");
        if (query == null) {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
            return;
        }
        List<Movie> found = movieManager.search(query);
        mav.putObject("movies", found);
        mav.putObject("query", query);
        mav.process(request, response);
    }

    public void genres(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView("/user.catalog.genre.tiles");
        String genreString = request.getParameter("genre");
        if (genreString == null) {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
            return;
        }
        MovieGenre movieGenre = MovieGenre.valueOf(genreString);
        List<Movie> found = movieManager.getByGenre(movieGenre);
        mav.putObject("movies", found);
        mav.putObject("genre", movieGenre);
        mav.process(request, response);
    }

    @RequiredParams(value = "id", canBeEmpty = {false})
    @HttpLoggable(variablesToLog = "id")
    public void showMovie(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String movieIdString = request.getParameter("id");
        Long movieId = Long.valueOf(movieIdString);
        ModelAndView mav = new ModelAndView("/user.catalog.movie.tiles");
        Movie movie = movieManager.getMovieById(movieId);
        mav.putObject("movie", movie);

        List<Comment> comments = commentManager.getCommentsForMovie(movie);
        mav.putObject("comments", comments);

        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            if (authData != null) {
                User user = authData.getUser();
                if (user != null) {
                    UserSettings settings = userManager.getUserSettings(user);
                    if (settings != null) {
                        List<Movie> movies = settings.getMovies();
                        for (Movie _movie : movies) {
                            if (_movie.getId() == movieId) {
                                mav.putObject("owned", true);
                                break;
                            }
                        }
                        movies = settings.getCart();
                        for (Movie _movie : movies) {
                            if (_movie.getId() == movieId) {
                                mav.putObject("owned", true);
                                break;
                            }
                        }
                    }
                }
            }
        }
        List<Actor> actors = actorManager.getAllActorsOfMovie(movie);
        mav.putObject("actors", actors);
        mav.process(request, response);
    }

    @RequiredParams(value = "id", canBeEmpty = {false})
    public void showActor(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("/user.catalog.showActor.tiles");
        String actorIdString = request.getParameter("id");
        Long actorId;
        try {
            actorId = Long.valueOf(actorIdString);
        } catch (NumberFormatException e) {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
            return;
        }
        Actor actor = actorManager.getActorById(actorId);
        mav.putObject("actor", actor);
        List<Movie> movies = actor.getMovies();
        mav.putObject("movies", movies);
        mav.process(request, response);
    }

}
