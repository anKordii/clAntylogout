package net.paulek.antylogout.listeners;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.utils.CreateScheduler;
import net.paulek.antylogout.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerDeathEvent implements Listener {

    private String nocombat = Util.fix(Main.getPlugin().getConfig().getString("no_longer_in_combat"));

    @EventHandler
    public void onDeath(org.bukkit.event.entity.PlayerDeathEvent event){

        if(event.getEntity() instanceof Player) {
            Player player = event.getEntity().getPlayer();
            if (DamagedSystem.isMarked(player)){
                if(!DamagedSystem.isGlobalScheduler()) {
                    CreateScheduler.cancelScheduler(CreateScheduler.getList().get(player));
                    CreateScheduler.getList().remove(player);
                }
                DamagedSystem.deleteDamaged(player, null);
                DamagedSystem.deleteMarked(player);
                player.sendMessage(nocombat);
            }
        }
    }
}
