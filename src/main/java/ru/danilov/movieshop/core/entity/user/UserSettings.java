package ru.danilov.movieshop.core.entity.user;

import org.jetbrains.annotations.NotNull;
import ru.danilov.movieshop.core.entity.movie.Movie;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Semyon on 05.09.2014.
 */
@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "money", nullable = false)
    private Double money;

    @ManyToMany
    private List<Movie> movies;

    @NotNull
    @OneToOne(optional = false, orphanRemoval = true)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(final Double money) {
        this.money = money;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }

}
