package velin.finki.emt.exchangebook.core.infrastructure.eventlog;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import velin.finki.emt.exchangebook.core.base.DomainEvent;

@Service
public class DomainEventLogAppender {

    private final DomainEventLogService domainEventLogService;

    DomainEventLogAppender(DomainEventLogService domainEventLogService) {
        this.domainEventLogService = domainEventLogService;
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onDomainEvent(@NonNull DomainEvent domainEvent) {
        domainEventLogService.append(domainEvent);
    }
}
