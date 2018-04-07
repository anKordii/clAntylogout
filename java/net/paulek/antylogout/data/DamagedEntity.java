package net.paulek.antylogout.data;

import org.bukkit.entity.Player;

import java.util.UUID;

public final class DamagedEntity {

    private UUID uuid;
    private String nick;
    private Player player;
    private Player damager;
    private long timestart;

    public DamagedEntity(Player player, Player damager){
        this.player = player;
        this.damager = damager;
        uuid = player.getUniqueId();
        nick = player.getDisplayName();
        timestart = (System.currentTimeMillis() / 1000L);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getDamaged() {
        return damager;
    }

    public void setDamaged(Player damager) {
        this.damager = damager;
    }

    public double getTimestart() {
        return timestart;
    }

    public void setTimestart(long timestart) {
        this.timestart = timestart;
    }
}
