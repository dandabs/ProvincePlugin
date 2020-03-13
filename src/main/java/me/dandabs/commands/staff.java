package me.dandabs.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class staff implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
            player.sendMessage("Current Staff On The CloudCraft Team: xOchikonde_, (Owner) Hydroweaponx, (HoD) Alve, (HoS) Alyssa, (Mod) Chop, (Admin) Dave, (Admin) DODO, (Builder) FPS, (Builder) frithiof, (Mod) Fabi110, (Mod) Lev, (Admin) MWstudio, (Admin) Valxteri, (Co-Owner), VS, (Admin)");

        }
        return true;
    }
}