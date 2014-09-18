package ru.danilov.movieshop.core.entity.comment;

import org.jetbrains.annotations.NotNull;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.core.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Semyon on 10.09.2014.
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne(optional = true)
    private User user;

    @ManyToOne(optional = false)
    private Movie movie;

    @NotNull
    @Column(name = "date", nullable = false)
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(final Movie movie) {
        this.movie = movie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

}
