package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class ThePowerOfTwoTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public ThePowerOfTwoTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!(this.player.isOnline())) {
            this.clear();
            return;
        }
        if (!Util.getHalfSetIDs(this.player).contains(18)) {
            return;
        }
        FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
        this.player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (!near.getFaction().equals(self.getFaction()))
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 60, 0));
            }
        });
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 0));
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
