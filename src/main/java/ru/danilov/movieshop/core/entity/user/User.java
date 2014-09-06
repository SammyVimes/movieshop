package ru.danilov.movieshop.core.entity.user;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * Created by Semyon on 05.09.2014.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @NotNull
    @Column(name = "password_hash", unique = false, nullable = false)
    private String passwordHash;

    @NotNull
    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @NotNull
    public String getLogin() {
        return login;
    }

    public void setLogin(@NotNull final String login) {
        this.login = login;
    }

    @NotNull
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(@NotNull final String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @NotNull
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(@NotNull final UserRole userRole) {
        this.userRole = userRole;
    }

}
