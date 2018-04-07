package net.paulek.antylogout.utils;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedEntity;
import net.paulek.antylogout.data.DamagedSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CreateScheduler {

    private static String combatmessage = Util.fix(Main.getPlugin().getConfig().getString("combat_message"));
    private static String safecombat = Util.fix(Main.getPlugin().getConfig().getString("combat_nolonger_message"));
    private static String nocombat = Util.fix(Main.getPlugin().getConfig().getString("no_longer_in_combat"));

    private static HashMap<UUID, Integer> list = new HashMap<UUID, Integer>();

    public static void create(final Player player, final long endtime) {
        int id;
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            public void run() {
                DamagedEntity entity = DamagedSystem.getEntity(player);

                double timea = (System.currentTimeMillis() / 1000L) - entity.getTimestart();
                long time = endtime - (long)timea;

                if(entity.getPlayer() != null) CreateText.sendPacket(entity.getPlayer(), combatmessage.replace("{time}", Long.toString(time)));
                if(time <= 0){
                    if(entity.getPlayer() != null) CreateText.sendPacket(entity.getPlayer(), safecombat);
                    cancelScheduler(list.get(entity.getUuid()));
                    list.remove(entity.getUuid());
                    DamagedSystem.deleteDamaged(entity.getPlayer(), null);
                    DamagedSystem.deleteMarked(entity.getPlayer());
                    if(entity.getPlayer() != null) entity.getPlayer().sendMessage(nocombat);
                }
            }
        }, 20, 20);
        list.put(player.getUniqueId(), id);
    }

    public static void cancelScheduler(int id){
        Bukkit.getScheduler().cancelTask(id);
    }

    public static HashMap<UUID, Integer> getList() {
        return list;
    }
}
