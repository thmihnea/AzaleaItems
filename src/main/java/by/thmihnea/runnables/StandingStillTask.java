package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class StandingStillTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public StandingStillTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        if (!Util.getHalfSetIDs(this.player).contains(10)) return;
        Vector nullVec = new Vector(0, 0, 0);
        Vector posVec = this.player.getVelocity();
        if (nullVec.getX() != posVec.getX()) return;
        if (nullVec.getZ() != posVec.getZ()) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        int buff = (int) (attributedPlayer.getMaxMana() / 37.5);
        if (attributedPlayer.getCurrentMana() < attributedPlayer.getMaxMana())
            attributedPlayer.setCurrentMana(attributedPlayer.getCurrentMana() + buff);
        if (!Util.hasFullSet(this.player)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(this.player);
        itemizedPlayer.getCooldowns().forEach(cooldown -> cooldown.subtractEndTime(3));
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
