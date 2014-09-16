package ru.danilov.movieshop.web.admin;

import ru.danilov.movieshop.core.entity.actor.Actor;
import ru.danilov.movieshop.core.entity.actor.ActorManager;
import ru.danilov.movieshop.core.entity.movie.Movie;
import ru.danilov.movieshop.web.base.ModelAndView;
import ru.danilov.movieshop.web.controller.BaseController;
import ru.danilov.movieshop.web.util.ServiceContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Semyon on 14.09.2014.
 */
public class ActorsController extends BaseController {

    private ActorManager actorManager = ServiceContainer.getService(ActorManager.class);

    @Override
    public void handleGetRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        addActorView(request, response);
    }

    @Override
    public void handlePostRequest(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        addActorPost(request, response);
    }

    private void addActorView(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("/admin.addActor.tiles");
        modelAndView.process(request, response);
    }

    private void addActorPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String isMale = request.getParameter("sex");
        Actor actor = new Actor();
        actor.setMovies(new LinkedList<Movie>());
        if (isMale != null && isMale.equals("on")) {
            actor.setSex(true);
        } else {
            actor.setSex(false);
        }
        actor.setName(name);
        try {
            actorManager.createActor(actor);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("/admin.addActor.tiles");
            modelAndView.putObject("error", "Ошибка: " + e.getMessage());
            modelAndView.process(request, response);
        }
        response.sendRedirect("/movieshop/web/app/personal/admin/movies");
    }

}
