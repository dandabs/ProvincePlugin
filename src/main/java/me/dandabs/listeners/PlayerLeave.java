package me.dandabs.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        int points = 0;

        Player player = (Player) event.getPlayer();

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
        YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

        int placed = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_blocks_placed%"));
        int broken = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_blocks_broken%"));
        int killed = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_players_killed%"));
        int died = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_deaths%"));
        int crafted = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_crafted_items%"));
        int trades = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_villager_trades%"));
        int mobs = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_mobs_killed%"));

        points += placed / 200;
        points -= broken / 100;
        points += killed * 5;
        points -= died * 5;
        points += crafted / 2;
        points += trades * 3;
        points += mobs * 2;

        userConfig.set("player.points", points);

        boolean staff = false;

        if (player.hasPermission("group.moderator")) staff = true;

        userConfig.set("player.isstaff", staff);

        try {
            userConfig.save(userFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        event.setQuitMessage(" §6{§c-§6} §6" + event.getPlayer().getName());

    }

}
