package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import com.cryptomorin.xseries.particles.XParticle;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.inventivetalent.particle.ParticleEffect;

public class CrashingWavesTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public CrashingWavesTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 40L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!Util.hasFullSet(this.player)) {
            this.clear();
        }
        for (int x = this.player.getLocation().getBlockX() - 2; x <= this.player.getLocation().getBlockX() + 3; x++)
            for (int z = this.player.getLocation().getBlockZ() - 2; z <= this.player.getLocation().getBlockZ() + 3; z++) {
                Location location = new Location(this.player.getWorld(), x, this.player.getLocation().getBlockY(), z);
                ParticleEffect.WATER_SPLASH.send(Bukkit.getOnlinePlayers(), location, 0, 0, 0, 0, 1);
            }
        this.player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                if (Util.hasFullSet(player) && Util.getHalfSetIDs(player).contains(11)) return;
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (!self.getFaction().equals(near.getFaction())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 0));
                }
            }
        });
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
