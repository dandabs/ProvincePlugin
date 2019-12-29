package me.dandabs.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Citizenship implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            File langFile = new File("cloudconf", "lang.yml");
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);

            if (args.length >= 1) {

                if (args.length >= 2) {

                    if (args[0].contains("check")) {

                        if (!player.hasPermission("provinceplugin.citizenship.check")) {

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.nopermission")));
                            return true;

                        }

                        if (args.length == 2) {

                            OfflinePlayer oplayer = Bukkit.getOfflinePlayer(args[1]);

                            File userFile = new File("cloudconf" + File.separator + "users", oplayer.getUniqueId().toString() + ".yml");

                            if (userFile.exists()) {

                                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

                                ArrayList<String> rgs = (ArrayList<String>) userConfig.getStringList("player.nationalities");

                                String regions = "";

                                for (String s : rgs) {

                                    regions = regions + s + ", ";

                                }

                                String message = ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.citizenshipcheck").replace("%regions%", regions.substring(0, regions.length() - 2)).replace("%player%", oplayer.getName()));

                                player.sendMessage(message);

                            } else {

                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.incorrectplayer")));

                            }

                        } else {

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));

                        }

                        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.showmonarch").replace("%region%", args[1]).replace("%monarch%", new RegionGetters().whichMonarchName(args[1]))));

                    }

                    if (args[0].contains("grant")) {

                        if (args.length == 3) {

                            OfflinePlayer oplayer = Bukkit.getOfflinePlayer(args[1]);

                            File userFile = new File("cloudconf" + File.separator + "users", oplayer.getUniqueId().toString() + ".yml");

                            if (userFile.exists()) {

                                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

                                ArrayList<String> rgs = (ArrayList<String>) userConfig.getStringList("player.nationalities");

                                if (args[2].equals("kodoresu") || args[2].equals("sotogawa") || args[2].equals("shoko") || args[2].equals("mekakushi") || args[2].equals("kotonaru")) {

                                    if (!player.hasPermission("provinceplugin.citizenship.grant." + args[2])) {

                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.nopermission")));
                                        return true;

                                    }

                                    String message = ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.citizenshipgranted").replace("%region%", args[2]).replace("%player%", oplayer.getName()));

                                    rgs.add(args[2]);

                                    userConfig.set("player.nationalities", rgs);

                                    try {
                                        userConfig.save(userFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    player.sendMessage(message);

                                } else {

                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.incorrectregion")));

                                }

                            } else {

                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.incorrectplayer")));

                            }

                        } else {

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));

                        }

                        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.showmonarch").replace("%region%", args[1]).replace("%monarch%", new RegionGetters().whichMonarchName(args[1]))));

                    }

                    if (args[0].contains("revoke")) {

                        if (args.length == 3) {

                            OfflinePlayer oplayer = Bukkit.getOfflinePlayer(args[1]);

                            File userFile = new File("cloudconf" + File.separator + "users", oplayer.getUniqueId().toString() + ".yml");

                            if (userFile.exists()) {

                                YamlConfiguration userConfig = YamlConfiguration.loadConfiguration(userFile);

                                ArrayList<String> rgs = (ArrayList<String>) userConfig.getStringList("player.nationalities");

                                if (args[2].equals("kodoresu") || args[2].equals("sotogawa") || args[2].equals("shoko") || args[2].equals("mekakushi") || args[2].equals("kotonaru")) {

                                    if (!player.hasPermission("provinceplugin.citizenship.revoke." + args[2])) {

                                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.nopermission")));
                                        return true;

                                    }

                                    String message = ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.citizenshiprevoked").replace("%region%", args[2]).replace("%player%", oplayer.getName()));

                                    rgs.remove(args[2]);

                                    userConfig.set("player.nationalities", rgs);

                                    try {
                                        userConfig.save(userFile);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                    player.sendMessage(message);

                                } else {

                                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.incorrectregion")));

                                }

                            } else {

                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.incorrectplayer")));

                            }

                        } else {

                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));

                        }

                        //player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.showmonarch").replace("%region%", args[1]).replace("%monarch%", new RegionGetters().whichMonarchName(args[1]))));

                    }

                } else {

                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));

                }


            } else {

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', langConfig.getString("provinceplugin.wrongargs")));

            }

        }

        return true;

    }

}
