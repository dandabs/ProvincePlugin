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

public class Lobby implements CommandExecutor {

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

            player.teleport(RegionLocations.getLobby());

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.lobbymsg").replace("%player%", player.getName())));

        }

        return true;

    }

}
