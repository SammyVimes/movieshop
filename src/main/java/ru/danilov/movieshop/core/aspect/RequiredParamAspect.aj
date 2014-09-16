package ru.danilov.movieshop.core.aspect;

import ru.danilov.movieshop.web.controller.MissingParameterException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Semyon on 16.09.2014.
 */
public aspect RequiredParamAspect {

    pointcut publicMethod(): execution(public * *(..));

    before(RequiredParams requiredParams)throws MissingParameterException: publicMethod() && @annotation(requiredParams) {
        Object[] paramValues = thisJoinPoint.getArgs();
        HttpServletRequest request = (HttpServletRequest) paramValues[0];
        Class<?> callerClass = thisJoinPoint.getTarget().getClass();
        boolean[] canBeEmpty = requiredParams.canBeEmpty();
        int i = 0;
        for (String paramName : requiredParams.value()) {
            String[] values = request.getParameterValues(paramName);
            if (values != null) {
                for (String value : values) {
                    if (canBeEmpty.length > i) {
                        if (!canBeEmpty[i] && value.isEmpty()) {
                            throw new MissingParameterException("Required parameter '" + paramName + "' is empty");
                        }
                    }
                }
            } else {
                throw new MissingParameterException("Required parameter '" + paramName + "' is missing from request");
            }
            i++;
        }
    }

}
