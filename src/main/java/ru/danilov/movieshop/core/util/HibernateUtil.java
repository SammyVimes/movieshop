package ru.danilov.movieshop.core.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Semyon on 05.09.2014.
 */
public class HibernateUtil {

    private static EntityManagerFactory entityManagerFactory = null;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
