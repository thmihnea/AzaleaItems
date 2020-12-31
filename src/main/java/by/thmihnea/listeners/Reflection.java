package by.thmihnea.listeners;

import by.thmihnea.Util;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Objects;

public class Reflection implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(e.getDamager() instanceof LivingEntity)) return;
        LivingEntity livingEntity = (LivingEntity) e.getDamager();
        if (!Util.getHalfSetIDs(Objects.requireNonNull(((Player) e.getEntity()).getPlayer())).contains(29)) return;
        livingEntity.damage(e.getDamage() * 0.1);
    }
}
