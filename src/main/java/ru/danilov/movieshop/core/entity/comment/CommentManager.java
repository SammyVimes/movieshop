package ru.danilov.movieshop.core.entity.comment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.user.User;
import ru.danilov.movieshop.web.util.ServiceContainer;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    private Comparator<Comment> comparator = new Comparator<Comment>() {
        @Override
        public int compare(final Comment o1, final Comment o2) {
            Date firstDate = o1.getDate();
            Date secondDate = o2.getDate();
            if (firstDate.after(secondDate)) {
                return -1;
            } else {
                return 1;
            }
        }
    };

    public List<Comment> getCommentsForMovie(final Movie movie) {
        try {
            List<Comment> comments = commentDAO.getAllCommentsForMovie(movie);
            Collections.sort(comments, comparator);
            return comments;
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

    public void remove(final Comment comment) {
        commentDAO.remove(comment);
    }

}
