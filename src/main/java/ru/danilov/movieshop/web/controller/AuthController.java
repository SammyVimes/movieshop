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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Semyon on 05.09.2014.
 */
public class AuthController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private UserManager userManager = ServiceContainer.getService(UserManager.class);

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String url = request.getRequestURI();
        if (url.contains("doAuth")) {
            auth(request, response);
        } else {
            mainView(request, response);
        }
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

    }

    public void mainView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/web/common/auth.jsp").forward(request, response);
    }

    @HttpLoggable(variablesToLog = {"login", "password"})
    public void auth(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userManager.getUserByLogin(login);
        if (user != null) {
            String pass = Util.getMD5Hash(password);
            if (pass.equals(user.getPasswordHash())) {
                String key = Util.generateRandomString();
                Date date = new Date();
                Date expiration = new Date(date.getTime() + 2400000);
                AuthData authData = new AuthData(key, date, expiration, user);
                HttpSession session = request.getSession();
                authManager.putAuthData(authData);
                session.setAttribute(AttributeNames.AUTH_DATA_KEY, authData.getKey());
                Cookie cookie = new Cookie(AttributeNames.AUTH_DATA_KEY, authData.getKey());
                cookie.setMaxAge(600000 * 4);
                response.addCookie(cookie);
                LOGGER.trace("Successfully authorized user: " + login);
                if (user.getUserRole() == UserRole.ADMIN) {
                    response.sendRedirect("/movieshop/web/app/personal/admin/movies");
                } else {
                    response.sendRedirect("/movieshop/web/app/main");
                }
            } else {
                LOGGER.error("Password didnt match");
                ModelAndView modelAndView = new ModelAndView("/auth.tiles");
                modelAndView.putObject("error", "Неверный пароль");
                modelAndView.process(request, response);
            }
        } else {
            LOGGER.error("There is no such user: " + login);
            ModelAndView modelAndView = new ModelAndView("/auth.tiles");
            modelAndView.putObject("error", "Пользователь с таким ником не найден");
            modelAndView.process(request, response);
        }
    }

}
