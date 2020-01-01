package me.dandabs.listeners;

import me.dandabs.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Openable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.material.Door;

public class BlockRedstone implements Listener {

    @EventHandler
    public void onBlockRedstoneChange(BlockRedstoneEvent e) {

        Block b = e.getBlock();

        if (b.getType() == Material.IRON_DOOR) {

            BlockData bd = b.getBlockData();

            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Plugin.getInstance(), new Runnable() {

                public void run() {

                    for (BlockFace bf : BlockFace.values()) {

                        if (b.isBlockPowered() || b.getRelative(bf).isBlockPowered()) {

                            for (BlockFace bf3 : BlockFace.values()) {


                                Door d = (Door) b.getState().getData();

                                d.setOpen(false);

                                ((Openable) bd).setOpen(false);
                                b.setBlockData(bd);

                                //b.setType(Material.IRON_DOOR);

                                e.setNewCurrent(0);

                                Bukkit.getServer().getLogger().info("ya");


                            }

                        } else {

                            for (BlockFace bf2 : BlockFace.values()) {

                                if (b.getRelative(bf).getRelative(bf2).isBlockPowered()) {


                                    Door d = (Door) b.getState().getData();

                                    d.setOpen(false);

                                    ((Openable) bd).setOpen(false);
                                    b.setBlockData(bd);

                                    //b.setType(Material.IRON_DOOR);

                                    e.setNewCurrent(0);

                                    Bukkit.getServer().getLogger().info("ya2");


                                }

                            }

                        }

                    }

                }

            }, 0, 5);

        }

    }

}
