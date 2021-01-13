package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.inventivetalent.particle.ParticleEffect;

public class BleedingTask implements Runnable {

    private Player player;
    private long endTime;
    private long seconds;
    private int damage;
    private BukkitTask task;

    public BleedingTask(Player player, int damage, long seconds) {
        this.seconds = seconds;
        this.player = player;
        this.damage = damage;
        this.endTime = System.currentTimeMillis() + (seconds * 1000);
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
    }

    @Override
    public void run() {
        if (isOver()) {
            clear();
        }
        ParticleEffect.REDSTONE.send(Bukkit.getOnlinePlayers(), this.player.getEyeLocation().clone().add(0, -0.1, 0), 0, 0, 0, 1, 3);
        player.damage(this.damage);
    }

    public void resetTime() {
        this.endTime = System.currentTimeMillis() + (this.seconds * 1000);
    }

    public void subtractEndTime(long amount) {
        this.endTime -= amount;
    }

    public long timeLeft() {
        return (endTime - System.currentTimeMillis());
    }

    public long timeLeftInSeconds() {
        return timeLeft() / 1000;
    }

    public boolean isOver() {
        return System.currentTimeMillis() >= endTime;
    }

    public void extendTime(long seconds) {
        this.endTime = endTime + (seconds * 1000);
    }

    public void clear() {
        if (task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
