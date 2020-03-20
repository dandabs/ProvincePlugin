package me.dandabs.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class BlockRedstone implements Listener {

    @EventHandler
    public void onBlockRedstoneChange(BlockRedstoneEvent e) {

        Block b = e.getBlock();

        if (b.getType() == Material.IRON_DOOR) {

            BlockData bd = b.getBlockData();

        }

    }

}
