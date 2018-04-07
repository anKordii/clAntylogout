package net.paulek.antylogout.listeners;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerCommandPreprocessEvent implements Listener{

    private String message = Util.fix(Main.getPlugin().getConfig().getString("cant_command"));

    @EventHandler
    public void onCommand(org.bukkit.event.player.PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if(DamagedSystem.isMarked(player)){
            event.setCancelled(true);
            player.sendMessage(message);
        }
    }
}
