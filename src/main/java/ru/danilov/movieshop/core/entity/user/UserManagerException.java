package ru.danilov.movieshop.core.entity.user;

/**
 * Created by Semyon on 08.09.2014.
 */
public class UserManagerException extends Exception {

    public UserManagerException(final String message) {
        super(message);
    }

    public UserManagerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UserManagerException(final Throwable cause) {
        super(cause);
    }

    public UserManagerException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
