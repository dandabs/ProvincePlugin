package me.dandabs.misc;

import me.dandabs.Plugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class UserDataHandler {

    Plugin p;
    UUID u;
    File userFile;
    FileConfiguration userConfig;

    public UserDataHandler(UUID u){

        this.u = u;

        userFile = new File(p.getDataFolder(), "../../cloudconf/players/" + u + ".yml");

        userConfig = YamlConfiguration.loadConfiguration(userFile);

    }

    public void createUser(final Player player){

        if ( !(userFile.exists()) ) {
            try {


                YamlConfiguration UserConfig = YamlConfiguration.loadConfiguration(userFile);

                UserConfig.set("user.info.previousName", player.getName());
                UserConfig.set("user.info.uniqueID", player.getUniqueId().toString());
                UserConfig.set("user.info.ipAddress", player.getAddress().getAddress().getHostAddress());

                UserConfig.save(userFile);

            } catch (Exception e) {

                e.printStackTrace();

            }
        }
    }

    public FileConfiguration getUserFile(){

        return userConfig;

    }


    public void saveUserFile() {

        try {

            getUserFile().save(userFile);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
