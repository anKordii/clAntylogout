package net.paulek.antylogout.listeners;

import net.paulek.antylogout.Main;
import net.paulek.antylogout.data.DamagedSystem;
import net.paulek.antylogout.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EntityDamageByEntityEvent implements Listener{

    private boolean cleativecombat = Main.getPlugin().getConfig().getBoolean("creative_damage");
    private String creativecombat_off = Util.fix(Main.getPlugin().getConfig().getString("creative_damage"));

    @EventHandler
    public void onDamage(org.bukkit.event.entity.EntityDamageByEntityEvent event) {
        Entity attack = event.getDamager();
        Entity damaged = event.getEntity();

        if (!cleativecombat) {
            if (attack instanceof Player) {
                Player damager = (Player) attack;
                if (damager.getGameMode() == GameMode.CREATIVE) {
                    damager.sendMessage(creativecombat_off);
                    event.setCancelled(true);
                }
            }

        }
        if (!cleativecombat) {
            if((damaged instanceof Player) && (attack instanceof Monster)){
                Player player = (Player)damaged;
                if (DamagedSystem.isDamaged(player)) {
                    DamagedSystem.getDamaged().get(player).setTimestart(System.currentTimeMillis() / 1000L);
                    return;
                }
                DamagedSystem.addDamaged(player, null);
            }

            if ((attack instanceof Player) && (damaged instanceof Player)) {
                Player player = (Player) damaged;
                Player damager = (Player) attack;
                if (DamagedSystem.isDamaged(player) && DamagedSystem.isDamaged(damager)) {
                    DamagedSystem.getDamaged().get(player).setTimestart(System.currentTimeMillis() / 1000L);
                    DamagedSystem.getDamaged().get(damager).setTimestart(System.currentTimeMillis() / 1000L);
                    return;
                }

                DamagedSystem.addDamaged(player, damager);

            }
            if (((event.getDamager() instanceof Projectile)) && ((event.getEntity() instanceof Player)) &&
                    ((((Projectile) event.getDamager()).getShooter() instanceof Player))) {
                System.out.println("test");
                Player damage = null;
                Player shotted = null;
                Entity player = event.getEntity();
                Entity damager = event.getDamager();
                if (event.getDamager() instanceof Arrow) {
                    shotted = ((Player) event.getEntity()).getPlayer();
                    damage = (Player) ((Projectile) damager).getShooter();
                }
                if (event.getDamager() instanceof ThrownPotion) {
                    shotted = ((Player) event.getEntity()).getPlayer();
                    damage = (Player) ((Projectile) damager).getShooter();
                }
                if (event.getDamager() instanceof Snowball) {
                    shotted = ((Player) event.getEntity()).getPlayer();
                    damage = (Player) ((Projectile) damager).getShooter();
                }
                if (event.getDamager() instanceof Egg) {
                    shotted = ((Player) event.getEntity()).getPlayer();
                    damage = (Player) ((Projectile) damager).getShooter();
                }

                if (DamagedSystem.isDamaged(shotted) && DamagedSystem.isDamaged(damage)) {
                    DamagedSystem.getDamaged().get(damage).setTimestart(System.currentTimeMillis() / 1000L);
                    DamagedSystem.getDamaged().get(shotted).setTimestart(System.currentTimeMillis() / 1000L);
                    return;
                }

                DamagedSystem.addDamaged(shotted, damage);
                shotted.setLastDamage(0.5F);

            }
        }
    }

}
