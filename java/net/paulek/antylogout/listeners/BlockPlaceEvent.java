package net.paulek.antylogout.listeners;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockPlaceEvent implements Listener{

    private String message = Util.fix(Main.getPlugin().getConfig().getString("cant_place"));

    @EventHandler
    public void onPlace(org.bukkit.event.block.BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(DamagedSystem.isMarked(player)){
            player.sendMessage(message);
            event.setCancelled(true);
        }
    }
}
