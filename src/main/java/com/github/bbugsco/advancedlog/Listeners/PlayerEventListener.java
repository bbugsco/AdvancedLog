package com.github.bbugsco.advancedlog.Listeners;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.LogType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerEventListener implements Listener {

    private final AdvancedLog plugin;

    public PlayerEventListener(AdvancedLog plugin) {
        this.plugin = plugin;
    }

    // PlayerJoin log
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_LOGIN)) return;

        // Get required variables
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();

        if (world == null) return;

        // Create log
        String sb = LogType.PLAYER_LOGIN.getID() + "," +
                player.getName() + "," +
                location.getBlockX() + "," +
                location.getBlockY() + "," +
                location.getBlockZ() + "," +
                location.getWorld().getName();

        // Send log string to logger
        plugin.getAdvancedLogger().log(sb);
    }

    // PlayerQuit log
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_QUIT)) return;

        // Get required variables
        Player player = event.getPlayer();
        Location location = player.getLocation();
        World world = location.getWorld();

        if (world == null) return;

        // Create log
        String sb = LogType.PLAYER_QUIT.getID() + "," +
                player.getName() + "," +
                location.getBlockX() + "," +
                location.getBlockY() + "," +
                location.getBlockZ() + "," +
                location.getWorld().getName();

        // Send log string to logger
        plugin.getAdvancedLogger().log(sb);
    }

    // Advancement log
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_ADVANCEMENT)) return;

        // Get required variables
        Player player = event.getPlayer();

        // Create log
        String sb = LogType.PLAYER_ADVANCEMENT.getID() + "," +
                player.getName() + "," +
		        event.getAdvancement().getKey();

        // Send log string to logger
        plugin.getAdvancedLogger().log(sb);
    }

    // Player command event
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_COMMAND)) return;

        // Get required variables
        Player player = event.getPlayer();

        // Create log
        String sb = LogType.PLAYER_COMMAND.getID() + "," +
                player.getName() + "," +
		        event.getMessage();

        // Send log string to logger + ","
        plugin.getAdvancedLogger().log(sb);
    }

}
