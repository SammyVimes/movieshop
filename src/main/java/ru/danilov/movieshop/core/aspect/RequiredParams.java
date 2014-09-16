package ru.danilov.movieshop.core.aspect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Semyon on 16.09.2014.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredParams {

    public String[] value();

    public boolean[] canBeEmpty();

}
