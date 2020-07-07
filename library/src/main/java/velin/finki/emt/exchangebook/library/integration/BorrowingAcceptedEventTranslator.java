package velin.finki.emt.exchangebook.library.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.core.infrastructure.eventlog.RemoteEventTranslator;
import velin.finki.emt.exchangebook.core.infrastructure.eventlog.StoredDomainEvent;

import java.util.Optional;

@Service
class BorrowingAcceptedEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    BorrowingAcceptedEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("mk.ukim.finki.emt.productordering.productcatalog.integration.OrderCreatedEvent");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, BorrowingAcceptedEvent.class));
    }
}
