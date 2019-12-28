package me.dandabs.listeners;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        //Bukkit.broadcastMessage(new RegionGetters().getRegionsInPlayer(player).toString());

        UUID u = player.getUniqueId();

        File userFile = new File("cloudconf" + File.separator + "users", u + ".yml");

        event.setJoinMessage(" §6{§c+§6} §6" + event.getPlayer().getName());

        if (!userFile.exists()) {

            new me.dandabs.interfaces.RegionSelectionGUI().openInventory(player);

        } else {

            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            if (!userConfig.contains("player.nationalities")) {

                ArrayList<String> userRegions = new ArrayList<String>();
                userRegions.add(userConfig.getString("player.startingregion"));
                userConfig.set("player.nationalities", userRegions);
                try {
                    userConfig.save(userFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        }


    }
