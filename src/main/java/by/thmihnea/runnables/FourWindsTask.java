package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class FourWindsTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public FourWindsTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 200L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!Util.hasFullSet(this.player)) {
            this.clear();
            return;
        }
        FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
        this.player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            boolean emitWave = false;
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (!near.getFaction().equals(self.getFaction()))
                    emitWave = true;
            } else emitWave = true;
            if (emitWave) {
                entity.setVelocity(entity.getLocation().getDirection().multiply(-1.2).setY(1));
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
