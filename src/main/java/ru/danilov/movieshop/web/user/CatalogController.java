package ru.danilov.movieshop.web.user;

import ru.danilov.movieshop.web.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 06.09.2014.
 */
public class CatalogController extends BaseController {


    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        catalogView(request, response);
    }

    public void catalogView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/user.catalog.tiles").forward(request, response);
    }

}
