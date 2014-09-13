package ru.danilov.movieshop.core.entity.actor;

import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.web.util.ServiceContainer;

import java.util.List;

/**
 * Created by Semyon on 13.09.2014.
 */
public class ActorManager {

    private ActorDAO actorDAO = ServiceContainer.getService(ActorDAO.class);

    public void createActor(final Actor actor) {
        actorDAO.persist(actor);
    }

    public void updateActor(final Actor actor) {
        actorDAO.update(actor);
    }

    public List<Actor> getAllActors() {
        return actorDAO.getAllActors();
    }

    public List<Actor> getAllActorsOfMovie(final Movie movie) {
        return actorDAO.getAllActorsOfMovie(movie);
    }

    public Actor getActorById(final long id) {
        return actorDAO.getById(id);
    }

}
