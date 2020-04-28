package me.dandabs.utilities;

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

                points += userConfig.getInt("player.points");

            }

        }

        return points;

    }

}
