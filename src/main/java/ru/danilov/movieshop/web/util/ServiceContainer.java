package ru.danilov.movieshop.web.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Semyon on 05.09.2014.
 */
public class ServiceContainer {

    private static Map<Class, Object> serviceMap = new HashMap<>();

    public static <T> void putService(final T object) {
        serviceMap.put(object.getClass(), object);
    }

    public static  <T> T getService(final Class<T> serviceClass) {
        return (T) serviceMap.get(serviceClass);
    }

}
