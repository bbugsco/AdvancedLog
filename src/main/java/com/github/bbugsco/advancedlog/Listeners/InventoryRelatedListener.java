package com.github.bbugsco.advancedlog.Listeners;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.LogType;
import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

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

		// Get required variables
		Player player = (Player) event.getWhoClicked();
		Location chestLoc = ((Chest) event.getInventory().getHolder()).getLocation();

		if (chestLoc.getWorld() == null) return;

		// Create log
		String log = LogType.PLAYER_PICKUP_ITEM_CHEST  + " player " +
				player.getName() + " took " +
				event.getCurrentItem().getType() + " of " +
				event.getCurrentItem().getAmount() + " at " +
				chestLoc.getBlockX() + "," +
				chestLoc.getBlockY() + "," +
				chestLoc.getBlockZ() + "@" +
				chestLoc.getWorld().getName();

		// Send log string to logger
		plugin.getAdvancedLogger().log(log);

	}

	@EventHandler
	public void onItemPick(EntityPickupItemEvent event) {

		// Check enabled
		if (!plugin.getLogTypeEnabled(LogType.PLAYER_PICKUP_ITEM_DROP)) return;

		if (!(event.getEntity() instanceof Player)) return;

		// Get required variables
		Player player = (Player) event.getEntity();
		Location location = player.getLocation();
		ItemStack pickedUpItem = event.getItem().getItemStack();

		if (location.getWorld() == null) return;

		// Create log
		String log = LogType.PLAYER_PICKUP_ITEM_DROP + " player " +
				player.getName() + " picked up " +
				pickedUpItem.getType() + " of " +
				pickedUpItem.getAmount() + " at " +
				location.getBlockX() + "," +
				location.getBlockY() + "," +
				location.getBlockZ() + "@" +
				location.getWorld().getName();

		// Send log string to logger
		plugin.getAdvancedLogger().log(log);

	}

}
