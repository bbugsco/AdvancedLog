package com.github.bbugsco.advancedlog.Listeners;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.LogType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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
		System.out.println("Player join");
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_LOGIN)) return;

        // Get required variables
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if (location.getWorld() == null) return;

        // Create log
        String log = LogType.PLAYER_LOGIN.getID() + "," +
                player.getName() + "," +
                location.getBlockX() + "," +
                location.getBlockY() + "," +
                location.getBlockZ() + "," +
                location.getWorld().getName();

        // Send log string to logger
        plugin.getAdvancedLogger().log(log);
    }

    // PlayerQuit log
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_QUIT)) return;

        // Get required variables
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if (location.getWorld() == null) {
	        System.out.println("nerd check");
	        return;
        }

        // Create log
        String log = LogType.PLAYER_QUIT.getID() + "," +
                player.getName() + "," +
                location.getBlockX() + "," +
                location.getBlockY() + "," +
                location.getBlockZ() + "," +
		        location.getWorld().getName();

        // Send log string to logger
        plugin.getAdvancedLogger().log(log);
    }

    // Advancement log
    @EventHandler
    public void onPlayerAdvancement(PlayerAdvancementDoneEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_ADVANCEMENT)) return;

        // Get required variables
        Player player = event.getPlayer();

        // Create log
        String log = LogType.PLAYER_ADVANCEMENT.getID() + "," +
                player.getName() + "," +
		        event.getAdvancement().getKey();

        // Send log string to logger
        plugin.getAdvancedLogger().log(log);
    }

    // Player command event
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_COMMAND)) return;

        // Get required variables
        Player player = event.getPlayer();

        // Create log
        String log = LogType.PLAYER_COMMAND.getID() + "," +
                player.getName() + "," +
		        event.getMessage();

        // Send log string to logger + ","
        plugin.getAdvancedLogger().log(log);
    }

	// Player Death
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		// Check enabled
		if (!plugin.getLogTypeEnabled(LogType.PLAYER_DEATH)) return;

		// Get required variables
		Player player = event.getEntity();
		String message = event.getDeathMessage();
		Location location = player.getLocation();

		if (location.getWorld() == null) return;

		// Create log
		String log = LogType.PLAYER_DEATH.getID() + "," +
				player.getName() + "," +
				message + "," +
				location.getBlockX() + "," +
				location.getBlockY() + "," +
				location.getBlockZ() + "," +
				location.getWorld().getName();

		// Send log string to logger
		plugin.getAdvancedLogger().log(log);
	}

	// Player Kill Player
	@EventHandler
	public void onPlayerKillPlayer(PlayerDeathEvent event) {

		// Check enabled
		if (!plugin.getLogTypeEnabled(LogType.PLAYER_DEATH)) return;

		// Get required variables
		Player victim = event.getEntity();
		Location location = victim.getLocation();

		if (victim.getKiller() == null) return;
		Player killer = victim.getKiller();

		if (location.getWorld() == null) return;

		// Create log
		String log = LogType.PLAYER_DEATH.getID() + "," +
				victim.getName() + "," +
				killer.getName() + "," +
				location.getBlockX() + "," +
				location.getBlockY() + "," +
				location.getBlockZ() + "," +
				location.getWorld().getName();

		// Send log string to logger
		plugin.getAdvancedLogger().log(log);

	}

}
