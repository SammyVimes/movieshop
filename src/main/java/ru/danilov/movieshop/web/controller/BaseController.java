package ru.danilov.movieshop.web.controller;

import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Semyon on 05.09.2014.
 */
public abstract class BaseController {

    public abstract void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException;

    public void sendJSONResponse(final JSONObject jsonObject, final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toJSONString());
        out.flush();
    }

}
