package by.thmihnea.listeners;

import by.thmihnea.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.concurrent.ThreadLocalRandom;

public class SandVeil implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = ((Player) e.getEntity()).getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(24)) return;
        int chance = 30;
        int hash = ThreadLocalRandom.current().nextInt(100);
        if (hash <= chance) {
            e.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSand Veil &7dodged an attack for you."));
        }
    }
}
