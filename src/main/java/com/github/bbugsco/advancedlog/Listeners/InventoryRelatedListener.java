package com.github.bbugsco.advancedlog.Listeners;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.LogType;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryRelatedListener implements Listener {

	private final AdvancedLog plugin;

	public InventoryRelatedListener(AdvancedLog plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		// Check enabled
		if (!plugin.getLogTypeEnabled(LogType.PLAYER_PICKUP_ITEM_CHEST)) return;

		// Check is player picking up item from chest
		if (!(event.getInventory().getHolder() instanceof Chest)) return;
		if (!(event.getCurrentItem() != null && event.getWhoClicked() instanceof Player)) return;

		Player player = (Player) event.getWhoClicked();
		Location chestLoc = ((Chest) event.getInventory().getHolder()).getLocation();

		if (chestLoc.getWorld() == null) return;

		// Create log
		String sb = LogType.PLAYER_PICKUP_ITEM_CHEST.getID() + "," +
				player.getName() + "," +
				event.getCurrentItem().getType() + "," +
				event.getCurrentItem().getAmount() + "," +
				chestLoc.getBlockX() + "," +
				chestLoc.getBlockY() + "," +
				chestLoc.getBlockZ() + "," +
				chestLoc.getWorld().getName() + ",";

		// Send log string to logger
		plugin.getAdvancedLogger().log(sb);

	}
}
