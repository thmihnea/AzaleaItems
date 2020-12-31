package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import com.cryptomorin.xseries.particles.XParticle;
import com.massivecraft.factions.util.particle.ParticleColor;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class SandstormTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public SandstormTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        if (!Util.getHalfSetIDs(this.player).contains(24)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        circle(this.player, 2);
        this.player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (!(entity instanceof LivingEntity)) return;
            LivingEntity living = (LivingEntity) entity;
            living.damage(attributedPlayer.getDamage() / 10);
            living.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSandstorm &7has damaged you for " + (attributedPlayer.getDamage()/10) + " &cHP&7."));
        });
    }

    public void circle(Player player, double radius) {
        Location location = player.getLocation().subtract(0, 5, 0);
        new BukkitRunnable() {
            double t = 0;
            double p = Math.PI;
            public void run() {
                t += Math.PI/20;
                p += Math.PI/20;
                double x = radius * Math.cos(t);
                double y = t;
                double z = radius * Math.sin(t);
                double x2 = radius * Math.cos(p);
                double y2 = p;
                double z2 = radius * Math.sin(p);
                location.add(x, y, z);
                ParticleEffect.FLAME.send(Bukkit.getOnlinePlayers(), location, 0, 0, 0, 0, 1);
                location.subtract(x, y, z);
                location.add(z2, y2, x2);
                ParticleEffect.FLAME.send(Bukkit.getOnlinePlayers(), location, 0, 0, 0, 0, 1);
                location.subtract(z2, y2, x2);
                if (t > Math.PI * 4)
                    this.cancel();
            }
        }.runTaskTimer(AzaleaItems.getInstance(), 0, 1);
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }

}
