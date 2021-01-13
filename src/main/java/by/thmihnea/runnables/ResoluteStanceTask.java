package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.item.SetBonus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

public class ResoluteStanceTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public ResoluteStanceTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(this.player);
        if (!Util.getHalfSetIDs(this.player).contains(19)) {
            itemizedPlayer.setBoostedStat(SetBonus.RESOLUTE_STANCE, 0);
            if (this.player.hasPotionEffect(PotionEffectType.ABSORPTION))
                this.player.removePotionEffect(PotionEffectType.ABSORPTION);
            return;
        }
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RESOLUTE_STANCE) < 0) itemizedPlayer.setBoostedStat(SetBonus.RESOLUTE_STANCE, 0);
        if (itemizedPlayer.hasCooldown(CooldownType.RESOLUTE_STANCE)) return;
        Cooldown cooldown = new Cooldown(this.player, CooldownType.RESOLUTE_STANCE, 120);
        itemizedPlayer.addCooldown(cooldown);
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 2));
        itemizedPlayer.setBoostedStat(SetBonus.RESOLUTE_STANCE, (int) (attributedPlayer.getMaxHealth() * 0.3));
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
