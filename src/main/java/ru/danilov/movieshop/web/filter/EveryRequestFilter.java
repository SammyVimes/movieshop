package ru.danilov.movieshop.web.filter;

import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Semyon on 07.09.2014.
 */
public class EveryRequestFilter extends BaseFilter {

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        if (key != null) {
            AuthData authData = authManager.getAuthData(key);
            if (authData != null && !authData.isExpired()) {
                request.setAttribute("user", authData.getUser());
            }
        }
        String lang = (String) request.getSession().getAttribute("lang");
        if (lang == null || lang.isEmpty()) {
            lang = "ru";
        }
        request.setAttribute("locale", new Locale(lang));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}