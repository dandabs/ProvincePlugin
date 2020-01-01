package me.dandabs.statics;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PresetItems {

    public ItemStack securityScanner(Integer level) {

        ItemStack scanner = new ItemStack(Material.DROPPER);
        scanner.addUnsafeEnchantment(Enchantment.SILK_TOUCH, level);
        scanner.setAmount(1);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(String.valueOf(level));

        ItemMeta scannermeta = scanner.getItemMeta();
        scannermeta.setDisplayName(ChatColor.DARK_RED + "SECURITY SCANNER LEVEL" + level);

        scanner.setItemMeta(scannermeta);

        return scanner;

    }

    public ItemStack securityPass(Integer level, OfflinePlayer owner) {

        ItemStack scanner = new ItemStack(Material.PAPER);
        scanner.addUnsafeEnchantment(Enchantment.SILK_TOUCH, level);
        scanner.setAmount(1);

        ArrayList<String> lore = new ArrayList<String>();
        lore.add(String.valueOf(level));
        lore.add(ChatColor.RED + "Issued for Level " + ChatColor.GOLD + level + ChatColor.RED + ".");
        lore.add(ChatColor.RED + "Property of " + ChatColor.GOLD + owner.getName() + ChatColor.RED + ".");

        ItemMeta scannermeta = scanner.getItemMeta();
        scannermeta.setDisplayName(ChatColor.DARK_RED + "Security Clearance Pass");
        scannermeta.setLore(lore);

        scanner.setItemMeta(scannermeta);

        return scanner;

    }

    public ItemStack blankKey() {

        ItemStack key = new ItemStack(Material.TRIPWIRE_HOOK);
        key.setAmount(1);

        ItemMeta keymeta = key.getItemMeta();
        keymeta.setDisplayName(ChatColor.GRAY + "Blank Key");

        key.setItemMeta(keymeta);

        return key;

    }

}
