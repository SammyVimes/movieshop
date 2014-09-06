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

    public List<Movie> search(final String query) {
        return movieDAO.search(query);
    }

    public List<Movie> getAllMovies() {
        return null;
    }

    public Movie getMovieById(final Long movieId) {
        return movieDAO.getById(movieId);
    }

    private void removeMovie(final Movie movie) {

    }

    public List<Movie> getPopularMovies() {
        return movieDAO.getPopularMovies();
    }

}
