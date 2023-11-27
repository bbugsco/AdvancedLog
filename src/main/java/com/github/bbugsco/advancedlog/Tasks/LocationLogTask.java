package com.github.bbugsco.advancedlog.Tasks;

import com.github.bbugsco.advancedlog.AdvancedLog;
import com.github.bbugsco.advancedlog.Logging.LogType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LocationLogTask extends BukkitRunnable {

    private final AdvancedLog plugin;

    public LocationLogTask(AdvancedLog plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location location = player.getLocation();
            World world = location.getWorld();

            if (world == null) continue;

            String log = LogType.PLAYER_LOCATION.getID() + "," +
                    player.getName() + "," +
                    location.getBlockX() + "," +
                    location.getBlockY() + "," +
                    location.getBlockZ() + "," +
                    location.getWorld().getName() + ",";

            plugin.getAdvancedLogger().log(log);

        }
    }

}
