package com.ashkiano.buildheightlimit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildHeightLimit extends JavaPlugin implements Listener {

    private int maxBuildHeight;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        maxBuildHeight = config.getInt("max-build-height", 100);
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("BuildHeightLimit has been enabled with a max build height of " + maxBuildHeight);
        Metrics metrics = new Metrics(this, 22045);
        this.getLogger().info("Thank you for using the BuildHeightLimit plugin! If you enjoy using this plugin, please consider making a donation to support the development. You can donate at: https://donate.ashkiano.com");
    }

    @Override
    public void onDisable() {
        getLogger().info("BuildHeightLimit has been disabled");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission("buildheight.bypass") && event.getBlock().getY() > maxBuildHeight) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot build above height " + maxBuildHeight);
        }
    }
}
