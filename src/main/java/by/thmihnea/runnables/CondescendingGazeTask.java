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

import java.util.ArrayList;

public class CondescendingGazeTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public CondescendingGazeTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!Util.getHalfSetIDs(this.player).contains(11)) {
            this.clear();
        }
        if (Util.hasFullSet(this.player)) {
            new ArrayList<>(this.player.getActivePotionEffects()).forEach(pe -> {
                if (pe.getType().equals(PotionEffectType.WEAKNESS)) this.player.removePotionEffect(pe.getType());
                else if (pe.getType().equals(PotionEffectType.POISON)) this.player.removePotionEffect(pe.getType());
                else if (pe.getType().equals(PotionEffectType.HARM)) this.player.removePotionEffect(pe.getType());
                else if (pe.getType().equals(PotionEffectType.CONFUSION)) this.player.removePotionEffect(pe.getType());
                else if (pe.getType().equals(PotionEffectType.BLINDNESS)) this.player.removePotionEffect(pe.getType());
                else if (pe.getType().equals(PotionEffectType.HUNGER)) this.player.removePotionEffect(pe.getType());
                else if (pe.getType().equals(PotionEffectType.WITHER)) this.player.removePotionEffect(pe.getType());
            });
        }
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 60, 0));
        this.player.getNearbyEntities(3, 3, 3).forEach(entity -> {
            FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                if (Util.hasFullSet(player) && Util.getHalfSetIDs(player).contains(11)) return;
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (!self.getFaction().equals(near.getFaction())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 2));
                }
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
