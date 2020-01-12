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

        Location loc = new Location(Bukkit.getWorld("world"), 2868.500, 67.000, -18616.500);

        loc.setPitch((float) 0.899);
        loc.setYaw((float) 180.444);

        return loc;

    }

    public static Location getMekakushiSpawn() {

        Location loc = new Location(Bukkit.getWorld("world"), -2843.500, 97.000, -16418.500);

        loc.setPitch((float) 0.148);
        loc.setYaw((float) 88.419);

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
