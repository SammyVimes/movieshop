package ru.danilov.movieshop.core.entity.actor;

import org.jetbrains.annotations.NotNull;
import ru.danilov.movieshop.core.entity.movie.Movie;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Semyon on 13.09.2014.
 */
@Table(name = "actor")
@Entity
public class Actor {

    //уникальный идентификатор
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //имя
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    private List<Movie> movies;

    //пол
    @NotNull
    @Column(name = "sex", nullable = false)
    private boolean sex;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(final List<Movie> movies) {
        this.movies = movies;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(final boolean sex) {
        this.sex = sex;
    }
}
