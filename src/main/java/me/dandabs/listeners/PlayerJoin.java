package me.dandabs.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.dandabs.Plugin;
import me.dandabs.misc.UserDataHandler;
import me.dandabs.utilities.RegionGetters;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        //Bukkit.broadcastMessage(new RegionGetters().getRegionsInPlayer(player).toString());

        UUID u = player.getUniqueId();

        File userFile = new File("cloudconf" + File.separator + "users", u + ".yml");

        event.setJoinMessage(" §6{§c+§6} §6" + event.getPlayer().getName());

        if (!userFile.exists()) {

            //event.setJoinMessage("");

            /*if (!player.getName().contains("dandabs")) {

                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
                userConfig.set("player.uuid", player.getUniqueId().toString());
                userConfig.set("player.startingregion", "kodoresu");

                try {
                    userConfig.save(userFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + player.getName() + " permission set group.kodoresu");


            } else {*/


                //YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
                //userConfig.set("player.uuid", player.getUniqueId().toString());
                //userConfig.set("player.startingregion", "kodoresu");

                /*try {
                    //userConfig.save(userFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                new me.dandabs.interfaces.RegionSelectionGUI().openInventory(player);

                //LuckPerms.getApi().getUserManager().getUser(u).setPermission(LuckPerms.getApi().buildNode("group.kodoresu").build());
                //Plugin.getInstance().getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + player.getName() + " permission set group.kodoresu");

           // }



        } else {



        }

        }


    }
