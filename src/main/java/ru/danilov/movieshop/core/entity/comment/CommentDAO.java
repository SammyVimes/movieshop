package ru.danilov.movieshop.core.entity.comment;

import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.core.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Semyon on 10.09.2014.
 */
public class CommentDAO {

    @PersistenceContext
    private EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    public void persist(final Comment comment) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(comment);
        transaction.commit();
    }

    public Comment update(final Comment comment) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Comment _comment = entityManager.merge(comment);
        transaction.commit();
        return _comment;
    }

    public List<Comment> getAllCommentsOfUser(final User user) {
        return entityManager.createQuery("SELECT a FROM Comment a WHERE user = :user", Comment.class).setParameter("user", user).getResultList();
    }

    public List<Comment> getAllCommentsForMovie(final Movie movie) {
        return entityManager.createQuery("SELECT a FROM Comment a WHERE movie = :movie", Comment.class).setParameter("movie", movie).getResultList();
    }

}
