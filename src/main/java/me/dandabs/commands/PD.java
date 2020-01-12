package me.dandabs.commands;

import me.dandabs.interfaces.TrainSelectionGUI;
import me.dandabs.statics.PresetItems;
import me.dandabs.utilities.TrainSystem;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class PD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if ((args[0].contains("rmconf"))) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                UUID u = target.getUniqueId();

                player.sendMessage("§cRemoving " + args[1] + "'s regional file.");

                File userFile = new File("cloudconf" + File.separator + "users", u + ".yml");

                userFile.delete();

                player.sendMessage("§aSuccessfully deleted " + target.getUniqueId().toString() + ".yml.");

            }

            if (args[0].contains("getscanner")) {

                player.getInventory().setItemInMainHand(new PresetItems().securityScanner(Integer.valueOf(args[1])));

            }

            if (args[0].contains("getpass")) {

                OfflinePlayer opl = Bukkit.getOfflinePlayer(args[2]);

                player.getInventory().setItemInMainHand(new PresetItems().securityPass(Integer.valueOf(args[1]), opl));

            }

            if (args[0].contains("getkey")) {

                player.getInventory().setItemInMainHand(new PresetItems().blankKey());

            }

            if (args[0].equals("setlore")) {

                ItemMeta meta = player.getItemInHand().getItemMeta();

                ArrayList<String> lore = new ArrayList<String>();
                lore.add(args[1]);

                meta.setLore(lore);

                player.getItemInHand().setItemMeta(meta);

            }

            if (args[0].equals("boardtrain")) {

                Integer endtime = Math.toIntExact((System.currentTimeMillis() / 1000L)) + 60;

                new TrainSystem().boardTrain(player, endtime, args[1]);

            }

            if (args[0].equals("trains")) {

                new TrainSelectionGUI().openInventory(Bukkit.getPlayer(args[2]));

            }

        }

        return true;

    }

}
