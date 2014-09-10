package ru.danilov.movieshop.web.user;

import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.comment.Comment;
import ru.danilov.movieshop.core.entity.comment.CommentManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.core.entity.user.UserSettings;
import ru.danilov.movieshop.core.money.Currency;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Semyon on 07.09.2014.
 */
public class ShoppingController extends BaseController {

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    private CommentManager commentManager = ServiceContainer.getService(CommentManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("buy")) {
            buyMovies(request, response);
        } else if (requestURI.contains("addToCart")) {
            addMovieToCart(request, response);
        } else if (requestURI.contains("owned")) {
            ownedMovies(request, response);
        } else if (requestURI.contains("remove")) {
            removeMovieFromCart(request, response);
        } else if (requestURI.contains("cart")) {
            cart(request, response);
        } else {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        if (requestURI.contains("addComment")) {
            addComment(request, response);
        }
    }

    private void addComment(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String commentString = request.getParameter("comment");
        String movieId = request.getParameter("movieId");
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        User user = null;
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            user = authData.getUser();
        }
        Movie movie = movieManager.getMovieById(Long.valueOf(movieId));
        Comment comment = new Comment();
        comment.setMovie(movie);
        comment.setUser(user);
        comment.setComment(commentString);
        comment.setDate(new Date());
        commentManager.addComment(comment);
        response.sendRedirect("/movieshop/web/app/catalog/movie?id=" + movieId);
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
            userSettings.setCart(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        List<Movie> movieList = userSettings.getMovies();
        modelAndView.putObject("movies", movieList);
        modelAndView.process(request, response);
    }

    private void cart(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/user.cart.tiles");
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
            userSettings.setCart(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        List<Movie> movieList = userSettings.getCart();
        if (movieList.isEmpty()) {
            modelAndView.putObject("isEmpty", true);
        }
        modelAndView.putObject("movies", movieList);
        modelAndView.process(request, response);
    }

    private void removeMovieFromCart(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
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
            userSettings.setCart(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        List<Movie> cart = userSettings.getCart();
        for (int i = 0; i < cart.size(); i++) {
            Movie _movie = cart.get(i);
            if (_movie.getId() == movie.getId()) {
                cart.remove(_movie);
            }
        }
        userManager.update(userSettings);
        response.sendRedirect("/movieshop/web/app/personal/user/shop/cart");
    }

    private void addMovieToCart(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
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
            userSettings.setCart(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        List<Movie> cart = userSettings.getCart();
        for (Movie _movie : cart) {
            if (_movie.getId() == movie.getId()) {
                ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
                modelAndView.process(request, response);
                return;
            }
        }
        cart.add(movie);
        userManager.update(userSettings);
        response.sendRedirect("/movieshop/web/app/personal/user/shop/cart");
    }

    private void buyMovies(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
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
            userSettings.setCart(new LinkedList<Movie>());
            userManager.createSettings(userSettings);
        }
        Double money = userSettings.getMoney();
        List<Movie> movieList = userSettings.getMovies();
        List<Movie> cart = userSettings.getCart();
        for (int i = 0; i < cart.size(); i++) {
            Movie _movie = cart.get(i);
            for (Movie __movie : movieList) {
                if (__movie.getId() == _movie.getId()) {
                    ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
                    modelAndView.process(request, response);
                    return;
                }
            }
            Double price = _movie.getPrice();
            if (_movie.getCurrency() == Currency.US_DOLLARS) {
                price *= 35;
            }
            money -= price;
            if (money <= 0) {
                ModelAndView modelAndView = new ModelAndView("/error.tiles");
                modelAndView.putObject("error", "Не достаточно средств");
                modelAndView.process(request, response);
                return;
            }
            movieList.add(_movie);
        }
        userSettings.setMoney(money);
        cart.clear();
        userManager.update(userSettings);
        response.sendRedirect("/movieshop/web/app/personal/user/shop/owned");
    }

}
