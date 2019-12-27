package me.dandabs.listeners;

import me.dandabs.statics.RegionLocations;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        File userFile = new File("cloudconf" + File.separator + "users", event.getPlayer().getUniqueId().toString() + ".yml");
        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

        String rg = userConfig.getString("player.startingregion");
        Player p = event.getPlayer();

        if (rg.contains("kodoresu")) {

            p.teleport(new RegionLocations().getKodoresuSpawn());

        }

        if (rg.contains("mekakushi")) {

            p.teleport(new RegionLocations().getMekakushiSpawn());

        }

        if (rg.contains("sotogawa")) {

            p.teleport(new RegionLocations().getSotogawaSpawn());

        }

        if (rg.contains("shoko")) {

            p.teleport(new RegionLocations().getShokoSpawn());

        }

        if (rg.contains("kotonaru")) {

            p.teleport(new RegionLocations().getKotonaruSpawn());

        }

    }

}
