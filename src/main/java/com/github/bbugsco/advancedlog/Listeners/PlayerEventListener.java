package com.github.bbugsco.advancedlog.Listeners;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.Types.PlayerJoin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventListener implements Listener {

    private final AdvancedLog plugin;

    public PlayerEventListener(AdvancedLog plugin) {
        this.plugin = plugin;
    }

    // PlayerJoin log
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
            plugin.getAdvancedLogger().log(new PlayerJoin(event.getPlayer().getName(), event.getPlayer().getLocation()));
    }

    // PlayerQuit log
    @EventHandler
    public void onPlayerQuit(PlayerJoinEvent event) {

    }

    // Advancement log
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        event.getAdvancement().toString();
    }

}
