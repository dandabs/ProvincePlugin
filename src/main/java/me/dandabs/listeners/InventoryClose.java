package me.dandabs.listeners;

import me.dandabs.Plugin;
import me.dandabs.interfaces.RegionSelectionGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.io.File;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {
            public void run() {




        Bukkit.getServer().getLogger().info("1");

        if (e.getInventory().contains(new RegionSelectionGUI().createGuiItem(Material.BIRCH_LEAVES, "§aKodoresu", "§aHistorical city of CloudCraft.", "§aArmour + Weapons", "§aMonarch: dandabs")))
        {

            Bukkit.getServer().getLogger().info("2");

            File userFile = null;

            userFile = new File("cloudconf" + File.separator + "users", e.getPlayer().getUniqueId().toString() + ".yml");

            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            Bukkit.getServer().getLogger().info(String.valueOf(userConfig.contains("player.startingregion")));

            if (userConfig.contains("player.startingregion") == false) {

                Bukkit.getServer().getLogger().info("3");

                new me.dandabs.interfaces.RegionSelectionGUI().openInventory((Player) e.getPlayer());

            }


        }

            }
        }, 20L);

    }

}
