package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class SpringBurstTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public SpringBurstTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        if (!Util.getHalfSetIDs(this.player).contains(27)) return;
        if (!this.player.isSprinting()) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        if (this.player.getHealth() > attributedPlayer.getMaxHealth() * 0.5) return;
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }

}
