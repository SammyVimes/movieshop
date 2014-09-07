package ru.danilov.movieshop.core.entity.movie;

/**
 * Created by Semyon on 07.09.2014.
 */
public class MovieManagerException extends Exception {

    public MovieManagerException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MovieManagerException(final Throwable cause) {
        super(cause);
    }

    public MovieManagerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MovieManagerException(final String message) {
        super(message);
    }

}
