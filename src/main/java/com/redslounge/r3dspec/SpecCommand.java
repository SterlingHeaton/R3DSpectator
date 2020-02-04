package com.redslounge.r3dspec;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@CommandAlias("spec")
public class SpecCommand extends BaseCommand
{
    @Dependency
    private Plugin plugin;
    private String chatTag = "&8[&4R3D&7Spec&8]";

    @Default
    @Syntax("<Player>")
    @CommandPermission("r3dspec.spec")
    @CommandCompletion("@players")
    public void onSpec(Player player, @Optional String playerName)
    {
        if(playerName == null)
        {
            if(plugin.getSpectators().containsKey(player))
            {
                cleanceEffects(player);
                return;
            }
            else
            {
                player.sendMessage(Utils.color(chatTag + " &cInvalid player entered, must be online!"));
                return;
            }
        }

        Player targetPlayer = plugin.getServer().getPlayer(playerName);

        if(targetPlayer == null)
        {
            player.sendMessage(Utils.color(chatTag + " &cInvalid player entered, must be online!"));
        }
        else if(plugin.getSpectators().containsKey(player))
        {
            applyEffects(player, targetPlayer);
        }
        else
        {
            SpecPlayer specPlayer = new SpecPlayer(player.getLocation(), player.getGameMode());
            plugin.getSpectators().put(player, specPlayer);
            applyEffects(player, targetPlayer);
        }
    }

    private void applyEffects(Player player, Player targetPlayer)
    {
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 1, true, false));
        player.setGameMode(GameMode.SPECTATOR);
        player.teleport(targetPlayer);
        player.sendMessage(Utils.color(chatTag + " &aNow watching &6" + targetPlayer.getName() + "&a!"));
        player.sendMessage(Utils.color(chatTag + " &aType &6/spec &ato return!"));
    }

    private void cleanceEffects(Player player)
    {
        SpecPlayer specPlayer = plugin.getSpectators().get(player);
        player.removePotionEffect(PotionEffectType.NIGHT_VISION);
        player.setGameMode(specPlayer.getGameMode());
        player.teleport(specPlayer.getLocation());
        player.sendMessage(Utils.color(chatTag + " &aYou have been returned!"));
        plugin.getSpectators().remove(player);
    }
}
