package velin.finki.emt.exchangebook.library.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.helger.commons.annotation.Nonempty;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.library.application.viewmodels.FullNameViewModel;
import velin.finki.emt.exchangebook.library.application.viewmodels.GenreViewModel;
import velin.finki.emt.exchangebook.library.domain.model.UserId;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

public class BookAddedEvent implements DomainEvent {

    @JsonProperty("title")
    private final String title;

    @JsonProperty("plot")
    private final String plot;

    @JsonProperty("genre")
    private final GenreViewModel genre;

    @JsonProperty("author")
    private final FullNameViewModel author;

    @JsonProperty("user_id")
    private final UserId userId;

    @JsonProperty("added_on")
    private final Instant occuredOn;

    @JsonCreator
    public BookAddedEvent(@JsonProperty("title") @Nonempty String title,
                     @JsonProperty("plot") @Nonempty String plot,
                     @JsonProperty("author") @NonNull FullNameViewModel author,
                     @JsonProperty("genre") @NonNull GenreViewModel genre,
                     @JsonProperty("user_id") @NonNull UserId userId,
                     @JsonProperty("added_on") @NonNull Instant occuredOn ) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.plot = Objects.requireNonNull(plot, "plot must not be null");
        this.genre = Objects.requireNonNull(genre, "genre must not be null");
        this.author = Objects.requireNonNull(author, "author must not be null");
        this.userId = Objects.requireNonNull(userId, "userId must not be null");
        this.occuredOn = Objects.requireNonNull(occuredOn, "occuredOn must not be null");
    }


    @NonNull
    public String getTitle() {
        return title;
    }

    @NotNull
    public String getPlot(){return plot;}

    @NotNull
    public FullNameViewModel getAuthor(){ return author; }

    @NotNull
    public GenreViewModel getGenre(){return genre;}

    @NotNull
    public UserId getUserId(){return userId;}

    @Override
    public Instant occurredOn() {
        return occuredOn;
    }
}

