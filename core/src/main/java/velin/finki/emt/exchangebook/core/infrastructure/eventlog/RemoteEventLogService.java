package velin.finki.emt.exchangebook.core.infrastructure.eventlog;


import velin.finki.emt.exchangebook.core.base.RemoteEventLog;

public interface RemoteEventLogService {

    String source();

    RemoteEventLog currentLog(long lastProcessedId);

}
