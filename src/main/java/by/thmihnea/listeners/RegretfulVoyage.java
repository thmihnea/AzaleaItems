package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public class RegretfulVoyage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof Player)) return;
        Player damager = ((Player) e.getDamager()).getPlayer();
        Player damagee = ((Player) e.getEntity()).getPlayer();
        if (!Util.getHalfSetIDs(damagee).contains(26)) return;
        int chance = 20;
        int hash = ThreadLocalRandom.current().nextInt(100);
        if (hash <= chance) {
            ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(damager);
            itemizedPlayer.setRooted(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                itemizedPlayer.setRooted(false);
            }, 40L);
        }
        if (!Util.hasFullSet(damagee)) return;
        damager.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
        damager.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 40, 0));
        damager.playEffect(EntityEffect.HURT);
        /*Location shake1 = damager.getLocation();
        shake1.setPitch(damager.getLocation().getPitch() - 1);

        Location shake2 = damager.getLocation();
        shake2.setPitch(damager.getLocation().getPitch() + 1);

        Location shake3 = damager.getLocation();
        shake3.setPitch(damager.getLocation().getPitch() - 1);
        shake3.setYaw(damager.getLocation().getYaw() + 1);

        Location shake4 = damager.getLocation();
        shake4.setPitch(damager.getLocation().getPitch());
        shake4.setYaw(damager.getLocation().getYaw() - 1);

        Location shake5 = damager.getLocation();
        shake5.setYaw(damager.getLocation().getYaw() + 1);

        Location shake0 = damager.getLocation();
        damager.teleport(shake1);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            damager.teleport(shake2);
            Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                damager.teleport(shake3);
                Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                    damager.teleport(shake4);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                        damager.teleport(shake5);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                            damager.teleport(shake0);
                        }, 4L);
                    }, 4L);
                }, 4L);
            }, 4L);
        }, 4L);*/ // TODO: Try to find a way of doing it via locations but it is buggy if we teleport the player back to the same location since he can't move!
    }
}
