package net.paulek.antylogout.listeners;
import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.utils.CreateScheduler;
import net.paulek.antylogout.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerQuitEvent implements Listener {

    private boolean brogcastmessage = Main.getPlugin().getConfig().getBoolean("bc_combat_logout");
    private String combatlogout = Util.fix(Main.getPlugin().getConfig().getString("combat_logout"));

    @EventHandler
    public void onQuit(org.bukkit.event.player.PlayerQuitEvent event){
        if(DamagedSystem.isMarked(event.getPlayer())){
            DamagedSystem.deleteMarked(event.getPlayer());
            if(!DamagedSystem.isGlobalScheduler()) {
                CreateScheduler.cancelScheduler(CreateScheduler.getList().get(event.getPlayer().getUniqueId()));
                CreateScheduler.getList().remove(event.getPlayer().getUniqueId());
            }
            if(brogcastmessage){
                Bukkit.broadcastMessage(combatlogout.replace("{nick}", event.getPlayer().getDisplayName())
                        .replace("{health}", "❤"+(int)event.getPlayer().getHealth()+"/"+(int)event.getPlayer().getMaxHealth()));
            }
            event.getPlayer().setHealth(0.0F);
        }
    }
}
