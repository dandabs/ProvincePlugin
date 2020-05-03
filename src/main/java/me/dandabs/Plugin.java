package me.dandabs;

import me.dandabs.commands.*;
import me.dandabs.interfaces.RegionSelectionGUI;
import me.dandabs.interfaces.TrainSelectionGUI;
import me.dandabs.listeners.*;
import me.dandabs.placeholders.RegionExpansion;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

public class Plugin extends JavaPlugin {

    private static Plugin instance;

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }

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
        this.getCommand("lobby").setExecutor(new Lobby());
        this.getCommand("whereis").setExecutor(new Whereis());
        this.getCommand("territory").setExecutor(new Territory());
        this.getCommand("citizenship").setExecutor(new Citizenship());
        this.getCommand("wilderness").setExecutor(new Wilderness());
        this.getCommand("ppdev").setExecutor(new ppdev());
        this.getCommand("staffmode").setExecutor(new staffmode());
        this.getCommand("checkstaff").setExecutor(new checkstaff());

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.getInstance(), new Runnable() {

            @Override
            public void run() {

                Date date = new Date();
                DateFormat df = new SimpleDateFormat("hh:mmaa");

                df.setTimeZone(TimeZone.getTimeZone("Europe/Helsinki"));


                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "etime set " + df.format(date) + " world");

            }

        }, 1, 20 * 60 * 1);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this.getInstance(), new Runnable() {

            @Override
            public void run() {

                for (Player p : Bukkit.getServer().getOnlinePlayers()) {

                    File userFile = new File("cloudconf" + File.separator + "users", p.getUniqueId().toString() + ".yml");
                    YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

                    userConfig.set("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + LocalDateTime.now().getDayOfMonth(), userConfig.getInt("player.playtime." + LocalDateTime.now().getYear() + "." + LocalDateTime.now().getMonth() + "." + LocalDateTime.now().getDayOfMonth()) + 1);

                    try {
                        userConfig.save(userFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        }, 1, 20 * 60 * 1);

    }

    @Override
    public void onDisable() {

        log.info(String.format("[%s] Disabled Version %s", getDescription().getName(), getDescription().getVersion()));

    }

    public static Plugin getInstance() {
        return instance;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

}
