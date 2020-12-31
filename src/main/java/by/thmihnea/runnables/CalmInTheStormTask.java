package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.SetBonus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class CalmInTheStormTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public CalmInTheStormTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        recalcCrit();
    }

    public void recalcCrit() {
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(this.player);
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.CALM_IN_THE_STORM) > 0) {
            System.out.println(itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.CALM_IN_THE_STORM));
            attributedPlayer.setCritDmg(attributedPlayer.getCritDmg() - 100);
            attributedPlayer.setCritChance(attributedPlayer.getCritChance() - 50);
            itemizedPlayer.setBoostedStat(SetBonus.CALM_IN_THE_STORM, 0);
        }
        int quarteredHp = attributedPlayer.getMaxHealth() / 4;
        if (Util.hasFullSet(this.player) && Util.getHalfSetIDs(this.player).contains(14) && this.player.getHealth() <= quarteredHp) {
            itemizedPlayer.setBoostedStat(SetBonus.CALM_IN_THE_STORM, 100);
            attributedPlayer.setCritDmg(attributedPlayer.getCritDmg() + 100);
            attributedPlayer.setCritChance(attributedPlayer.getCritChance() + 50);
        }
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
