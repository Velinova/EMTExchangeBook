package velin.finki.emt.exchangebook.library.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.helger.commons.annotation.Nonempty;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.library.domain.model.BookId;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

public class BookDeletedEvent implements DomainEvent {
    @JsonProperty("book_id")
    private final BookId id;

    @JsonProperty("added_on")
    private final Instant occuredOn;

    @JsonCreator
    public BookDeletedEvent(@JsonProperty("book_id") @Nonempty BookId id,
                       @JsonProperty("added_on") @NonNull Instant occuredOn ) {
        this.id = Objects.requireNonNull(id, "bookId must not be null");
        this.occuredOn = Objects.requireNonNull(occuredOn, "occuredOn must not be null");
    }

    @NotNull
    public BookId getId(){ return id; }

    @Override
    public Instant occurredOn() {
        return occuredOn;
    }
}
