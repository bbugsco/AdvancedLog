package com.github.bbugsco.advancedlog.Logging.Types;

import com.github.bbugsco.advancedlog.Logging.LogEntry;
import com.github.bbugsco.advancedlog.Logging.LogType;

public class ServerStart extends LogEntry {

    // Construct from event
    public ServerStart() {
        super(LogType.SERVER_START.getID(), System.currentTimeMillis());
    }

    // Construct from log
    public ServerStart(String log) {
        super(LogType.SERVER_START.getID(), Long.getLong(log.split(",")[0]));
    }

}
