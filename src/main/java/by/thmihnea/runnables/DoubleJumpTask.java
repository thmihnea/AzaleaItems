package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.CooldownType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class DoubleJumpTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public DoubleJumpTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        if (this.player.getGameMode() == GameMode.CREATIVE) {
            this.player.setAllowFlight(true);
            return;
        }
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.FLUTTER)) return;
        if (Util.getHalfSetIDs(this.player).contains(7)) this.player.setAllowFlight(true);
        else this.player.setAllowFlight(false);
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
