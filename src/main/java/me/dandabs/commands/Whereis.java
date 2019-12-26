package me.dandabs.commands;

import me.dandabs.utilities.RegionGetters;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Whereis implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

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

            String region = new RegionGetters().whichRegion(player);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.whereis").replace("%region%", region).replace("%player%", player.getName())));

        }

        return true;

    }

}
