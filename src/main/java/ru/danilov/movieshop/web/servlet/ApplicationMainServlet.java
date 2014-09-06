package ru.danilov.movieshop.web.servlet;

import ru.danilov.movieshop.core.entity.user.UserDAO;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.web.controller.AuthController;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.user.MainPageController;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 05.09.2014.
 */
public class ApplicationMainServlet extends BaseServlet {

    private UserManager userManager;

    @Override
    public void init() throws ServletException {
        AuthController authController = new AuthController();
        controllerMap.put("/auth", authController);
        MainPageController mainPageController= new MainPageController();
        controllerMap.put("/app/main", mainPageController);
        super.init();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        BaseController controller = findMatchingController(req.getRequestURI());
        if (controller != null) {
            controller.handleRequest(req, resp);
        }
    }
}
