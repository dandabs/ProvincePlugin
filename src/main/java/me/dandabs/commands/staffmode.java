package me.dandabs.commands;

import me.dandabs.statics.RegionLocations;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class staffmode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            if (player.hasPermission("group.admin")) {

                if (player.isOp()) {

                    player.setOp(false);
                    player.setGameMode(GameMode.SURVIVAL);
                    player.teleport(new RegionLocations().getLobby());

                } else {

                    player.setOp(true);
                    player.setGameMode(GameMode.CREATIVE);

                }

            } else {

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.nopermission")));

            }

        }
        return true;
    }
}