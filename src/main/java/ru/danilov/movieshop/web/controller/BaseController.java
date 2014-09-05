package ru.danilov.movieshop.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 05.09.2014.
 */
public abstract class BaseController {

    public abstract void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException;

}
