package me.dandabs.commands;

import me.dandabs.statics.RegionLocations;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Spawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player;

            if (args.length == 1) {

                player = Bukkit.getPlayer(args[0].toString());

            } else player = (Player) sender;

            //Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            String region = userConfig.getString("player.startingregion");

            if (region.contains("kodoresu")) {
                player.teleport(RegionLocations.getKodoresuSpawn());
            }
            if (region.contains("mekakushi")) {
                player.teleport(RegionLocations.getMekakushiSpawn());
            }
            if (region.contains("shoko")) {
                player.teleport(RegionLocations.getShokoSpawn());
            }
            if (region.contains("sotogawa")) {
                player.teleport(RegionLocations.getSotogawaSpawn());
            }
            if (region.contains("kotonaru")) {
                player.teleport(RegionLocations.getKotonaruSpawn());
            }

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.spawnmsg").replace("%player%", player.getName())));

        }

        return true;

    }

}
