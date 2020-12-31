package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ChronoTask implements Runnable {

    private Player player;
    private BukkitTask task;
    private int manaBoost = 0;

    public ChronoTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        if (!Util.hasFullSet(this.player)) {
            attributedPlayer.setMaxMana(attributedPlayer.getMaxMana() - this.manaBoost);
            this.manaBoost = 0;
            return;
        }
        if (!Util.getHalfSetIDs(player).contains(23)) return;
        byte light = this.player.getWorld().getHighestBlockAt(this.player.getLocation()).getLightLevel();
        int moonLevel = 15 - light;
        int sunLevel = light - 4;
        int buffedDamage = sunLevel * 15;
        int buffedMana = moonLevel * 50; // attributedPlayer.setMaxMana(attributedPlayer.getMaxMana() + buffedMana);
        if (this.manaBoost > buffedMana)
            attributedPlayer.setMaxMana(attributedPlayer.getMaxMana() - (this.manaBoost - buffedMana));
        else
            attributedPlayer.setMaxMana(attributedPlayer.getMaxMana() + (buffedMana - this.manaBoost));
        this.manaBoost = buffedMana;
        attributedPlayer.setDamage(attributedPlayer.getDamage() + buffedDamage);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            attributedPlayer.setDamage(attributedPlayer.getDamage() - buffedDamage);
        }, 20L);
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }

}
