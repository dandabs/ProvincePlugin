package me.dandabs.interfaces;

import me.dandabs.Plugin;
import me.dandabs.statics.TrainLocations;
import me.dandabs.utilities.TrainSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;

public class TrainSelectionGUI implements InventoryHolder, Listener {

    private final Inventory inv;

    public TrainSelectionGUI() {
        inv = Bukkit.createInventory(this, 9, "§3Please select a destination.");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {

        inv.setItem(0, createGuiItem(Material.RED_CONCRETE, "§cKodoresu City Train Station"));
        inv.setItem(1, createGuiItem(Material.BLUE_CONCRETE, "§bShoko City Subway"));
    }

    // Nice little method to create a gui item with a custom name, and description
    public ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metalore = new ArrayList<String>();

        for (String lorecomments : lore) {

            metalore.add(lorecomments);

        }

        meta.setLore(metalore);
        item.setItemMeta(meta);
        return item;
    }

    // You can open the inventory with this
    public void openInventory(Player p) {

        initializeItems();

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {
            public void run() {

                p.openInventory(inv);
            }
        }, 20L);

    }


    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        /*if (e.getInventory().getHolder() != this) {
            return;
        }*/

        if (!ChatColor.stripColor(e.getView().getTitle()).contains("Please select a destination.")) {
            return;
        }

        File userFile = new File("cloudconf" + File.separator + "users", e.getWhoClicked().getUniqueId().toString() + ".yml");

        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
        userConfig.set("player.uuid", e.getWhoClicked().getUniqueId().toString());
        //userConfig.set("player.startingregion", "kodoresu");

        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }

        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR)
            return;

        ArrayList<String> userRegions = new ArrayList<String>();

        if (e.getRawSlot() == 0) {

            Location dest = TrainLocations.getDestination("KodoresuCityTrain");
            double distance = e.getWhoClicked().getLocation().distance(dest);
            double seconds = (distance / 2) / 60;
            new TrainSystem().boardTrain(((Player) e.getWhoClicked()), Integer.valueOf((int) (Integer.valueOf((int) Instant.now().getEpochSecond()) + seconds)), "KodoresuCityTrain");

        }

        if (e.getRawSlot() == 1) {

            Location dest = TrainLocations.getDestination("ShokoCitySubway");
            double distance = e.getWhoClicked().getLocation().distance(dest);
            double seconds = (distance / 2) / 60;
            new TrainSystem().boardTrain(((Player) e.getWhoClicked()), Integer.valueOf((int) (Integer.valueOf((int) Instant.now().getEpochSecond()) + seconds)), "ShokoCitySubway");

        }

    }

}
