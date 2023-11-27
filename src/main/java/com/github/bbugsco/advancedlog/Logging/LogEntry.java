package com.github.bbugsco.advancedlog.Logging;

import java.sql.Timestamp;

public abstract class LogEntry {

    // Common components
    private final LogType logType;
    private final Timestamp timestamp;

    // Construct with type and time
    public LogEntry(byte ID, Long time) {
        this.logType = LogType.get(ID);
        this.timestamp = new Timestamp(time);
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public LogType getLogType() {
        return logType;
    }

    // To Override
    public String getString() {
        return null;
    }

}
