package me.dandabs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dropper;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.io.File;

public class InventoryOpen implements Listener {

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e) {

        Player p = (Player) e.getPlayer();

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        if (!e.getView().getTitle().contains(ChatColor.DARK_RED + "SECURITY SCANNER")) {

            return;

        }

        if (!(p.getItemInHand().getType() == Material.PAPER)) {

            e.setCancelled(true);
            return;

        }

        e.setCancelled(true);

        Integer scannerlevel = Integer.valueOf(e.getView().getTitle().split("LEVEL")[1]);

        Integer passlevel = Integer.valueOf(p.getItemInHand().getItemMeta().getLore().get(0));

        if (passlevel >= scannerlevel) {

            Dropper scanner = (Dropper) e.getInventory().getHolder();

            Block activation = scanner.getBlock().getRelative(BlockFace.DOWN);

            if (!(activation.getType() == Material.REDSTONE_BLOCK)) {

                activation.setType(Material.REDSTONE_BLOCK);

            } else {

                activation.setType(Material.RED_CONCRETE);

            }

        } else {

            p.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.toolowlevel")));

        }

    }

}
