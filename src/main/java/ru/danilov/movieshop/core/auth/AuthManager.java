package ru.danilov.movieshop.core.auth;

import ru.danilov.movieshop.core.util.Cache;

/**
 * Created by Semyon on 06.09.2014.
 */
public class AuthManager {

    private Cache<String, AuthData> authSessions = null;

    private final long TTL = 600000 * 4; //40 минут
    private final long TTC = 600000;

    public AuthManager() {
        authSessions = new Cache<>(TTL, TTC);
    }

    public void putAuthData(final AuthData authData) {
        authSessions.put(authData.getKey(), authData);
    }

    public AuthData getAuthData(final String key) {
        return authSessions.get(key);
    }

}
