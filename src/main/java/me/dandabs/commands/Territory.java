package me.dandabs.commands;

import me.dandabs.utilities.RegionGetters;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Territory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            if (args.length >= 1) {

                if (args[0].contains("showmonarch")) {

                    if (args.length != 2) {

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));
                        return true;

                    }

                    if (!player.hasPermission("provinceplugin.territory.showmonarch")) {

                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.nopermission")));
                        return true;

                    }

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.showmonarch").replace("%region%", args[1]).replace("%monarch%", new RegionGetters().whichMonarchName(args[1]))));

                }


            } else {

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));

            }

        }

        return true;

    }

}
