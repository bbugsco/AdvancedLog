package com.github.bbugsco.advancedlog;

import com.github.bbugsco.advancedlog.Listeners.PlayerEventListener;
import com.github.bbugsco.advancedlog.Logging.LogType;
import com.github.bbugsco.advancedlog.Logging.Types.ServerStart;
import com.github.bbugsco.advancedlog.Logging.Types.ServerStop;

import com.github.bbugsco.advancedlog.Tasks.LocationLogTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class AdvancedLog extends JavaPlugin {

    private HashMap<LogType, Boolean> enabledLogTypes;
    private Logger logger;

    @Override
    public void onEnable() {

        // Save config before reading from config
        saveDefaultConfig();

        // Get enabled log types from config
        enabledLogTypes = new HashMap<>();
        for (LogType type : LogType.values()) {
            enabledLogTypes.put(type, getConfig().getBoolean("log_types." + type.name()));
        }

        // Initialize classes
        this.logger = new Logger(this);

        // Register Events
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerEventListener(this), this);

        // Run tasks
        if (getLogTypeEnabled(LogType.PLAYER_LOCATION)) new LocationLogTask().runTaskTimerAsynchronously(this, 1L, getConfig().getLong("log_intervals.PLAYER_LOCATION"));

        // Log Start
        if(getLogTypeEnabled(LogType.SERVER_START)) getAdvancedLogger().log(new ServerStart());

    }

    @Override
    public void onDisable() {
        // Log Stop
        if(getLogTypeEnabled(LogType.SERVER_STOP)) getAdvancedLogger().log(new ServerStop());

        this.logger.close();
    }

    public Boolean getLogTypeEnabled(LogType type) {
        return enabledLogTypes.get(type);
    }

    public Logger getAdvancedLogger() {
        return this.logger;
    }

}
