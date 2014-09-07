package ru.danilov.movieshop.core.entity.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.danilov.movieshop.core.money.Currency;
import ru.danilov.movieshop.web.util.ServiceContainer;

import java.util.List;

/**
 * Created by Semyon on 06.09.2014.
 */
public class MovieManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieManager.class);

    private MovieDAO movieDAO = ServiceContainer.getService(MovieDAO.class);

    private void validate(final Movie movie) throws MovieManagerException {
        String title = movie.getTitle();
        if (title == null || title.isEmpty()) {
            throw new MovieManagerException("Название не задано");
        }
        String coverUri = movie.getCoverUri();
        if (coverUri == null || coverUri.isEmpty()) {
            throw new MovieManagerException("URL картинки не задан");
        }
        String description = movie.getDescription();
        if (description == null || description.isEmpty()) {
            throw new MovieManagerException("Описание не задано");
        }
        Double price = movie.getPrice();
        if (price == null || price < 0) {
            throw new MovieManagerException("Цена должна быть числом больше нуля");
        }
        Currency currency = movie.getCurrency();
        if (currency == null) {
            throw new MovieManagerException("Не задана валюта");
        }
    }

    public void createMovie(final Movie movie) throws MovieManagerException {
        validate(movie);
        try {
            movieDAO.persist(movie);
        } catch (Exception e) {
            LOGGER.error("Failed to save movie: " + e.getMessage());
            throw new MovieManagerException("Не удалось сохранить фильм", e);
        }
    }

    public Movie updateMovie(final Movie movie) throws MovieManagerException {
        validate(movie);
        try {
            return movieDAO.update(movie);
        } catch (Exception e) {
            LOGGER.error("Failed to update movie: " + e.getMessage());
            throw new MovieManagerException("Не удалось обновить фильм", e);
        }
    }

    public List<Movie> getMovies(final int quantity) {
        return movieDAO.getMovies(quantity);
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
