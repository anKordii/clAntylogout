package net.paulek.antylogout.utils;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedEntity;
import net.paulek.antylogout.data.DamagedSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GlobalScheduler implements Runnable {

    private static String combatmessage = Util.fix(Main.getPlugin().getConfig().getString("combat_message"));
    private static String safecombat = Util.fix(Main.getPlugin().getConfig().getString("combat_nolonger_message"));
    private static String nocombat = Util.fix(Main.getPlugin().getConfig().getString("no_longer_in_combat"));
    private static int endtime = Main.getGlobaltimecombat();

    public void run(){

        for(Player p : Bukkit.getOnlinePlayers()){

            if(DamagedSystem.isMarked(p)){

                DamagedEntity entity = DamagedSystem.getEntity(p);

                double timea = (System.currentTimeMillis() / 1000L) - entity.getTimestart();
                long time = endtime - (long)timea;

                if(entity.getPlayer() != null) CreateText.sendPacket(entity.getPlayer(), combatmessage.replace("{time}", Long.toString(time)));
                if(time <= 0){
                    if(entity.getPlayer() != null) CreateText.sendPacket(entity.getPlayer(), safecombat);
                    DamagedSystem.deleteDamaged(entity.getPlayer(), null);
                    DamagedSystem.deleteMarked(entity.getPlayer());
                    if(entity.getPlayer() != null) entity.getPlayer().sendMessage(nocombat);
                }

            }


        }


    }

}
