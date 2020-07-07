package velin.finki.emt.exchangebook.core.infrastructure.eventlog;



import velin.finki.emt.exchangebook.core.base.DomainEvent;

import java.util.Optional;

public interface RemoteEventTranslator {

    boolean supports(StoredDomainEvent storedDomainEvent);

    Optional<DomainEvent> translate(StoredDomainEvent remoteEvent);
}
