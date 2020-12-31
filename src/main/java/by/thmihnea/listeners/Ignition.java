package by.thmihnea.listeners;

import by.thmihnea.Util;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.concurrent.ThreadLocalRandom;

public class Ignition implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!e.getCause().equals(EntityDamageEvent.DamageCause.PROJECTILE)) return;
        Entity damaged = e.getEntity();
        if (!(damaged instanceof Player)) return;
        Player player = ((Player) damaged).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(14)) return;
        int chance = 25;
        int hash = ThreadLocalRandom.current().nextInt(100);
        if (hash <= chance) e.setCancelled(true);
    }
}
