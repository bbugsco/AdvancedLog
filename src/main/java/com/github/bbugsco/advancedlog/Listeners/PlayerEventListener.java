package com.github.bbugsco.advancedlog.Listeners;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.LogType;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerEventListener implements Listener {

    private final AdvancedLog plugin;

    public PlayerEventListener(AdvancedLog plugin) {
        this.plugin = plugin;
    }

    // PlayerJoin log
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
		System.out.println("Player join");
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_LOGIN)) return;

        // Get required variables
        Player player = event.getPlayer();
        Location location = player.getLocation();

        if (location.getWorld() == null) return;

        // Create log
        String log = LogType.PLAYER_LOGIN + " player " +
                player.getName() + " login at" +
                location.getBlockX() + "," +
                location.getBlockY() + "," +
                location.getBlockZ() + "@" +
                location.getWorld().getName();

        // Send log string to logger
        plugin.getAdvancedLogger().log(log);
    }

    // PlayerQuit log
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_QUIT)) return;

        // Get required variables
        Player player = event.getPlayer();
        Location location = player.getLocation();

        // Create log
        String log = LogType.PLAYER_QUIT + " player " +
                player.getName() + " quit at " +
                location.getBlockX() + "," +
                location.getBlockY() + "," +
                location.getBlockZ() + "@" +
                (location.getWorld() != null ? location.getWorld().getName() : " ");

        // Send log string to logger
        plugin.getAdvancedLogger().log(log);
    }

    // Advancement log
    @EventHandler
    public void onPlayerAdvancement(org.bukkit.event.player.PlayerAdvancementDoneEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_ADVANCEMENT)) return;

        // Get required variables
        Player player = event.getPlayer();

        // Create log
        String log = LogType.PLAYER_ADVANCEMENT + " player " +
                player.getName() + " got advancement " +
		        event.getAdvancement().getKey();

        // Send log string to logger
        plugin.getAdvancedLogger().log(log);
    }

    // Player command event
    @EventHandler
    public void onPlayerCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent event) {
        // Check enabled
        if (!plugin.getLogTypeEnabled(LogType.PLAYER_COMMAND)) return;

        // Get required variables
        Player player = event.getPlayer();

        // Create log
        String log = LogType.PLAYER_COMMAND + " player " +
                player.getName() + " attempted command " +
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
		String log = LogType.PLAYER_DEATH + " player " +
				player.getName() + " died, reason: " +
				message + " at " +
				location.getBlockX() + "," +
				location.getBlockY() + "," +
				location.getBlockZ() + "@" +
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
		String log = LogType.PLAYER_DEATH + " player " +
				victim.getName() + " killed by " +
				killer.getName() + " at " +
				location.getBlockX() + "," +
				location.getBlockY() + "," +
				location.getBlockZ() + "@" +
				location.getWorld().getName();

		// Send log string to logger
		plugin.getAdvancedLogger().log(log);

	}

}
