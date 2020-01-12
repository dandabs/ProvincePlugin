package me.dandabs.utilities;

import me.dandabs.Plugin;
import me.dandabs.statics.TrainLocations;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TrainSystem {

    public void boardTrain(Player p, Integer endtime, String destination) {

        p.teleport(TrainLocations.train());
        File trainFile = new File("cloudconf", "trains.yml");
        YamlConfiguration trainConfig = YamlConfiguration.loadConfiguration(trainFile);

        ArrayList<String> journeyinfo = new ArrayList<String>();
        journeyinfo.add(String.valueOf(System.currentTimeMillis() / 1000L));
        journeyinfo.add(endtime.toString());
        journeyinfo.add(destination);

        trainConfig.set("journeys." + p.getUniqueId().toString(), journeyinfo);

        try {
            trainConfig.save(trainFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer remainingTime = Math.toIntExact(endtime - (System.currentTimeMillis() / 1000L));

        BossBar bar = Bukkit.createBossBar("ETA at " + destination + " in " + remainingTime + "s.", BarColor.BLUE, BarStyle.SOLID);
        bar.addPlayer(p);
        bar.setVisible(true);
        bar.setProgress(0);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

            @Override
            public void run() {

                //if (trainConfig.isSet("journeys." + p.getUniqueId().toString())) return;

                int remainingTimeRunnable = Math.toIntExact(endtime - (System.currentTimeMillis() / 1000L));

                Double remainingTimePercentage;
                Double remainingTimeDecimal;

                if (remainingTime != 0) {


                    remainingTimePercentage = Double.valueOf((remainingTimeRunnable * 100) / remainingTime);
                    remainingTimeDecimal = remainingTimePercentage / 100;

                } else {

                    remainingTimePercentage = Double.valueOf(0);
                    remainingTimeDecimal = Double.valueOf(0);

                }

                if (remainingTimePercentage <= 0) {

                    if (!trainConfig.isSet("journeys." + p.getUniqueId().toString())) return;

                    p.teleport(TrainLocations.getDestination(destination));

                    bar.removeAll();

                    trainConfig.set("journeys." + p.getUniqueId().toString(), null);
                    try {
                        trainConfig.save(trainFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    bar.setVisible(false);

                } else {

                    bar.setTitle("ETA at " + destination + " in " + remainingTimeRunnable + "s.");
                    bar.setProgress(remainingTimeDecimal);

                    Bukkit.getServer().getLogger().info(remainingTimeDecimal.toString());

                }

            }
        }, 20L, 20L);

    }

    public boolean playerIsTravelling(Player p) {

        File trainFile = new File("cloudconf", "trains.yml");
        YamlConfiguration trainConfig = YamlConfiguration.loadConfiguration(trainFile);

        if (trainConfig.isSet("journeys." + p.getUniqueId().toString())) {

            return true;

        } else return false;

    }

}
