package ru.danilov.movieshop.core.money;

/**
 * Created by Semyon on 06.09.2014.
 */
public enum Currency {

    US_DOLLARS("Доллар", "$"),
    RUS_RUBLES("Рубль", "руб.");

    private String title;

    private String shortTitle;

    private Currency(final String title, final String shortTitle) {
        this.title = title;
        this.shortTitle = shortTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        return shortTitle;
    }
}
