package velin.finki.emt.exchangebook.userborrowings.domain.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BookId;
import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

public class BorrowingCreated implements DomainEvent {

    @JsonProperty("borrowing_id")
    private final BorrowingId borrowingId;
    @JsonProperty("made_on")
    private final Instant occuredOn;

    @JsonCreator
    public BorrowingCreated(@JsonProperty("borrowing_id") @NonNull BorrowingId borrowingId,
                            @JsonProperty("made_on") @NonNull Instant occuredOn, BookId borrowedBook) {
        this.borrowingId = Objects.requireNonNull(borrowingId, "borrowingId must not be null");
        this.occuredOn = Objects.requireNonNull(occuredOn, "madeOn must not be null");
    }


    @NonNull
    public BorrowingId getBorrowingId() {
        return borrowingId;
    }


    @Override
    public Instant occurredOn() {
        return occuredOn;
    }
}

