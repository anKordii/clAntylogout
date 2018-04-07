package net.paulek.antylogout;

import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.listeners.*;
import net.paulek.antylogout.utils.GlobalScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;
    private static int globaltimecombat = 20;

    @Override
    public void onEnable(){
        plugin = this;

        getPlugin().getLogger().info("Plugin evends initializing....");
        this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerCommandPreprocessEvent(), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakEvent(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathEvent(), this);

        getPlugin().getLogger().info("Plugin system initializing....");
        if(this.getConfig().getString("scheduler").equalsIgnoreCase("global")){
            Bukkit.getScheduler().runTaskTimer(this, new GlobalScheduler(), 20, 20);
            getPlugin().getLogger().info("Scheduler type changed to GLOBAL type.");
        } else if(this.getConfig().getString("scheduler").equalsIgnoreCase("single")){
            DamagedSystem.setGlobalScheduler(false);
            getPlugin().getLogger().info("Scheduler type changed to SINGLE type.");
        } else {
            Bukkit.getScheduler().runTaskTimer(this, new GlobalScheduler(), 20, 20);
            getPlugin().getLogger().warning("Scheduler type not recognized! Changing to GLOBAL type.");
        }

        globaltimecombat = this.getConfig().getInt("time");

        getPlugin().getLogger().info("Plugin ready!");

        getPlugin().getLogger().info("Plugin author: PaulekOfficial");


        saveDefaultConfig();
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static int getGlobaltimecombat() {
        return globaltimecombat;
    }
}
