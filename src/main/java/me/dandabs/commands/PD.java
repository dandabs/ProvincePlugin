package me.dandabs.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Bukkit.getServer().getLogger().info(args[0]);
            Bukkit.getServer().getLogger().info(args[1]);

            if ((args[0].contains("rmconf"))) {

                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);

                UUID u = target.getUniqueId();

                player.sendMessage("§cRemoving " + args[1] + "'s regional file.");

                File userFile = new File("cloudconf" + File.separator + "users", u + ".yml");

                userFile.delete();

                player.sendMessage("§aSuccessfully deleted " + target.getUniqueId().toString() + ".yml.");

            }

        }

        return true;

    }

}
