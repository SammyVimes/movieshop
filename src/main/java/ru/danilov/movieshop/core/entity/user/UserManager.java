package ru.danilov.movieshop.core.entity.user;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Semyon on 05.09.2014.
 */
public class UserManager {

    @Inject
    private UserDAO userDAO;

    public UserManager() {
        int a = 0;
        a++;
    }

}
