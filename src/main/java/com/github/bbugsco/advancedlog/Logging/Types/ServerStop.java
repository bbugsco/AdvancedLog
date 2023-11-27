package com.github.bbugsco.advancedlog.Logging.Types;

import com.github.bbugsco.advancedlog.Logging.LogEntry;
import com.github.bbugsco.advancedlog.Logging.LogType;

public class ServerStop extends LogEntry {

    // Construct from event
    public ServerStop() {
        super(LogType.SERVER_STOP.getID(), System.currentTimeMillis());
    }

    // Construct from log
    public ServerStop(String log) {
        super(LogType.SERVER_STOP.getID(), Long.getLong(log.split(",")[0]));
    }

}
