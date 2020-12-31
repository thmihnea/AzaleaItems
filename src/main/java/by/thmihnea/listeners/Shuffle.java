package by.thmihnea.listeners;

import by.thmihnea.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.concurrent.ThreadLocalRandom;

public class Shuffle implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = ((Player) e.getEntity()).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(30)) return;
        int roll = ThreadLocalRandom.current().nextInt(3) + 1;
        double reduction = roll * 0.1;
        e.setDamage(e.getDamage() * (1 - reduction));
    }
}
