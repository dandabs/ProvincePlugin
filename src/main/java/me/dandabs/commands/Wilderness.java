package me.dandabs.commands;

import me.dandabs.utilities.RegionGetters;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Random;

public class Wilderness implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            Integer x = getRandomIntegerWithinRange(-5000, 5000);

            Integer z = 0;

            if (new RegionGetters().whichRegion(player).contains("shoko")) z = getRandomIntegerWithinRange(-47500, -37500);
            if (new RegionGetters().whichRegion(player).contains("kodoresu")) z = getRandomIntegerWithinRange(-37500, -27500);
            if (new RegionGetters().whichRegion(player).contains("sotogawa")) z = getRandomIntegerWithinRange(-27500, -17500);
            if (new RegionGetters().whichRegion(player).contains("mekakushi")) z = getRandomIntegerWithinRange(-17500, -7500);

            if (new RegionGetters().whichRegion(player).contains("wasteland")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.fendforyourself")));
                return true;
            }

            Location loc = new Location(player.getWorld(), x, player.getWorld().getHighestBlockYAt(x, z), z);

            player.teleport(loc);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.randomlytp")));

        }

        return true;

    }

    private static int getRandomIntegerWithinRange(int min, int max) {
        int spread = max - min;
        return new Random().nextInt(spread + 1) + min;
    }

}
