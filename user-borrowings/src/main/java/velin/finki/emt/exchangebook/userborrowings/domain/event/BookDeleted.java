package velin.finki.emt.exchangebook.userborrowings.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.helger.commons.annotation.Nonempty;
import lombok.NonNull;
import org.aspectj.weaver.GeneratedReferenceTypeDelegate;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.core.enums.Genre;
import velin.finki.emt.exchangebook.core.valueobjects.FullName;
import velin.finki.emt.exchangebook.userborrowings.application.viewmodels.FullNameViewModel;
import velin.finki.emt.exchangebook.userborrowings.application.viewmodels.GenreViewModel;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.UserId;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

public class BookDeleted implements DomainEvent {

    @JsonProperty("book_id")
    private final BookId id;

    @JsonProperty("added_on")
    private final Instant occuredOn;

    @JsonCreator
    public BookDeleted(@JsonProperty("book_id") @Nonempty BookId id,
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

