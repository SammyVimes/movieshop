package ru.danilov.movieshop.core.entity.movie;

/**
 * Created by Semyon on 15.09.2014.
 */
public enum MovieGenre {

    ACTION("s_action", "Боевик", "fa-bomb"),
    ADVENTURE("s_adventure", "Приключения", "fa-plane"),
    COMEDY("s_comedy", "Комедия", "fa-smile-o"),
    ROMANTIC("s_romantic", "Романтика", "fa-heart"),
    DRAMA("s_drama", "Драма", "fa-book"),
    HORROR("s_horror", "Ужасы", "fa-bug"),
    CARTOON("s_cartoon", "Мультфильм", "fa-cube");

    private String prop;

    private String title;

    private String cssClass;

    private MovieGenre(final String prop, final String title, final String cssClass) {
        this.prop = prop;
        this.title = title;
        this.cssClass = cssClass;
    }

    public String getProp() {
        return prop;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name();
    }

    public String getCssClass() {
        return cssClass;
    }
}
