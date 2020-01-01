package me.dandabs.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;

public class AsyncPlayerChat implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {

        File langFile = new File("cloudconf", "lang.yml");
        YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

        if (e.getMessage().contains("staff") || e.getMessage().contains("apply")) {

            e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.staffapply")));

        }

    }

}
