package ru.danilov.movieshop.core.entity.movie;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.danilov.movieshop.core.money.Currency;

import javax.persistence.*;

/**
 * Created by Semyon on 06.09.2014.
 */
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "cover_uri", nullable = false)
    private String coverUri;

    @NotNull
    @Column(name = "title")
    private String title;

    @Nullable
    @Column(name = "localized_title", nullable = true)
    private String localizedTitle;

    @NotNull
    @Column(name = "price", nullable = false)
    private int price;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull
    @Column(name = "is_popular", nullable = false)
    private boolean isPopular;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull final String title) {
        this.title = title;
    }

    @Nullable
    public String getLocalizedTitle() {
        return localizedTitle;
    }

    public void setLocalizedTitle(@Nullable final String localizedTitle) {
        this.localizedTitle = localizedTitle;
    }

    @NotNull
    public int getPrice() {
        return price;
    }

    public void setPrice(@NotNull final int price) {
        this.price = price;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull final String description) {
        this.description = description;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    @NotNull
    public boolean isPopular() {
        return isPopular;
    }

    public void setPopular(@NotNull final boolean isPopular) {
        this.isPopular = isPopular;
    }

    @NotNull
    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(@NotNull final String coverUri) {
        this.coverUri = coverUri;
    }
}