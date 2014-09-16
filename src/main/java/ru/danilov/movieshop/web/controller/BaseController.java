package ru.danilov.movieshop.web.controller;

import org.json.simple.JSONObject;
import ru.danilov.movieshop.web.base.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Semyon on 05.09.2014.
 */
public abstract class BaseController {

    public abstract void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception;

    public abstract void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception;

    public void sendJSONResponse(final JSONObject jsonObject, final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toJSONString());
        out.flush();
    }

    public void show404(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
        modelAndView.process(request, response);
    }

}
