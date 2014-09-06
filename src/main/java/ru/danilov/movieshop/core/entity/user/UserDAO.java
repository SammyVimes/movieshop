package ru.danilov.movieshop.core.entity.user;


import ru.danilov.movieshop.core.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by Semyon on 05.09.2014.
 */
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    public void persist(final User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    public User getByLogin(final String login) {
        List<User> list = entityManager.createQuery("SELECT a FROM User a WHERE  login = :login", User.class)
                .setParameter("login", login).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

}
