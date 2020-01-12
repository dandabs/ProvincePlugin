package me.dandabs.statics;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class TrainLocations {

    private static Location makeLoc(Double x, Integer y, Double z, Double yaw, Double pitch) {

        Location loc = new Location(Bukkit.getWorld("world"), x, y, z);
        loc.setYaw(Float.valueOf(String.valueOf(yaw)));
        loc.setPitch(Float.valueOf(String.valueOf(pitch)));

        return loc;

    }

    public static Location train() {

        return makeLoc(-5706.131, 73, -41429.385, 358.965, 2.855);

    }

    public static Location getDestination(String destination) {

        if (destination.equals("ShokoCitySubway")) {

            return makeLoc(-1048.257, 40, -45720.378, 53.482, 11.757);

        } else if (destination.equals("KodoresuCityTrain")) {

            return makeLoc(2179.048, 66, -30174.699, 1.282, 3.402);

        } else {

            return RegionLocations.getKodoresuSpawn();

        }

    }

}
