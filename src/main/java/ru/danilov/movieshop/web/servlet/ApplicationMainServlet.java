package ru.danilov.movieshop.web.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.web.admin.ActorsController;
import ru.danilov.movieshop.web.admin.MoviesController;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.AuthController;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.controller.RegisterController;
import ru.danilov.movieshop.web.controller.SecretPageController;
import ru.danilov.movieshop.web.user.CatalogController;
import ru.danilov.movieshop.web.user.MainPageController;
import ru.danilov.movieshop.web.user.ProfileController;
import ru.danilov.movieshop.web.user.ShoppingController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 05.09.2014.
 */
public class ApplicationMainServlet extends BaseServlet {

    private Logger LOGGER = LoggerFactory.getLogger(ApplicationMainServlet.class);

    @Override
    public void init() throws ServletException {
        AuthController authController = new AuthController();
        controllerMap.put("/auth", authController);
        MainPageController mainPageController = new MainPageController();
        controllerMap.put("/app/main", mainPageController);
        CatalogController catalogController = new CatalogController();
        controllerMap.put("/app/catalog", catalogController);
        MoviesController moviesController = new MoviesController();
        controllerMap.put("/app/personal/admin/movies", moviesController);
        ShoppingController shoppingController = new ShoppingController();
        controllerMap.put("/app/personal/user/shop", shoppingController);
        RegisterController registerController = new RegisterController();
        controllerMap.put("register", registerController);
        ProfileController profileController = new ProfileController();
        controllerMap.put("/app/user/profile/", profileController);
        ActorsController actorsController = new ActorsController();
        controllerMap.put("/app/personal/admin/actors/", actorsController);
        SecretPageController secretPageController = new SecretPageController();
        controllerMap.put("/secretpage", secretPageController);
        super.init();
    }

    private void beforeProcess(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        beforeProcess(req, resp);
        BaseController controller = findMatchingController(req.getRequestURI());
        if (controller != null) {
            try {
                controller.handleGetRequest(req, resp);
            } catch (Exception e) {
                LOGGER.error("Failed to handle request: " + e.getMessage());
                ModelAndView modelAndView = new ModelAndView("/error.tiles");
                modelAndView.putObject("error", e.getMessage());
                modelAndView.process(req, resp);
            }
        } else {
            LOGGER.error("No controller to handle request: " + req.getRequestURI());
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(req, resp);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        beforeProcess(req, resp);
        BaseController controller = findMatchingController(req.getRequestURI());
        if (controller != null) {
            try {
                controller.handlePostRequest(req, resp);
            } catch (Exception e) {
                LOGGER.error("Failed to handle request: " + e.getMessage());
                ModelAndView modelAndView = new ModelAndView("/error.tiles");
                modelAndView.putObject("error", e.getMessage());
                modelAndView.process(req, resp);
            }
        } else {
            LOGGER.error("No controller to handle request: " + req.getRequestURI());
            ModelAndView modelAndView = new ModelAndView("/errorNotFound.tiles");
            modelAndView.process(req, resp);
        }
    }

}
