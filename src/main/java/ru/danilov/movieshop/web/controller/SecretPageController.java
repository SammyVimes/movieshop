package ru.danilov.movieshop.web.controller;

import ru.danilov.movieshop.web.base.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Semyon on 15.09.2014.
 */
public class SecretPageController extends BaseController {

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/secretpage.tiles");
        modelAndView.process(request, response);
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

    }

}
