package ru.danilov.movieshop.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 05.09.2014.
 */
public class AuthController extends BaseController {

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (url.contains("doAuth")) {
            auth(request, response);
        } else {
            mainView(request, response);
        }
    }

    public void mainView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/web/common/auth.jsp").forward(request, response);
    }

    public void auth(final HttpServletRequest request, final HttpServletResponse response) {

    }

}
