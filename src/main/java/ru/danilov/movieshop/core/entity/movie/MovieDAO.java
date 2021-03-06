package ru.danilov.movieshop.core.entity.movie;

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

    public Movie update(final Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Movie _movie = entityManager.merge(movie);
        transaction.commit();
        return _movie;
    }

    public void remove(final Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if (!entityManager.contains(movie)) {
            entityManager.remove(update(movie));
        } else {
            entityManager.remove(movie);
        }
        transaction.commit();
    }

    public List<Movie> search(final String query) {
        return entityManager.createQuery("SELECT a FROM Movie a WHERE title LIKE '%" + query + "%'", Movie.class).getResultList();
    }

    public Movie getById(final Long id) {
        List<Movie> list = entityManager.createQuery("SELECT a FROM Movie a WHERE id = :id", Movie.class)
                .setParameter("id", id).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Movie> getByGenre(final MovieGenre genre) {
        return entityManager.createQuery("SELECT a FROM Movie a WHERE genre = :genre", Movie.class)
                .setParameter("genre", genre).getResultList();
    }

    public List<Movie> getAllMovies() {
        return entityManager.createQuery("SELECT a FROM Movie a", Movie.class).getResultList();
    }

    public List<Movie> getPopularMovies() {
        return entityManager.createQuery("SELECT a FROM Movie a WHERE isPopular IS TRUE", Movie.class).getResultList();
    }

    public List<Movie> getMovies(final int quantity) {
        return entityManager.createQuery("SELECT a FROM Movie a", Movie.class).setMaxResults(quantity).getResultList();
    }

}
