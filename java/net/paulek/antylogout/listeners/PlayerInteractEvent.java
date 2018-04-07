package net.paulek.antylogout.listeners;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.utils.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

public class PlayerInteractEvent implements Listener {

    private String message = Util.fix(Main.getPlugin().getConfig().getString("cant_open"));

    @EventHandler
    public void onInteract(org.bukkit.event.player.PlayerInteractEvent event){
        Player player = event.getPlayer();
        if((((event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType() == Material.ENDER_CHEST)) || (((event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getClickedBlock().getType() == Material.ENDER_CHEST))
                || (((event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType() == Material.CHEST)) || (((event.getAction() == Action.LEFT_CLICK_BLOCK) && event.getClickedBlock().getType() == Material.CHEST))) {
            if (DamagedSystem.isMarked(player)) {
                player.sendMessage(message);
                event.setCancelled(true);
            }
        }
    }
}
