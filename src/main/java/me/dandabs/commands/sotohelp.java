package me.dandabs.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class sotohelp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
            player.sendMessage("Sotogawa is an SMP region on CloudCraft. SMP has commands like /claim, /rtp, buy, & more. Sotogawa’s spawn area generally has a snowy & icy theme. Something unique to CloudCraft SMP is the other regions you can travel to. Near spawn, there is a train that you can walk into and you can select a region to travel to. This is useful if you have a friend in another region. I hope you enjoy CloudCraft as much as I do. Cheers, Hydroweaponx (HoD) (Founding Soto Monarch)");

        }
        return true;
    }
}