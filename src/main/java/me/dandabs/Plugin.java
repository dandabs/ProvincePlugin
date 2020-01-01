package me.dandabs;

import me.dandabs.commands.*;
import me.dandabs.interfaces.RegionSelectionGUI;
import me.dandabs.listeners.*;
import me.dandabs.placeholders.RegionExpansion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {

        instance = this;

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            new RegionExpansion(this).register();

        }

        getServer().getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleport(), this);
        getServer().getPluginManager().registerEvents(new RegionSelectionGUI(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new RegionEnter(), this);

        this.getCommand("pd").setExecutor(new PD());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("whereis").setExecutor(new Whereis());
        this.getCommand("territory").setExecutor(new Territory());
        this.getCommand("citizenship").setExecutor(new Citizenship());
        this.getCommand("wilderness").setExecutor(new Wilderness());

    }

    @Override
    public void onDisable() {


    }

    public static Plugin getInstance() {
        return instance;
    }

}
