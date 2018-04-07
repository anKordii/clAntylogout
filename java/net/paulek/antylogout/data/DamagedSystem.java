package net.paulek.antylogout.data;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.utils.CreateScheduler;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DamagedSystem {

    private static List<Player> marked = new ArrayList<Player>();
    private static HashMap<Player, DamagedEntity> damaged = new HashMap<Player, DamagedEntity>();
    private static boolean globalScheduler = true;


    public static List<Player> getMarked() {
        return marked;
    }

    public static void setMarked(List<Player> marked) {
        DamagedSystem.marked = marked;
    }
    public static void  addMarked(Player player){
        marked.add(player);
    }
    public static void deleteMarked(Player player){
        marked.remove(player);
    }
    public static boolean isMarked(Player player){
        if(marked.contains(player)){
            return true;
        } else {
            return false;
        }
    }

    public static HashMap<Player, DamagedEntity> getDamaged() {
        return damaged;
    }

    public static void setDamaged(HashMap<Player, DamagedEntity> damaged) {
        DamagedSystem.damaged = damaged;
    }

    public static void addDamaged(Player player, Player damager){
        if(damager != null) {
            damaged.put(player, new DamagedEntity(player, damager));
            damaged.put(damager, new DamagedEntity(damager, null));
            addMarked(player);
            addMarked(damager);
            if(!globalScheduler) {
                CreateScheduler.create(player, Main.getGlobaltimecombat());
                CreateScheduler.create(damager, Main.getGlobaltimecombat());
            }
        }
        if(damager == null){
            damaged.put(player, new DamagedEntity(player, null));
            addMarked(player);
            if(!globalScheduler) CreateScheduler.create(player, 20);
        }
    }
    public static void deleteDamaged(Player player, Player damager){
        if(damager != null) {
            damaged.remove(player);
            damaged.remove(damager);
            deleteMarked(player);
            deleteMarked(damager);
        }
        if(damager == null){
            damaged.remove(player);
            deleteMarked(player);
        }
    }

    public static DamagedEntity getEntity(Player player){
        if(damaged.containsKey(player)){
            return damaged.get(player);
        } else {
            return null;
        }
    }

    public static boolean isDamaged(Player player){
        if(damaged.containsKey(player)){
            return true;
        } else {
            return false;
        }
        //return false;
    }

    public static void setGlobalScheduler(boolean globalScheduler) {
        DamagedSystem.globalScheduler = globalScheduler;
    }

    public static boolean isGlobalScheduler() {
        return globalScheduler;
    }
}