package ru.danilov.movieshop.core.entity.user;

import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.web.util.ServiceContainer;

import java.util.List;

/**
 * Created by Semyon on 05.09.2014.
 */
public class UserManager {

    private UserDAO userDAO = ServiceContainer.getService(UserDAO.class);

    private UserSettingsDAO userSettingsDAO = ServiceContainer.getService(UserSettingsDAO.class);

    private void validate(final User user) throws UserManagerException {
        String login = user.getLogin();
        if (login == null || login.isEmpty()) {
            throw new UserManagerException("Логин не задан");
        }
        User existed = userDAO.getByLogin(login);
        if (existed != null) {
            throw new UserManagerException("Пользователь с таким ником уже существует");
        }
        String hashedString = user.getPasswordHash();
        if (hashedString == null || hashedString.isEmpty()) {
            throw new UserManagerException("Пароль не задан");
        }
        UserRole userRole = user.getUserRole();
        if (userRole == null) {
            throw new UserManagerException("Роль не задана");
        }
    }

    public void createUser(final User user) throws UserManagerException {
        validate(user);
        userDAO.persist(user);
    }

    public void updateUser(final User user) throws UserManagerException {
        userDAO.update(user);
    }

    public User getUserById(final Long id) {
        return userDAO.getById(id);
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

    public List<UserSettings> getUserSettingsForMovie(final Movie movie) {
        return userSettingsDAO.getSettingsForMovie(movie);
    }

    public void removeSettings(final UserSettings userSettings) {
        userSettingsDAO.remove(userSettings);
    }

}
