package me.dandabs.listeners;

import me.dandabs.utilities.RegionGetters;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.io.File;
import java.util.ArrayList;

public class PlayerTeleport implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e) {

        Player player = e.getPlayer();

        File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

        ArrayList<String> rgs = (ArrayList<String>) userConfig.getStringList("player.nationalities");

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        if (!rgs.contains(new RegionGetters().whichRegion(player))) {

            //player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.enterwarning")));

        }

    }
}
