package me.dandabs.commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ppdev implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            File userFile = new File("cloudconf" + File.separator + "users", player.getUniqueId().toString() + ".yml");
            YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);
            player.sendMessage("Developed By Sebastian Nesci And Olavi Koskinen ");


            return true;
        }}