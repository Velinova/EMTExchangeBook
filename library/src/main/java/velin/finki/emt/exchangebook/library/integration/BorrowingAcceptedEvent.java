package velin.finki.emt.exchangebook.library.integration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.library.domain.model.BookId;
import velin.finki.emt.exchangebook.library.domain.model.BorrowingId;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;

public class BorrowingAcceptedEvent implements DomainEvent {

    @JsonProperty("borrowing_id")
    private final BorrowingId borrowingId;
    @JsonProperty("borrowed_book_id")
    private final BookId borrowedBookId;
    @JsonProperty("lent_book_id")
    private final BookId lentBookId;
    @JsonProperty("occurred_on")
    private final Instant occurredOn;

    @JsonCreator
    public BorrowingAcceptedEvent(@JsonProperty("borrowing_id") @NonNull BorrowingId borrowingId,
                             @JsonProperty("borrowed_book_id") @NonNull BookId borrowedBookId,
                                  @JsonProperty("lent_book_id") @NonNull BookId lentBookId,
                                  @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
        this.borrowingId = Objects.requireNonNull(borrowingId, "borrowingId must not be null");
        this.borrowedBookId = borrowedBookId;
        this.lentBookId = lentBookId;
        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }

    @NonNull
    public BorrowingId getborrowingId() {
        return borrowingId;
    }
    @NotNull
    public BookId getBorrowedBookId(){
        return borrowedBookId;
    }
    @NotNull
    public BookId getLentBookId(){
        return lentBookId;
    }
    @Override
    @NonNull
    public Instant occurredOn() {
        return occurredOn;
    }
}

