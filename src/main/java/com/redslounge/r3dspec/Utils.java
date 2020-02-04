package com.redslounge.r3dspec;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils
{
    public static String color(String color)
    {
        return ChatColor.translateAlternateColorCodes('&', color);
    }

    public static void bugTest(String message)
    {
        Bukkit.broadcastMessage(Utils.color("&8[&4BugTest&8]" + "&7 " + message));
    }

    public static String buildMessage(String[] parts, int start)
    {
        StringBuilder note = new StringBuilder();
        for (int count = start; count < parts.length; count++)
        {
            note.append(parts[count]).append(" ");
        }
        return note.toString();
    }
}
