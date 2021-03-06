package ru.danilov.movieshop.web.admin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.aspect.HttpLoggable;
import ru.danilov.movieshop.core.aspect.RequiredParams;
import ru.danilov.movieshop.core.entity.actor.Actor;
import ru.danilov.movieshop.core.entity.actor.ActorManager;
import ru.danilov.movieshop.core.entity.comment.Comment;
import ru.danilov.movieshop.core.entity.comment.CommentManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieGenre;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.core.entity.movie.MovieManagerException;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.core.entity.user.UserSettings;
import ru.danilov.movieshop.core.money.Currency;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MoviesController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieManager.class);

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    private ActorManager actorManager = ServiceContainer.getService(ActorManager.class);

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private CommentManager commentManager = ServiceContainer.getService(CommentManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        if (request.getRequestURI().contains("search")) {
            searchForMovie(request, response);
        } else if (request.getRequestURI().contains("addMovie")) {
            addMovieGetView(request, response);
        } else if (request.getRequestURI().contains("editMovie")) {
            editMovieGetView(request, response);
        } else if (request.getRequestURI().contains("deleteMovie")) {
            deleteMovie(request, response);
        } else {
            mainView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("addMovie")) {
            addMoviePost(request, response);
        } else if (requestURI.contains("editMovie")) {
            editMoviePost(request, response);
        } else {
            show404(request, response);
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
        modelAndView.putObject("actors", actorManager.getAllActors());
        modelAndView.process(request, response);
    }

    public void editMovieGetView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/admin.editMovie.tiles");
        String idString = request.getParameter("id");
        Long id = Long.valueOf(idString);
        Movie movie = movieManager.getMovieById(id);
        modelAndView.putObject("movie", movie);
        List<Actor> actors = actorManager.getAllActorsOfMovie(movie);
        modelAndView.putObject("movieActors", actors);
        modelAndView.putObject("allActors", actorManager.getAllActors());
        modelAndView.process(request, response);
    }

    @HttpLoggable(variablesToLog = {"title", "localizedTitle", "coverURL",
            "trailerUrl", "price", "popular", "genre", "actor-id", "currency", "description"})
    @RequiredParams(value = {"title", "localizedTitle", "coverURL",
            "price", "genre", "actor-id", "currency", "description"},
            canBeEmpty = {false, false, false, true, false, true, false, false})
    public void addMoviePost(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String localizedTitle = request.getParameter("localizedTitle");
        String coverURL = request.getParameter("coverURL");
        String trailerURL = request.getParameter("trailerURL");
        String priceString = request.getParameter("price");
        String popularString = request.getParameter("popular");
        String genreString = request.getParameter("genre");
        MovieGenre genre = MovieGenre.valueOf(genreString);
        String currencyString = request.getParameter("currency");
        String[] actorsIds = request.getParameterValues("actor-id");
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

        movie.setGenre(genre);

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
            modelAndView.putObject("allActors", actorManager.getAllActors());
            modelAndView.process(request, response);
        }
        if (actorsIds != null) {
            for (String actorIdString : actorsIds) {
                Long actorId;
                try {
                    actorId = Long.valueOf(actorIdString);
                    try {
                        Actor actor = actorManager.getActorById(actorId);
                        actor.getMovies().add(movie);
                        actorManager.updateActor(actor);
                    } catch (Exception e) {
                        LOGGER.error("Exception raised while adding actor: " + e.getMessage());
                        ModelAndView modelAndView = new ModelAndView("/admin.addMovie.tiles");
                        modelAndView.putObject("error", e.getMessage());
                        modelAndView.putObject("allActors", actorManager.getAllActors());
                        modelAndView.process(request, response);
                    }
                } catch (NumberFormatException e) {
                    LOGGER.trace("Bad actor id: " + e.getMessage());
                }
            }
        }
        response.sendRedirect("/movieshop/web/app/personal/admin/movies/editMovie?id=" + movie.getId());
    }

    @HttpLoggable(variablesToLog = {"id", "title", "localizedTitle", "coverURL", "price", "popular", "genre", "actor-id", "currency", "description"})
    @RequiredParams(value = {"id", "title", "localizedTitle", "coverURL", "price", "genre", "actor-id", "currency", "description"},
            canBeEmpty = {false, false, false, true, false, true, false, false})
    public void editMoviePost(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String idString = request.getParameter("id");
        String localizedTitle = request.getParameter("localizedTitle");
        String coverURL = request.getParameter("coverURL");
        String trailerURL = request.getParameter("trailerURL");
        String priceString = request.getParameter("price");
        String popularString = request.getParameter("popular");
        String genreString = request.getParameter("genre");
        MovieGenre genre = MovieGenre.valueOf(genreString);
        String[] actorsIds = request.getParameterValues("actor-id");
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
        clone.setGenre(genre);
        clone.setCurrency(currency);
        ModelAndView modelAndView = new ModelAndView("/admin.editMovie.tiles");
        modelAndView.putObject("movie", clone);
        if (actorsIds != null) {
            List<Actor> newMovieActors = new LinkedList<>();
            List<Actor> actorsLeft = actorManager.getAllActorsOfMovie(movie);
            for (String actorIdString : actorsIds) {
                Long actorId;
                try {
                    actorId = Long.valueOf(actorIdString);
                    try {
                        Actor actor = actorManager.getActorById(actorId);
                        if (!actorsLeft.remove(actor)) {
                            newMovieActors.add(actor);
                        }
                    } catch (Exception e) {
                        LOGGER.error("Exception raised while adding actor: " + e.getMessage());
                        modelAndView.putObject("error", e.getMessage());
                        List<Actor> actors = actorManager.getAllActorsOfMovie(movie);
                        modelAndView.putObject("movieActors", actors);
                        modelAndView.putObject("allActors", actorManager.getAllActors());
                        modelAndView.process(request, response);
                    }
                } catch (NumberFormatException e) {
                    LOGGER.trace("Bad actor id: " + e.getMessage());
                }
            }
            try {
                for (Actor actor : actorsLeft) {
                    actor.getMovies().remove(movie);
                    actorManager.updateActor(actor);
                }
                for (Actor actor : newMovieActors) {
                    boolean hasMovie = false;
                    for (Movie movie1 : actor.getMovies()) {
                        if (movie1.getId() == clone.getId()) {
                            hasMovie = true;
                            break;
                        }
                    }
                    if (!hasMovie) {
                        actor.getMovies().add(movie);
                        actorManager.updateActor(actor);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Exception raised while adding actor: " + e.getMessage());
                modelAndView.putObject("error", e.getMessage());
                List<Actor> actors = actorManager.getAllActorsOfMovie(movie);
                modelAndView.putObject("movieActors", actors);
                modelAndView.putObject("allActors", actorManager.getAllActors());
                modelAndView.process(request, response);
            }
        } else {
            List<Actor> movieActors = actorManager.getAllActorsOfMovie(movie);
            for (Actor actor : movieActors) {
                actor.getMovies().remove(movie);
                actorManager.updateActor(actor);
            }
        }
        try {
            movieManager.updateMovie(clone);
            modelAndView.putObject("success", "Информация обновлена");
            List<Actor> actors = actorManager.getAllActorsOfMovie(clone);
            modelAndView.putObject("movieActors", actors);
            modelAndView.putObject("allActors", actorManager.getAllActors());
        } catch (MovieManagerException e) {
            List<Actor> actors = actorManager.getAllActorsOfMovie(movie);
            modelAndView.putObject("movieActors", actors);
            modelAndView.putObject("allActors", actorManager.getAllActors());
            LOGGER.error("Failed to update movie: " + e.getMessage());
            modelAndView.putObject("error", e.getMessage());
        }
        modelAndView.process(request, response);
    }

    @RequiredParams(value = "id", canBeEmpty = false)
    private void deleteMovie(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String idString = request.getParameter("id");
        Long id = null;
        try {
            id = Long.valueOf(idString);
        } catch (NumberFormatException e) {
            LOGGER.error("Id is not a long number");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            Movie movie = movieManager.getMovieById(id);
            List<UserSettings> settings = userManager.getUserSettingsForMovie(movie);
            List<Comment> comments = commentManager.getCommentsForMovie(movie);
            List<Actor> actors = actorManager.getAllActorsOfMovie(movie);
            for (Comment comment : comments) {
                commentManager.remove(comment);
            }
            for (UserSettings _settings : settings) {
                _settings.getCart().remove(movie);
                _settings.getMovies().remove(movie);
                userManager.update(_settings);
            }
            for (Actor actor : actors) {
                actor.getMovies().remove(movie);
                actorManager.updateActor(actor);
            }
            movieManager.remove(movie);
            jsonObject.put("success", true);
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("error", "Failed to delete: " + e.getMessage());
        }
        sendJSONResponse(jsonObject, request, response);
    }

}
