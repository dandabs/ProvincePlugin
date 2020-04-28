package me.dandabs.utilities;

import me.clip.placeholderapi.PlaceholderAPI;
import me.dandabs.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class RegionPoints {

    public int calculatePoints(String region) {

        int points = 0;

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        File userDirectory = new File("cloudconf" + File.separator + "users");

        File[] users = userDirectory.listFiles();

        for (File userFile : users) {

            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

            String userRegion = userConfig.getString("player.startingregion");

            System.out.println(userRegion + " is userregion");
            System.out.println(region + " is region");

            if (region.equals(userRegion)) {

                System.out.println(userConfig.getString("player.uuid"));

                OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(userConfig.getString("player.uuid")));

                if (player.isOnline()) {

                } else {

                    if (!Plugin.getPermissions().playerHas("world", player, "provinceplugin.meta.staff")) {

                        int placed = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_blocks_placed%"));
                        int broken = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_blocks_broken%"));
                        int killed = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_players_killed%"));
                        int died = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_deaths%"));
                        int crafted = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_crafted_items%"));
                        int trades = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_villager_trades%"));
                        int mobs = Integer.parseInt(PlaceholderAPI.setPlaceholders(player, "%statz_mobs_killed%"));

                        points += placed;
                        points -= broken;
                        points += killed * 5;
                        points -= died * 5;
                        points += crafted / 2;
                        points += trades * 3;
                        points += mobs * 2;

                    }

                }

            }

        }

        return points;

    }

}
