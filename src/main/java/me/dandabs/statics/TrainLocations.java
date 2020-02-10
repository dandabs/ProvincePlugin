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

        } else if (destination.equals("SotogawaCityTrain")) {

            return makeLoc(2860.408, 63, -18650.531, 17.85, 3.151);

        } else if (destination.equals("MekakushiCityMonorail")) {

            return makeLoc(-2832.527, 97, -16410.544, 180.27, 1.9);

        } else if (destination.equals("BallymenaReplicaTrain")) {

            return makeLoc(590.408, 88, -16142.387, 101.266, 7.906);

        } else {

            return RegionLocations.getKodoresuSpawn();

        }

    }

}
