package ru.danilov.movieshop.core.entity.user;

import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Semyon on 05.09.2014.
 */
public class UserManager {

    private UserDAO userDAO = ServiceContainer.getService(UserDAO.class);

    private UserSettingsDAO userSettingsDAO = ServiceContainer.getService(UserSettingsDAO.class);

    public void createUser(final User user) {

    }

    public User getUserByLogin(final String login) {
        return userDAO.getByLogin(login);
    }

    public void createSettings(final UserSettings userSettings) {
        userSettingsDAO.persist(userSettings);
    }

    public UserSettings update(final UserSettings userSettings) {
        return userSettingsDAO.update(userSettings);
    }

    public UserSettings getUserSettings(final User user) {
        return userSettingsDAO.getUserSettings(user);
    }

}
