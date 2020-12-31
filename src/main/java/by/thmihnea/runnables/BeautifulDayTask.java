package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class BeautifulDayTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public BeautifulDayTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 20L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!this.player.isOnline()) {
            this.clear();
            return;
        }
        if (this.player.getWorld().hasStorm()) return;
        if (!Util.getHalfSetIDs(this.player).contains(15)) return;
        Vector nullVec = new Vector(0, 0, 0);
        Vector posVec = this.player.getVelocity();
        if (nullVec.getX() != posVec.getX()) return;
        if (nullVec.getZ() != posVec.getZ()) return;
        this.player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 1));
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

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
