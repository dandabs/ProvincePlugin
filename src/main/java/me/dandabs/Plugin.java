package me.dandabs;

import me.dandabs.commands.*;
import me.dandabs.interfaces.RegionSelectionGUI;
import me.dandabs.interfaces.TrainSelectionGUI;
import me.dandabs.listeners.*;
import me.dandabs.placeholders.RegionExpansion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Plugin extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {

        instance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            new RegionExpansion(this).register();

        }

        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        //getServer().getPluginManager().registerEvents(new BlockRedstone(), this);
        getServer().getPluginManager().registerEvents(new EntityDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleport(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new RegionSelectionGUI(), this);
        getServer().getPluginManager().registerEvents(new TrainSelectionGUI(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new RegionEnter(), this);

        this.getCommand("pd").setExecutor(new PD());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("whereis").setExecutor(new Whereis());
        this.getCommand("territory").setExecutor(new Territory());
        this.getCommand("citizenship").setExecutor(new Citizenship());
        this.getCommand("wilderness").setExecutor(new Wilderness());
        this.getCommand("ppdev").setExecutor(new ppdev());
        this.getCommand("staff").setExecutor(new staff());
        this.getCommand("sotohelp").setExecutor(new sotohelp());
        this.getCommand("kodohelp").setExecutor(new kodohelp());
        this.getCommand("shokohelp").setExecutor(new shokohelp());
        this.getCommand("mekahelp").setExecutor(new mekahelp());
        this.getCommand("cloudcraft").setExecutor(new cloudcraft());



        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.getInstance(), new Runnable() {

            @Override
            public void run() {

                Date date = new Date();
                DateFormat df = new SimpleDateFormat("hh:mmaa");

                df.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));


                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "etime set " + df.format(date) + " world");

            }

        }, 1, 20 * 60 * 1);

    }

    @Override
    public void onDisable() {


    }

    public static Plugin getInstance() {
        return instance;
    }

}
