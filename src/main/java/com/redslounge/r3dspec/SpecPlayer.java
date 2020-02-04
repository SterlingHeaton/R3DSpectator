package com.redslounge.r3dspec;

import org.bukkit.GameMode;
import org.bukkit.Location;

public class SpecPlayer
{
    private Location location;
    private GameMode gameMode;

    public SpecPlayer(Location location, GameMode gameMode)
    {
        this.location = location;
        this.gameMode = gameMode;
    }

    public Location getLocation()
    {
        return location;
    }

    public GameMode getGameMode()
    {
        return gameMode;
    }
}
