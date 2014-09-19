package ru.danilov.movieshop.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.aspect.HttpLoggable;
import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.core.entity.user.UserRole;
import ru.danilov.movieshop.core.util.Util;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Semyon on 08.09.2014.
 */
public class RegisterController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        registerView(request, response);
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        register(request, response);
    }

    public void registerView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/register.tiles");
        modelAndView.process(request, response);
    }

    @HttpLoggable(variablesToLog = {"login", "password", "repeat-password"})
    public void register(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");
        User user = new User();
        user.setLogin(login);
        if (!password.equals(repeatPassword)) {
            ModelAndView modelAndView = new ModelAndView("/register.tiles");
            modelAndView.putObject("error", "Пароли не совпадают");
            modelAndView.process(request, response);
            return;
        }
        String hashedPassword = Util.getMD5Hash(password);
        user.setPasswordHash(hashedPassword);
        user.setUserRole(UserRole.USER);
        try {
            userManager.createUser(user);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("/register.tiles");
            modelAndView.putObject("error", e.getMessage());
            modelAndView.process(request, response);
            return;
        }
        String key = Util.generateRandomString();
        Date date = new Date();
        Date expiration = new Date(date.getTime() + 2400000);
        AuthData authData = new AuthData(key, date, expiration, user);
        HttpSession session = request.getSession();
        authManager.putAuthData(authData);
        session.setAttribute(AttributeNames.AUTH_DATA_KEY, authData.getKey());
        LOGGER.trace("New user was created: " + user.getLogin());
        response.sendRedirect("/movieshop/web/app/catalog");
    }

}
