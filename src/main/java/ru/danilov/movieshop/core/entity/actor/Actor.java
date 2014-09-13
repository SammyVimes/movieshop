package ru.danilov.movieshop.core.entity.actor;

import ru.danilov.movieshop.core.entity.movie.Movie;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Semyon on 13.09.2014.
 */
@Table(name = "actor")
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    private List<Movie> movies;

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

}
