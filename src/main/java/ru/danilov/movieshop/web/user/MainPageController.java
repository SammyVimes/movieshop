package ru.danilov.movieshop.web.user;

import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MainPageController extends BaseController {

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("logout")) {
            logout(request, response);
        } else {
            mainView(request, response);
        }
    }

    public void mainView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/common.tiles").forward(request, response);
    }

    public void logout(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        authManager.logout((String) session.getAttribute(AttributeNames.AUTH_DATA_KEY));
        response.sendRedirect("/web/auth");
    }

}
