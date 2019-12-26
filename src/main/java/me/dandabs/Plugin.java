package me.dandabs;

import me.dandabs.commands.PD;
import me.dandabs.commands.Spawn;
import me.dandabs.commands.Whereis;
import me.dandabs.interfaces.RegionSelectionGUI;
import me.dandabs.listeners.InventoryClose;
import me.dandabs.listeners.PlayerJoin;
import me.dandabs.listeners.PlayerLeave;
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

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new RegionSelectionGUI(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);

        this.getCommand("pd").setExecutor(new PD());
        this.getCommand("spawn").setExecutor(new Spawn());
        this.getCommand("whereis").setExecutor(new Whereis());

    }

    @Override
    public void onDisable() {


    }

    public static Plugin getInstance() {
        return instance;
    }

}
