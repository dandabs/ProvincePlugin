package me.dandabs.statics;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class RegionLocations {

    public static Location getKodoresuSpawn() {

        Location loc = new Location(Bukkit.getWorld("world"), 2214.500, 66.000, -30033.500);

        loc.setPitch((float) 4.9);
        loc.setYaw((float) 135.0);

        return loc;

    }

    public static Location getSotogawaSpawn() {

        Location loc = new Location(Bukkit.getWorld("world"), 2868.500, 67.000, -18816.500);

        loc.setPitch((float) 12.1);
        loc.setYaw((float) -179.9);

        return loc;

    }

    public static Location getMekakushiSpawn() {

        Location loc = new Location(Bukkit.getWorld("world"), -49939.500, 65.000, 1198.500);

        loc.setPitch((float) 0.75);
        loc.setYaw((float) 179.852);

        return loc;

    }

    public static Location getShokoSpawn() {

        Location loc = new Location(Bukkit.getWorld("world"), -1099.500, 66.000, -45790.500);

        loc.setPitch((float) 6);
        loc.setYaw((float) 0.331);

        return loc;

    }

    public static Location getKotonaruSpawn() {

        Location loc = new Location(Bukkit.getWorld("world"), -49939.500, 65.000, 1198.500);

        loc.setPitch((float) 11.175);
        loc.setYaw((float) 180.031);

        return loc;

    }

}
