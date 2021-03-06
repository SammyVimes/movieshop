package ru.danilov.movieshop.web.filter;

import ru.danilov.movieshop.core.auth.AuthData;
import ru.danilov.movieshop.core.auth.AuthManager;
import ru.danilov.movieshop.core.entity.user.UserRole;
import ru.danilov.movieshop.web.util.AttributeNames;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Semyon on 05.09.2014.
 */
public class AccessFilter extends BaseFilter {

    private UserRole requiredRole;

    private AuthManager authManager = ServiceContainer.getService(AuthManager.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        requiredRole = UserRole.valueOf(filterConfig.getInitParameter("requiredRole"));
    }

    @Override
    public void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        String key = (String) request.getSession().getAttribute(AttributeNames.AUTH_DATA_KEY);
        if (key == null) {
            response.sendRedirect("/movieshop/web/auth");
        } else {
            AuthData authData = authManager.getAuthData(key);
            if (authData == null || authData.isExpired()) {
                response.sendRedirect("/movieshop/web/auth");
            } else {
                if (authData.getUser().getUserRole() != requiredRole) {
                    response.sendRedirect("/movieshop/web/accessDenied");
                }
                filterChain.doFilter(request, response);
            }
        }
    }

    public UserRole getRequiredRole() {
        return requiredRole;
    }

    public void setRequiredRole(final UserRole requiredRole) {
        this.requiredRole = requiredRole;
    }

    @Override
    public void destroy() {

    }

}
