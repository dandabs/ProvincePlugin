package me.dandabs.utilities;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Set;
import java.util.UUID;

public class RegionGetters {

    private Set<ProtectedRegion> getRegionsInPlayer(Player player) {

        Location location = player.getLocation();
        World world = player.getWorld();

        com.sk89q.worldedit.world.World weWorld = new BukkitWorld(world);
        com.sk89q.worldedit.util.Location weLocation = BukkitAdapter.adapt(location);

        BlockVector3 blockvector = BukkitAdapter.asBlockVector(location);

        LocalPlayer localplayer = WorldGuardPlugin.inst().wrapPlayer(player);

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(weWorld);

        ApplicableRegionSet applicableRegions = regions.getApplicableRegions(blockvector);

        Set<ProtectedRegion> entryRegions = applicableRegions.getRegions();

        return entryRegions;

    }

    public String whichRegion(Player player) {

        Set<ProtectedRegion> rgs = getRegionsInPlayer(player);

        for (ProtectedRegion rg : rgs) {

            if (rg.getId().contains("kodoresu")) {

                return "kodoresu";

            } else if (rg.getId().contains("shoko")) {

                return "shoko";

            } else if (rg.getId().contains("sotogawa")) {

                return "sotogawa";

            } else if (rg.getId().contains("mekakushi")) {

                return "mekakushi";

            }

        }

        return "wasteland";

    }

    public String whichMonarchName(String region) {

        File regionFile = new File("cloudconf", "regions.yml");
        YamlConfiguration regionConfig = YamlConfiguration.loadConfiguration(regionFile);

        UUID monarch = UUID.fromString(regionConfig.getString("regions." + region + ".monarch"));

        return Bukkit.getOfflinePlayer(monarch).getName();

    }

    public UUID whichMonarchId(String region) {

        File regionFile = new File("cloudconf", "regions.yml");
        YamlConfiguration regionConfig = YamlConfiguration.loadConfiguration(regionFile);

        UUID monarch = UUID.fromString(regionConfig.getString("regions." + region + ".monarch"));

        return monarch;

    }

}
