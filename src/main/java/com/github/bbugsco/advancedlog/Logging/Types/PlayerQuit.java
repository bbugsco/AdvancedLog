package com.github.bbugsco.advancedlog.Logging.Types;

import com.github.bbugsco.advancedlog.Logging.LogEntry;
import com.github.bbugsco.advancedlog.Logging.LogType;

import org.bukkit.Location;

import java.lang.Long;
import java.util.Objects;

public class PlayerQuit extends LogEntry {

    // Log components
    private final String player;
    private final int x;
    private final int y;
    private final int z;
    private final String world;

    // Construct from log string
    public PlayerQuit(String log) {
        super(LogType.PLAYER_QUIT.getID(), Long.getLong(log.split(",")[0]));
        String[] logParts = log.split(",");
        this.player = logParts[1];
        this.x = Integer.parseInt(logParts[2]);
        this.y = Integer.parseInt(logParts[3]);
        this.z = Integer.parseInt(logParts[4]);
        this.world = logParts[5];
    }

    // Construct from event
    public PlayerQuit(String username, Location location) {
        super(LogType.PLAYER_QUIT.getID(), System.currentTimeMillis());
        this.player = username;
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = Objects.requireNonNull(location.getWorld()).getName();
    }

    @Override
    public String getString() {
        return getLogType().getID() + "," + getTimestamp().getTime() + "," + player + "," + x + "," + y + "," + z + "," + world;
    }

    public String getPlayer() {
        return this.player;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public String getWorld() {
        return this.world;
    }



}

