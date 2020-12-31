package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.items.SetBonus;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class GluttonyTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public GluttonyTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        recalcCrit(this.player);
    }

    public void recalcCrit(Player player) {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.CONVERSION) > 0) {
            attributedPlayer.setCritDmg(attributedPlayer.getCritDmg() - itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.CONVERSION));
            itemizedPlayer.setBoostedStat(SetBonus.CONVERSION, 0);
        }
        int hp = (int) ((player.getHealth()/attributedPlayer.getMaxHealth()) * 100);
        int critDmg = 100 - hp;
        if (Util.hasFullSet(player) && Util.getHalfSetIDs(player).contains(13)) {
            itemizedPlayer.setBoostedStat(SetBonus.CONVERSION, critDmg);
            attributedPlayer.setCritDmg(attributedPlayer.getCritDmg() + critDmg);
        }
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
