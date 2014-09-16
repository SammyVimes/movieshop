package ru.danilov.movieshop.core.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.util.Cache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Semyon on 13.09.2014.
 */
public aspect HttpLogAspect {

    private Cache<Class, Logger> loggers = new Cache<>(60000000, 60000000);

    pointcut publicMethod(): execution(public * *(..));

    before(HttpLoggable httpLoggable): publicMethod() && @annotation(httpLoggable) {
        Object[] paramValues = thisJoinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) paramValues[0];
        Class<?> callerClass = thisJoinPoint.getTarget().getClass();

        Logger LOGGER = loggers.get(callerClass);
        if (LOGGER == null) {
            LOGGER = LoggerFactory.getLogger(callerClass);
            loggers.put(callerClass, LOGGER);
        }
        StringBuilder trace = new StringBuilder();
        for (String variableToLog : httpLoggable.variablesToLog()) {
            String[] values = request.getParameterValues(variableToLog);
            if (values != null) {
                trace.append(variableToLog).append(" = [");
                for (String value : values) {
                    trace.append(value).append(", ");
                }
                trace.append("]; ");
            }
        }
        LOGGER.trace(trace.toString());
    }

    after(HttpLoggable httpLoggable): publicMethod() && @annotation(httpLoggable) {
        Object[] paramValues = thisJoinPoint.getArgs();
        HttpServletResponse response = (HttpServletResponse) paramValues[1];
        Class<?> callerClass = thisJoinPoint.getTarget().getClass();
        Logger LOGGER = loggers.get(callerClass);
        if (LOGGER == null) {
            LOGGER = LoggerFactory.getLogger(callerClass);
            loggers.put(callerClass, LOGGER);
        }
        StringBuilder trace = new StringBuilder();
        trace.append("Status code is ").append(response.getStatus());
        LOGGER.trace(trace.toString());
    }

}
