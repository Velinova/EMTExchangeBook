package velin.finki.emt.exchangebook.library.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import velin.finki.emt.exchangebook.core.base.DomainEvent;
import velin.finki.emt.exchangebook.core.infrastructure.eventlog.RemoteEventTranslator;
import velin.finki.emt.exchangebook.core.infrastructure.eventlog.StoredDomainEvent;

import java.util.Optional;

@Service
class BorrowingDoneEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    BorrowingDoneEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent remoteEvent) {
        return remoteEvent.domainEventClassName().equals("velin.finki.emt.exchangebook.library.integration.BorrowingDoneEvent");
    }

    @Override
    public Optional<DomainEvent> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, BorrowingDoneEvent.class));
    }
}