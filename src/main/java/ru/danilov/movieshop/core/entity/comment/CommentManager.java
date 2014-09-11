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
        try {
            commentDAO.persist(comment);
        } catch (Exception e) {
            LOGGER.trace("Failed to persist comment: " + e.getMessage());
        }
    }

    public List<Comment> getCommentsForMovie(final Movie movie) {
        try {
            return commentDAO.getAllCommentsForMovie(movie);
        } catch (Exception e) {
            LOGGER.trace("Failed to get comments for movie: " + e.getMessage());
        }
        return null;
    }

    public List<Comment> getCommentsOfUser(final User user) {
        try {
            return commentDAO.getAllCommentsOfUser(user);
        } catch (Exception e) {
            LOGGER.trace("Failed to get comments of user: " + e.getMessage());
        }
        return null;
    }

}
