package me.dandabs.interfaces;

import me.dandabs.Plugin;
import me.dandabs.statics.RegionLocations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
import java.io.IOException;
import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class RegionSelectionGUI implements InventoryHolder, Listener {
    // Create a new inventory, with "this" owner for comparison with other inventories, a size of nine, called example
    private final Inventory inv;

    public RegionSelectionGUI() {
        inv = Bukkit.createInventory(this, 9, "§l§cWelcome! §eSelect a region.");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {

        inv.setItem(1, createGuiItem(Material.DARK_OAK_SIGN, "§cWelcome!", "§aHere on CloudCraft, you are assigned to a region.", "§eThis region will be the only place you can build.", "§bView each region's description and click on the one you'd like to start with.", "§4Note: you may change your region later."));

        inv.setItem(3, createGuiItem(Material.BIRCH_WOOD, "§aKodoresu", "§a§l✔ §r§a PvP", "§a§l✔ §r§a Ice Melting", "§a§l✔ §r§a Mob Spawning", "§c§l✘ §r§c Mob Griefing", "§c§l✘ §r§c Leaf Decaying"));
        inv.setItem(4, createGuiItem(Material.ICE, "§cSotogawa", "§a§l✔ §r§a PvP", "§a§l✔ §r§a Ice Melting", "§a§l✔ §r§a Mob Spawning", "§a§l✔ §c§a Mob Griefing", "§a§l✔ §r§a Leaf Decaying"));
        inv.setItem(5, createGuiItem(Material.JUNGLE_LEAVES, "§bMekakushi", "§c§l✘ §r§c PvP", "§a§l✔ §r§a Ice Melting", "§c§l✘ §r§c Mob Spawning", "§c§l✘ §r§c Mob Griefing", "§a§l✔ §r§a Leaf Decaying"));
        inv.setItem(6, createGuiItem(Material.BRICKS, "§6Shoko", "§c§l✘ §r§c PvP", "§c§l✘ §r§c Ice Melting", "§c§l✘ §r§c Mob Spawning", "§c§l✘ §r§c Mob Griefing", "§c§l✘ §c§l Leaf Decaying"));
        //inv.setItem(7, createGuiItem(Material.BLACK_BANNER, "§eKotonaru", "§aFirst line of the lore", "§bSecond line of the lore"));
    }

    // Nice little method to create a gui item with a custom name, and description
    public ItemStack createGuiItem(Material material, String name, String...lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metalore = new ArrayList<String>();

        for(String lorecomments : lore) {

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

        if (!e.getInventory().contains(createGuiItem(Material.BIRCH_WOOD, "§aKodoresu", "§a§l✔ §r§a PvP", "§a§l✔ §r§a Ice Melting", "§a§l✔ §r§a Mob Spawning", "§c§l✘ §r§c Mob Griefing", "§c§l✘ §r§c Leaf Decaying"))) {
            return;
        }

        File userFile = new File("cloudconf" + File.separator + "users", e.getWhoClicked().getUniqueId().toString() + ".yml");

        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
        userConfig.set("player.uuid", e.getWhoClicked().getUniqueId().toString());
        //userConfig.set("player.startingregion", "kodoresu");

        if (e.getClick().equals(ClickType.NUMBER_KEY)){
            e.setCancelled(true);
        }

        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.DARK_OAK_SIGN)
            return;

        ArrayList<String> userRegions = new ArrayList<String>();

        if (e.getRawSlot() == 3) {
            userConfig.set("player.startingregion", "kodoresu");
            userRegions.add("kodoresu");
            userConfig.set("player.nationalities", userRegions);
            Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + e.getWhoClicked().getName() + " permission set group.kodoresu");
            e.getWhoClicked().teleport(RegionLocations.getLobby());
            Bukkit.getServer().broadcastMessage(" §d{§5+§d} §dWelcome §5" + e.getWhoClicked().getName() + " §dto CloudCraft for the first time! They chose the §4Kodoresu §dregion.");
        }

        if (e.getRawSlot() == 4) {
            userConfig.set("player.startingregion", "sotogawa");
            userRegions.add("sotogawa");
            userConfig.set("player.nationalities", userRegions);
            Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + e.getWhoClicked().getName() + " permission set group.sotogawa");
            e.getWhoClicked().teleport(RegionLocations.getLobby());
            Bukkit.getServer().broadcastMessage(" §d{§5+§d} §dWelcome §5" + e.getWhoClicked().getName() + " §dto CloudCraft for the first time! They chose the §4Sotogawa §dregion.");
        }

        if (e.getRawSlot() == 5) {
            userConfig.set("player.startingregion", "mekakushi");
            userRegions.add("mekakushi");
            userConfig.set("player.nationalities", userRegions);
            Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + e.getWhoClicked().getName() + " permission set group.mekakushi");
            e.getWhoClicked().teleport(RegionLocations.getLobby());
            Bukkit.getServer().broadcastMessage(" §d{§5+§d} §dWelcome §5" + e.getWhoClicked().getName() + " §dto CloudCraft for the first time! They chose the §4Mekakushi §dregion.");
        }

        if (e.getRawSlot() == 6) {
            userConfig.set("player.startingregion", "shoko");
            userRegions.add("shoko");
            userConfig.set("player.nationalities", userRegions);
            Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + e.getWhoClicked().getName() + " permission set group.shoko");
            e.getWhoClicked().teleport(RegionLocations.getLobby());
            Bukkit.getServer().broadcastMessage(" §d{§5+§d} §dWelcome §5" + e.getWhoClicked().getName() + " §dto CloudCraft for the first time! They chose the §4Shoko §dregion.");
        }

        if (e.getRawSlot() == 7) {
            userConfig.set("player.startingregion", "kotonaru");
            userRegions.add("kotonaru");
            userConfig.set("player.nationalities", userRegions);
            Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + e.getWhoClicked().getName() + " permission set group.kotonaru");
            e.getWhoClicked().teleport(RegionLocations.getLobby());
            Bukkit.getServer().broadcastMessage(" §d{§5+§d} §dWelcome §5" + e.getWhoClicked().getName() + " §dto CloudCraft for the first time! They chose the §4Kotonaru §dregion.");
        }

        e.getWhoClicked().sendMessage(ChatColor.RED + "Welcome! Learn how to play on our server at this link: " + ChatColor.YELLOW + "https://s.cloudcraftmc.org.uk/introduction" + ChatColor.RED + ".");

        try {
            userConfig.save(userFile);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
