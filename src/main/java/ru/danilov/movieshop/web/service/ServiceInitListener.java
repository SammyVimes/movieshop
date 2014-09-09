package ru.danilov.movieshop.web.service;

import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.comment.Comment;
import ru.danilov.movieshop.core.entity.comment.CommentDAO;
import ru.danilov.movieshop.core.entity.comment.CommentManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.movie.MovieDAO;
import ru.danilov.movieshop.core.entity.movie.MovieManager;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.entity.user.UserDAO;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.core.entity.user.UserSettingsDAO;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Date;

/**
 * Created by Semyon on 05.09.2014.
 */
public class ServiceInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        UserDAO userDAO = new UserDAO();
        MovieDAO movieDAO = new MovieDAO();
        CommentDAO commentDAO = new CommentDAO();
        ServiceContainer.putService(userDAO);
        ServiceContainer.putService(movieDAO);
        ServiceContainer.putService(commentDAO);
        AuthManager authManager = new AuthManager();
        ServiceContainer.putService(authManager);
        MovieManager movieManager = new MovieManager();
        ServiceContainer.putService(movieManager);
        UserSettingsDAO userSettingsDAO = new UserSettingsDAO();
        ServiceContainer.putService(userSettingsDAO);
        UserManager userManager = new UserManager();
        ServiceContainer.putService(userManager);
        CommentManager commentManager = new CommentManager();
        ServiceContainer.putService(commentManager);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {

    }

}
