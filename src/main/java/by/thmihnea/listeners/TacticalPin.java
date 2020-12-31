package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class TacticalPin implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(17)) return;
        int chance = 20;
        int hash = ThreadLocalRandom.current().nextInt(100);
        if (hash <= chance) {
            Entity toRoot = e.getEntity();
            if (!(toRoot instanceof Player)) return;
            Player root = ((Player) toRoot).getPlayer();
            ItemizedPlayer toRootItemized = ItemizedPlayerManager.getItemizedPlayer(root);
            toRootItemized.setRooted(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
               toRootItemized.setRooted(false);
            }, 20L);
        }
    }
}
