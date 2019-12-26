package me.dandabs.commands;

import me.dandabs.utilities.RegionGetters;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Whereis implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            String region = new RegionGetters().whichRegion(player);

            player.sendMessage(region);

        }

        return true;

    }

}
