package ru.danilov.movieshop.web.service;

import ru.danilov.movieshop.core.entity.user.UserDAO;
import ru.danilov.movieshop.core.entity.user.UserManager;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Semyon on 05.09.2014.
 */
public class ServiceInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {
        UserDAO userDAO = new UserDAO();
        ServiceContainer.putService(userDAO);
        UserManager userManager = new UserManager();
        ServiceContainer.putService(userManager);
    }

    @Override
    public void contextDestroyed(final ServletContextEvent servletContextEvent) {

    }

}
