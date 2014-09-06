package ru.danilov.movieshop.core.entity.movie;

import ru.danilov.movieshop.web.util.ServiceContainer;

import java.util.List;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MovieManager {

    private MovieDAO movieDAO = ServiceContainer.getService(MovieDAO.class);

    private void createMovie(final Movie movie) {

    }

    private List<Movie> search(final String query) {
        return null;
    }

    private List<Movie> getAllMovies() {
        return null;
    }

    private Movie getMovieById() {
        return null;
    }

    private void removeMovie(final Movie movie) {

    }

}
