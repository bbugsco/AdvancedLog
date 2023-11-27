package com.github.bbugsco.advancedlog.Logging;

public enum LogType {

    PLAYER_LOGIN((byte) 0),
    PLAYER_QUIT((byte) 1),
    PLAYER_ADVANCEMENT((byte) 2),
    PLAYER_LOCATION((byte) 3),
    PLAYER_COMMAND((byte) 4),
    PLAYER_DEATH((byte) 5),
    PLAYER_KILL_PLAYER((byte) 6),
    PLAYER_PICKUP_ITEM_DROP((byte) 7),
    PLAYER_PICKUP_ITEM_CHEST((byte) 8),
    SERVER_START((byte) 9),
    SERVER_STOP((byte) 10);

    private final byte ID;

    LogType(byte id) {
        ID = id;
    }

    public byte getID() {
        return ID;
    }

    public static LogType get(byte ID) {
        for (LogType type : values()) {
            if (type.getID() == ID) return type;
        }
        return null;
    }

}
