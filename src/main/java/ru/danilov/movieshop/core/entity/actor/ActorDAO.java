package ru.danilov.movieshop.core.entity.actor;

import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Semyon on 13.09.2014.
 */
public class ActorDAO {

    @PersistenceContext
    private EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    public void persist(final Actor actor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(actor);
        transaction.commit();
    }

    public Actor update(final Actor actor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Actor _actor = entityManager.merge(actor);
        transaction.commit();
        return _actor;
    }

    public List<Actor> getAllActorsOfMovie(final Movie movie) {
        return entityManager.createQuery("select a from Actor a join a.movies as movie where movie.id = :movieId", Actor.class).setParameter("movieId", movie.getId()).getResultList();
    }

    public List<Actor> getAllActors() {
        return entityManager.createQuery("select a from Actor a", Actor.class).getResultList();
    }

    public Actor getById(final long id) {
        List<Actor> list = entityManager.createQuery("select a from Actor a WHERE id = :id", Actor.class).setParameter("id", id).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
