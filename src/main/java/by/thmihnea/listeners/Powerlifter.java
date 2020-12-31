package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.items.AzaleaArmor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Powerlifter implements Listener {

    @EventHandler (priority = EventPriority.NORMAL)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        Entity damager = e.getDamager();
        if (!(damager instanceof Player)) return;
        Player player = ((Player) damager).getPlayer();
        if (!Util.hasFullSet(player)) return;
        AzaleaArmor armor = Util.getArmor(player.getInventory().getHelmet());
        if (!armor.getObjectID().equals(6)) return;
        int cnt = (int) player.getNearbyEntities(5, 5, 5).stream().count();
        if (cnt >= 2) e.setDamage(e.getDamage() * 1.25);
    }
}
