package me.dandabs.listeners;

import me.dandabs.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Door;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlayerInteract implements Listener {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        // Admin iron door access

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType() == Material.IRON_DOOR && e.getPlayer().getName().equals("xpaistinpannu") && e.getPlayer().getItemInHand().getType() == Material.AIR) {

            Bukkit.getServer().getLogger().info(e.getPlayer().getName());

            Block door = e.getClickedBlock();
            BlockData doordata = door.getBlockData();

            ((Openable) doordata).setOpen(true);

            door.setBlockData(doordata);

            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 100, 100);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {

                @Override
                public void run() {

                    ((Openable) doordata).setOpen(false);

                    door.setBlockData(doordata);

                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 100, 100);

                }

            }, 20 * 2);

        }

        // Player door key card system

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        File keyFile = new File("cloudconf", "keycards.yml");
        YamlConfiguration keyConfig = YamlConfiguration.loadConfiguration(keyFile);

        Player p = e.getPlayer();
        ItemStack i = e.getPlayer().getItemInHand();

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType() == Material.IRON_DOOR) {

            Door thedoor = (Door) e.getClickedBlock().getState().getData();

            e.setCancelled(true);

            if (i.getType() == Material.TRIPWIRE_HOOK) {

                if (i.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Blank Key")) {

                    for (BlockFace bf : BlockFace.values()) {

                        if (e.getClickedBlock().getRelative(bf).getType() == Material.DROPPER) {

                            return;

                        }

                    }

                    Door d = (Door) e.getClickedBlock().getState().getData();

                    for (String s : keyConfig.getKeys(true)) {

                        ArrayList<String> a = (ArrayList<String>) keyConfig.getStringList(s);

                        if (a.get(0).equals(String.valueOf(e.getClickedBlock().getX()))) {

                            if (a.get(2).equals(String.valueOf(e.getClickedBlock().getZ()))) {

                                if ((a.get(1).equals(String.valueOf(e.getClickedBlock().getY()))) || (a.get(1).equals(String.valueOf(e.getClickedBlock().getY() - 1))) || (a.get(1).equals(String.valueOf(e.getClickedBlock().getY() + 1)))) {

                                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.keyexists")));
                                    return;

                                }

                            }

                        }

                    }

                    ArrayList<String> keyinfo = new ArrayList<String>();

                    keyinfo.add(String.valueOf(e.getClickedBlock().getX()));
                    keyinfo.add(String.valueOf(e.getClickedBlock().getY()));
                    keyinfo.add(String.valueOf(e.getClickedBlock().getZ()));

                    String keycode = randomAlphaNumeric(6);

                    keyConfig.set(keycode, keyinfo);

                    try {
                        keyConfig.save(keyFile);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    ItemMeta im = i.getItemMeta();
                    im.setDisplayName(ChatColor.GOLD + keycode);
                    i.setItemMeta(im);
                    i.addUnsafeEnchantment(Enchantment.DIG_SPEED, 5);

                    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_LOCKED, 100, 100);

                } else {

                    //if (ChatColor.valueOf(i.getItemMeta().getDisplayName()) == ChatColor.GOLD) {

                    ArrayList<String> keyinfo = (ArrayList<String>) keyConfig.getStringList(ChatColor.stripColor(i.getItemMeta().getDisplayName()));

                    if (e.getClickedBlock().getX() == Integer.valueOf(keyinfo.get(0)) && e.getClickedBlock().getZ() == Integer.valueOf(keyinfo.get(2)) && e.getClickedBlock().getY() == Integer.valueOf(keyinfo.get(1))) {

                        Block door = e.getClickedBlock();
                        BlockData doordata = door.getBlockData();

                        ((Openable) doordata).setOpen(true);
                        door.setBlockData(doordata);
                        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 100, 100);

                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Plugin.getInstance(), new Runnable() {

                            @Override
                            public void run() {

                                ((Openable) doordata).setOpen(false);

                                door.setBlockData(doordata);

                                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_IRON_DOOR_CLOSE, 100, 100);

                            }

                        }, 20 * 4);

                    } else {

                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongkey")));

                    }

                    //}

                }

            }

        }

    }


}
