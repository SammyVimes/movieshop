package ru.danilov.movieshop.web.servlet;

import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.web.admin.MoviesController;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.AuthController;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.controller.RegisterController;
import ru.danilov.movieshop.web.user.CatalogController;
import ru.danilov.movieshop.web.user.MainPageController;
import ru.danilov.movieshop.web.user.ShoppingController;

import javax.servlet.ServletException;
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
        CatalogController catalogController = new CatalogController();
        controllerMap.put("/app/catalog", catalogController);
        MoviesController moviesController = new MoviesController();
        controllerMap.put("/app/personal/admin/movies", moviesController);
        ShoppingController shoppingController = new ShoppingController();
        controllerMap.put("/app/personal/user/shop", shoppingController);
        RegisterController registerController = new RegisterController();
        controllerMap.put("register", registerController);
        super.init();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        BaseController controller = findMatchingController(req.getRequestURI());
        if (controller != null) {
            controller.handleGetRequest(req, resp);
        } else {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        BaseController controller = findMatchingController(req.getRequestURI());
        if (controller != null) {
            controller.handlePostRequest(req, resp);
        } else {
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(req, resp);
        }
    }

}
