package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.Util;
import by.thmihnea.items.AzaleaArmor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NaturesWrathListener implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.isCancelled()) return;
        Entity damager = e.getDamager();
        if (!(damager instanceof Player)) return;
        Player player = ((Player) damager).getPlayer();
        if (Util.hasFullSet(player)) {
            AzaleaArmor armor = Util.getArmor(player.getInventory().getHelmet());
            if (armor.getObjectID().equals(1)) {
                int chance = 20;
                Random random = ThreadLocalRandom.current();
                int hash = random.nextInt(100);
                if (hash > chance) return;
                AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
                e.setDamage(attributedPlayer.getDamage());
            }
        }
        System.out.println(e.getDamage());
    }
}
