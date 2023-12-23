package com.github.bbugsco.advancedlog;

import com.github.bbugsco.advancedlog.Logging.LogType;
import com.github.bbugsco.advancedlog.Logging.Logger;
import org.bukkit.plugin.PluginManager;
import java.util.HashMap;

public final class AdvancedLog extends org.bukkit.plugin.java.JavaPlugin {

    private HashMap<LogType, Boolean> enabledLogTypes;
    private Logger logger;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        this.logger = new Logger(this);

        // Get enabled log types from config
        enabledLogTypes = new HashMap<>();
        for (LogType type : LogType.values()) {
            enabledLogTypes.put(type, getConfig().getBoolean("log_types." + type.name()));
        }

        // Register Events
        PluginManager pm = org.bukkit.Bukkit.getPluginManager();
        pm.registerEvents(new com.github.bbugsco.advancedlog.Listeners.PlayerEventListener(this), this);
		pm.registerEvents(new com.github.bbugsco.advancedlog.Listeners.InventoryRelatedListener(this), this);

        // Log Start
        if(getLogTypeEnabled(LogType.SERVER_START)) getAdvancedLogger().log(LogType.SERVER_START.name());

    }

    @Override
    public void onDisable() {
        // Log Stop
        if(getLogTypeEnabled(LogType.SERVER_STOP)) getAdvancedLogger().log(LogType.SERVER_STOP.name());

        this.logger.close();
    }

    public Boolean getLogTypeEnabled(LogType type) {
        return enabledLogTypes.get(type);
    }

    public Logger getAdvancedLogger() {
        return this.logger;
    }

}
