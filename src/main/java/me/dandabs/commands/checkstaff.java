package me.dandabs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

public class checkstaff implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userDirectory = new File("cloudconf" + File.separator + "users");

            File[] users = userDirectory.listFiles();

            Player player = (Player) sender;

            player.sendMessage("&bCloud&fCraft &6Weekly Staff Playtime");
            player.sendMessage("&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-");
            player.sendMessage("&6Names in &agreen &6have completed this month's requirements.");
            player.sendMessage("&6Names in &cred &6have not completed this month's requirements.");
            player.sendMessage("&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-");

            for (File userFile : users) {

                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

                boolean staff = userConfig.getBoolean("player.isstaff", false);

                if (staff) {

                    int minutes = 0;

                    /*minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + LocalDateTime.now().getDayOfMonth());
                    minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + (LocalDateTime.now().getDayOfMonth() - 1));
                    minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + (LocalDateTime.now().getDayOfMonth() - 2));
                    minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + (LocalDateTime.now().getDayOfMonth() - 3));
                    minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + (LocalDateTime.now().getDayOfMonth() - 4));
                    minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + (LocalDateTime.now().getDayOfMonth() - 5));
                    minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + (LocalDateTime.now().getDayOfMonth() - 6));*/

                    for (String key : userConfig.getConfigurationSection("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth()).getKeys(true)) {

                        minutes += userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + key);

                    }

                    int days = 0;

                    try {

                        days = userConfig.getConfigurationSection("player.playtime." + LocalDateTime.now().getYear()).getKeys(false).size();

                        ChatColor cctotal = ChatColor.RED;
                        if (days >= 8 && minutes >= 960) cctotal = ChatColor.GREEN;

                        ChatColor ccminutes = ChatColor.RED;
                        if (minutes >= 960) ccminutes = ChatColor.GREEN;

                        ChatColor ccdays = ChatColor.RED;
                        if (days >= 8) ccdays = ChatColor.GREEN;

                        player.sendMessage(cctotal + Bukkit.getOfflinePlayer(UUID.fromString(userFile.getName().split(".yml")[0])).getName() + ChatColor.GOLD + " | " + ccdays + minutes + "min" + ChatColor.GOLD + " | " + ccdays + days + "days");

                    } catch (NullPointerException e) {

                    }

                }

            }

            player.sendMessage("&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-&b-&c-");

        }
        return true;
    }
}
