package ru.danilov.movieshop.core.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Semyon on 13.09.2014.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpLoggable {

    String[] variablesToLog();

}
