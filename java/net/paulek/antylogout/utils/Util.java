package net.paulek.antylogout.utils;

import org.bukkit.ChatColor;

public class Util {

    public static String fix(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
