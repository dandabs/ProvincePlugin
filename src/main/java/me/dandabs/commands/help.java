package me.dandabs.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
            player.sendMessage("How To Play CloudCraft: We are an SMP server with a difference: Monarchy SMP! You can play Monarchy SMP like normal SMP, (/claim, etc so make sure you claim your land!) however, there are multiple regions that you can select when you first join the server. They each have different unique atmospheres, architechture, and biomes. These include: Kodorest, Shoko, Sotogawa, and Mekakushi. Once you select your region for the first time, you can't change it. You can visit other regions by walking into your local regions train. But the most important feature of Monarchy SMP is our dedicated monarchs and our MPs. The current Sotogawa monarch is Hydroweaponx, the current Kodoresu Monarch is xOchikonde_, the current Mekakushi monarch is frithiof07, the current Shoko Monarch is Valxteri. We currently don't have any MPs. In the future a feature will be released where you can overthrow these monarchs, but currently there isn't one.");

        }
        return true;
    }
}