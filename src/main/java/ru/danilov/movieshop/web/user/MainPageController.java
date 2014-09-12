package ru.danilov.movieshop.web.user;

import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MainPageController extends BaseController {

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);
    private MovieManager movieManager = ServiceContainer.getService(MovieManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("logout")) {
            logout(request, response);
        } else if (request.getRequestURI().contains("setLang")) {
            setLang(request, response);
        } else {
            mainView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

    }

    private void setLang(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        request.getSession().setAttribute("lang", lang);
        ModelAndView modelAndView = new ModelAndView("/user.main.tiles");
        modelAndView.putObject("movies", movieManager.getMovies(3));
        modelAndView.process(request, response);
    }


    public void mainView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/user.main.tiles");
        modelAndView.putObject("movies", movieManager.getMovies(3));
        modelAndView.process(request, response);
    }

    public void logout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        authManager.logout((String) session.getAttribute(AttributeNames.AUTH_DATA_KEY));
        response.sendRedirect("/movieshop/web/app/catalog");
    }

}
