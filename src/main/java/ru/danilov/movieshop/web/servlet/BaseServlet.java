package ru.danilov.movieshop.web.servlet;

import ru.danilov.movieshop.web.controller.BaseController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Semyon on 05.09.2014.
 */
public class BaseServlet extends HttpServlet {

    protected Map<String, BaseController> controllerMap = new HashMap<>();

    public BaseController findMatchingController(final String url) {
        for (String key : controllerMap.keySet()) {
            if (url.contains(key)) {
                return controllerMap.get(key);
            }
        }
        return null;
    }

}
