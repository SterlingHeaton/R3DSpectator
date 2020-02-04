package com.redslounge.r3dspec;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class SpecEvents implements Listener
{
    private Plugin plugin;

    public SpecEvents(Plugin plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();

        if(plugin.getSpectators().containsKey(player))
        {
            SpecPlayer specPlayer = plugin.getSpectators().get(player);
            String chatTag = "&8[&4R3D&7Spec&8]";
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.setGameMode(specPlayer.getGameMode());
            player.teleport(specPlayer.getLocation());
            player.sendMessage(Utils.color(chatTag + " &aYou have been returned!"));
            plugin.getSpectators().remove(player);
        }
    }
}
