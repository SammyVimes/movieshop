package ru.danilov.movieshop.core.entity.movie;

import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MovieDAO {

    @PersistenceContext
    private EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    public void persist(final Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(movie);
        transaction.commit();
    }

    public Movie search(final String query) {
        List<Movie> list = entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Movie> getAllMovies() {
        return entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
    }

}
