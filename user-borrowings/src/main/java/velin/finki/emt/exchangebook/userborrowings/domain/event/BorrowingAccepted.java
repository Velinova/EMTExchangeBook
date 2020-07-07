//package velin.finki.emt.exchangebook.userborrowings.domain.event;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import org.springframework.lang.NonNull;
//import velin.finki.emt.exchangebook.core.base.DomainEvent;
//import velin.finki.emt.exchangebook.userborrowings.domain.model.Borrowing;
//import velin.finki.emt.exchangebook.userborrowings.domain.model.BorrowingId;
//
//import java.time.Instant;
//import java.util.Objects;
//
//public class BorrowingAccepted implements DomainEvent {
//
//    @JsonProperty("orderId")
//    private final BorrowingId borrowingId;
//    @JsonProperty("occurredOn")
//    private final Instant occurredOn;
//
//    @JsonCreator
//    public OrderCreated(@JsonProperty("orderId") @NonNull OrderId orderId,
//                        @JsonProperty("occurredOn") @NonNull Instant occurredOn) {
//        this.orderId = Objects.requireNonNull(orderId, "orderId must not be null");
//        this.occurredOn = Objects.requireNonNull(occurredOn, "occurredOn must not be null");
//    }
//
//    @NonNull
//    public OrderId orderId() {
//        return orderId;
//    }
//
//    @Override
//    @NonNull
//    public Instant occurredOn() {
//        return occurredOn;
//    }
//}
