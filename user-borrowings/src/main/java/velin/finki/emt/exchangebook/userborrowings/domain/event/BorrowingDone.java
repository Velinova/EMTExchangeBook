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

public class BorrowingDone implements DomainEvent {
    @JsonProperty("borrowing_id")
    private final BorrowingId borrowingId;
    @JsonProperty("done_on")
    private final Instant occuredOn;
    @JsonProperty("borrowed_book_id")
    private final BookId borrowedBookId;
    @JsonProperty("lent_book_id")
    private final BookId lentBookId;

    @JsonCreator
    public BorrowingDone(@JsonProperty("borrowing_id") @NonNull BorrowingId borrowingId,
                             @JsonProperty("done_on") @NonNull Instant occuredOn,
                             @JsonProperty("borrowed_book_id") @NotNull BookId borrowedBookId,
                             @JsonProperty("lent_book_id") @NotNull BookId lentBookId) {
        this.borrowingId = Objects.requireNonNull(borrowingId, "borrowingId must not be null");
        this.occuredOn = Objects.requireNonNull(occuredOn, "doneOn must not be null");
        this.borrowedBookId = Objects.requireNonNull(borrowedBookId, "borrowed book must not be null");
        this.lentBookId = Objects.requireNonNull(lentBookId, "lent book must not be null");
    }


    @NonNull
    public BorrowingId getBorrowingId() {
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
    public Instant occurredOn() {
        return occuredOn;
    }
}
