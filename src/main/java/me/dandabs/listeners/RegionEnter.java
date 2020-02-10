package me.dandabs.listeners;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import me.dandabs.interfaces.TrainSelectionGUI;
import net.raidstone.wgevents.events.RegionsEnteredEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.util.ArrayList;

public class RegionEnter implements Listener {

    @EventHandler
    public void onRegionEntered(RegionsEnteredEvent event) {

        Player player = event.getPlayer();

        if (event.getRegionsNames().contains("kodoresu-trainstationdoors") || event.getRegionsNames().contains("train-1") || event.getRegionsNames().contains("train-2") || event.getRegionsNames().contains("train-3") || event.getRegionsNames().contains("train-4") || event.getRegionsNames().contains("train-5")) {
            new TrainSelectionGUI().openInventory(event.getPlayer());
        }

        File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

        ArrayList<String> rgs = (ArrayList<String>) userConfig.getStringList("player.nationalities");

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        for (ProtectedRegion r : event.getRegions()) {

            if (r.getId().equals("kodoresu") || r.getId().equals("mekakushi") || r.getId().equals("shoko") || r.getId().equals("sotogawa")) {

                if (!userConfig.getStringList("player.nationalities").contains(r.getId())) {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.enterwarning")));

                }
            }

        }

    }

}
