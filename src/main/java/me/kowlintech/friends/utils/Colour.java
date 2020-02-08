package me.kowlintech.friends.utils;

import org.bukkit.ChatColor;

public class Colour {

    public static String translate(String toTranslate) {
        return ChatColor.translateAlternateColorCodes('&', toTranslate);
    }

    public static String prefix(ChatColor prefixColor) {
        return prefixColor + "Friends » ";
    }
}
