package ru.danilov.movieshop.core.auth;

import ru.danilov.movieshop.core.entity.user.User;

import java.util.Date;

/**
 * Created by Semyon on 06.09.2014.
 */
public class AuthData {

    private String key;

    private Date creationTimestamp;

    private Date expirationTimestamp;

    private User user;

    public AuthData(final String key, final Date creationTimestamp, final Date expirationTimestamp, final User user) {
        this.key = key;
        this.creationTimestamp = creationTimestamp;
        this.expirationTimestamp = expirationTimestamp;
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(final Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Date getExpirationTimestamp() {
        return expirationTimestamp;
    }

    public void setExpirationTimestamp(final Date expirationTimestamp) {
        this.expirationTimestamp = expirationTimestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public boolean isExpired() {
        return (new Date()).after(expirationTimestamp);
    }

}
