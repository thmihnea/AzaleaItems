package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import com.cryptomorin.xseries.XSound;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicReference;

public class VehicleTask implements Runnable {

    private Horse horse;
    private Player player;
    private BukkitTask task;

    public VehicleTask(Horse horse, Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 1L);
        this.horse = horse;
        this.player = player;
    }

    @Override
    public void run() {
        if (!(this.player.isOnline())) {
            this.clear();
            return;
        }
        if (!this.horse.isOnGround()) return;
        Player player = this.player;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        FPlayer self = FPlayers.getInstance().getByPlayer(player);
        AtomicReference<Double> damage = new AtomicReference<>((double) 0);
        player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (!(entity instanceof LivingEntity)) return;
            if (entity.getEntityId() == this.horse.getEntityId()) return;
            if (!(entity instanceof Player)) ((LivingEntity) entity).damage(attributedPlayer.getDamage() * 0.5);
            else {
                FPlayer near = FPlayers.getInstance().getByPlayer(((Player) entity).getPlayer());
                if (!self.getFaction().equals(near.getFaction())) {
                    ((LivingEntity) entity).damage(attributedPlayer.getDamage() * 0.5);
                    damage.set(damage.get() + attributedPlayer.getDamage() * 0.5);
                    near.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + player.getName() + " &7dealt &c" + (attributedPlayer.getDamage() * 0.5) + " &7damage to you using &cMighty Stallion&7!"));
                }
            }
        });
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You dealt a total of &c" + damage.get() + " &7damage using &cMighty Stallion&7!"));
        this.horse.remove();
        this.clear();
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
