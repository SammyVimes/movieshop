package ru.danilov.movieshop.web.controller;

/**
 * Created by Semyon on 16.09.2014.
 */
public class MissingParameterException extends Exception {

    public MissingParameterException() {
    }

    public MissingParameterException(final String message) {
        super(message);
    }

    public MissingParameterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MissingParameterException(final Throwable cause) {
        super(cause);
    }

    public MissingParameterException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
