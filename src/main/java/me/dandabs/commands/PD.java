package me.dandabs.commands;

import me.dandabs.interfaces.TrainSelectionGUI;
import me.dandabs.utilities.RegionPoints;
import me.dandabs.utilities.TrainSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.getName().contains("xpaistinpannu")) return true;

            if (args[0].equals("setlore")) {

                ItemMeta meta = player.getItemInHand().getItemMeta();

                ArrayList<String> lore = new ArrayList<String>();
                lore.add(args[1]);

                meta.setLore(lore);

                player.getItemInHand().setItemMeta(meta);

            }

            if (args[0].equals("boardtrain")) {

                Integer endtime = Math.toIntExact((System.currentTimeMillis() / 1000L)) + 20;

                new TrainSystem().boardTrain(player, endtime, args[1]);

            }

            if (args[0].equals("trains")) {

                new TrainSelectionGUI().openInventory(Bukkit.getPlayer(args[2]));

            }

            if (args[0].equals("points")) {

                if (args[1].contains("kodoresu"))
                    player.sendMessage("" + new RegionPoints().calculatePoints("kodoresu"));

                if (args[1].contains("shoko"))
                    player.sendMessage("" + new RegionPoints().calculatePoints("shoko"));

                if (args[1].contains("mekakushi"))
                    player.sendMessage("" + new RegionPoints().calculatePoints("mekakushi"));

                if (args[1].contains("sotogawa"))
                    player.sendMessage("" + new RegionPoints().calculatePoints("sotogawa"));

            }

        } else {

            if (args[0].contains("addpoints")) {

                File userFile = new File("cloudconf" + File.separator + "users", Bukkit.getOfflinePlayer(args[2]).getUniqueId().toString() + ".yml");
                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);


                if (userConfig.isInt("player.bonuspoints")) {

                    userConfig.set("player.bonuspoints", userConfig.getInt("player.bonuspoints") + Integer.parseInt(args[1]));

                } else {

                    userConfig.set("player.bonuspoints", Integer.parseInt(args[1]));

                }


                try {
                    userConfig.save(userFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("Added " + args[1] + " points to player " + args[2] + ".");

            }

        }

        return true;

    }

}
