package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import com.cryptomorin.xseries.XSound;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.inventivetalent.particle.ParticleEffect;

public class JusticeTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public JusticeTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 1L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!(this.player.isOnline())) {
            this.clear();
            return;
        }
        ParticleEffect.VILLAGER_HAPPY.send(Bukkit.getOnlinePlayers(), this.player.getLocation(), 0, 0, 0, 1, 0);
        if (!this.player.isOnGround()) return;
        ParticleEffect.EXPLOSION_HUGE.send(Bukkit.getOnlinePlayers(), this.player.getLocation(), 0, 0, 0, 1, 0);
        XSound.play(this.player.getLocation(), "EXPLODE");
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
        this.player.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (!(entity instanceof LivingEntity)) return;
            if (entity instanceof Player) {
                Player player = (Player) entity;
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (self.getFaction().equals(near.getFaction())) return;
                player.damage(attributedPlayer.getDamage() * 2);
            } else ((LivingEntity) entity).damage(attributedPlayer.getDamage() * 2);
        });
        this.clear();
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
