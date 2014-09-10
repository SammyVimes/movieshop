package ru.danilov.movieshop.core.entity.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.web.util.ServiceContainer;

import java.util.List;

/**
 * Created by Semyon on 10.09.2014.
 */
public class CommentManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentManager.class);

    private CommentDAO commentDAO = ServiceContainer.getService(CommentDAO.class);

    public void addComment(final Comment comment) {
        commentDAO.persist(comment);
    }

    public List<Comment> getCommentsForMovie(final Movie movie) {
        return commentDAO.getAllCommentsForMovie(movie);
    }

    public List<Comment> getCommentsOfUser(final User user) {
        return commentDAO.getAllCommentsOfUser(user);
    }

}
