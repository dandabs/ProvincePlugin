package me.dandabs.listeners;

import me.dandabs.utilities.RegionGetters;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;

public class EntityDamage implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {

        if (e.getEntityType() == EntityType.PLAYER) {

            File userFile = new File("cloudconf" + File.separator + "users", ((Player) e.getEntity()).getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            if (new RegionGetters().whichRegion((Player) e.getEntity()).equals(userConfig.getString("player.startingregion"))) {

                e.setCancelled(true);

            }

        }

    }

}
