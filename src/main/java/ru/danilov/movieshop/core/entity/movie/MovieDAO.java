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

    public List<Movie> search(final String query) {
        return entityManager.createQuery("SELECT a FROM Movie a WHERE title LIKE :query", Movie.class)
                .setParameter("query", query).getResultList();
    }

    public Movie getById(final Long id) {
        List<Movie> list = entityManager.createQuery("SELECT a FROM Movie a WHERE id = :id", Movie.class)
                .setParameter("id", id).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Movie> getAllMovies() {
        return entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
    }

    public List<Movie> getPopularMovies() {
        return entityManager.createQuery("SELECT a FROM Movie a WHERE isPopular IS TRUE", Movie.class).getResultList();
    }
}
