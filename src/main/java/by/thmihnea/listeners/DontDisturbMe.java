package by.thmihnea.listeners;

import by.thmihnea.Util;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DontDisturbMe implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity entity = e.getEntity();
        Entity damager = e.getDamager();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(15)) return;
        if (!player.isSneaking()) return;
        e.setDamage(e.getDamage() * 0.5);
        if (damager instanceof Player)
            ((Player) damager).getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60, 0));
    }
}
