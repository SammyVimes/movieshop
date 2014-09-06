package ru.danilov.movieshop.web.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Semyon on 06.09.2014.
 */
public class ModelAndView {

    private String viewName;

    private Map<String, Object> vars = new HashMap<>();

    public ModelAndView(final String viewName) {
        this.viewName = viewName;
    }

    public void putObject(final String name, final Object object) {
        vars.put(name, object);
    }

    public void process(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        for (Map.Entry<String, Object> entry : vars.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.getRequestDispatcher(viewName).forward(request, response);
    }

}
