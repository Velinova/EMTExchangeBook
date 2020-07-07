package velin.finki.emt.exchangebook.core.base;

import velin.finki.emt.exchangebook.core.infrastructure.eventlog.StoredDomainEvent;

import java.util.List;

public interface RemoteEventLog {

    List<StoredDomainEvent> events();
}
