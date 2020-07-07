package velin.finki.emt.exchangebook.core.infrastructure.eventlog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedRemoteEventRepository extends JpaRepository<ProcessedRemoteEvent,String> {
}
