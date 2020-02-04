package com.redslounge.r3dspec;

import co.aikar.commands.BukkitCommandManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class Plugin extends JavaPlugin
{
    private BukkitCommandManager commandManager;
    private HashMap<Player, SpecPlayer> spectators = new HashMap<>();

    @Override
    public void onEnable()
    {
        commandManager = new BukkitCommandManager(this);

        setupCommands();
        setupEvnets();
    }

    @Override
    public void onDisable()
    {
        for(Player player : spectators.keySet())
        {
            SpecPlayer specPlayer = spectators.get(player);
            String chatTag = "&8[&4R3D&7Spec&8]";
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.setGameMode(specPlayer.getGameMode());
            player.teleport(specPlayer.getLocation());
            player.sendMessage(Utils.color(chatTag + " &aYou have been returned!"));
            spectators.remove(player);
        }
    }

    private void setupCommands()
    {
        commandManager.registerCommand(new SpecCommand());
    }

    private void setupEvnets()
    {
        getServer().getPluginManager().registerEvents(new SpecEvents(this), this);
    }

    public HashMap<Player, SpecPlayer> getSpectators()
    {
        return spectators;
    }
}
