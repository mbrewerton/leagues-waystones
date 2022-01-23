package com.leagueswaystones;

import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

public enum LeaguesWaystoneCoordinates {
    WAYSTONE_ARDOUGNE(2679, 3302, 0, "Ardougne"),
    WAYSTONE_ALKHARID(3296, 3183, 0, "Al Kharid"),
    WAYSTONE_BRIMHAVEN(2805, 3186, 0, "Brimhaven"),
    WAYSTONE_CANIFIS(3498, 3489, 0, "Canifis"),
    WAYSTONE_CATHERBY(2778, 3434, 0, "Catherby"),
    WAYSTONE_FALADOR(2968, 3385, 0, "Falador"),
    WAYSTONE_FEROX_ENCLAVE(3147, 3638, 0, "Ferox Enclave"),
    WAYSTONE_KOUREND(3677, 1663, 0, "Kourend"),
    WAYSTONE_LUMBRIDGE(3233, 3216, 0, "Lumbridge"),
//    WAYSTONE_PRIFDDINAS(12894, 8755, 1, "Prifddinas"), // TODO Get Coordinates
    WAYSTONE_RELLEKKA(2659, 3621, 0, "Rellekka"),
    WAYSTONE_VARROCK(3221, 3427, 0, "Varrock");

    private int x, y, z;
    private String name;
    LeaguesWaystoneCoordinates(int x, int y, int z, String name)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return 0;
    }

    public String getName() { return name; }

    public WorldMapPoint getWorldPoint() {
        WorldPoint worldPoint = new WorldPoint(getX(), getY(), getZ());
        BufferedImage waypointImage = new BufferedImage(35, 58, BufferedImage.TYPE_INT_ARGB);
        BufferedImage waystoneIcon = ImageUtil.loadImageResource(LeaguesWaystonesPlugin.class, "/waystone.png");
        waypointImage.getGraphics().drawImage(waystoneIcon, 0, 0, null);

        WorldMapPoint worldMapPoint = new WorldMapPoint(worldPoint, waypointImage);
        worldMapPoint.setName(getName());
        worldMapPoint.setJumpOnClick(true);

        return worldMapPoint;
    }
}
