package by.thmihnea.cooldown;

import by.thmihnea.AzaleaItems;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Cooldown implements Runnable {

    private Player player;
    private CooldownType cooldownType;
    private long endTime;
    private long seconds;
    private BukkitTask task;

    public Cooldown(Player player, CooldownType cooldownType, long seconds) {
        this.seconds = seconds;
        this.player = player;
        this.cooldownType = cooldownType;
        this.endTime = System.currentTimeMillis() + (seconds * 1000);
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
    }

    @Override
    public void run() {
        if (isOver()) {
            clear();
        }
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
            ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(this.player);
            itemizedPlayer.removeCooldown(this);
            task = null;
        }
    }

    public CooldownType getCooldownType() {
        return this.cooldownType;
    }
}
