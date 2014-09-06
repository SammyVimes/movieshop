package ru.danilov.movieshop.web.admin;

import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MoviesController extends BaseController {

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        mainView(request, response);
    }

    public void mainView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/admin.moviesList.tiles");
        modelAndView.process(request, response);
    }

}
