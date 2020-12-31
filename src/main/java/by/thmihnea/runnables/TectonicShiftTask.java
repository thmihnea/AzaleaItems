package by.thmihnea.runnables;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import com.cryptomorin.xseries.particles.XParticle;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TectonicShiftTask implements Runnable {

    private Player player;
    private BukkitTask task;

    public TectonicShiftTask(Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 200L);
        this.player = player;
    }

    @Override
    public void run() {
        if (!Util.hasFullSet(this.player)) {
            this.clear();
            return;
        }
        FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
        Random random = ThreadLocalRandom.current();
        this.player.getNearbyEntities(3, 3, 3).forEach(entity -> {
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                if (Util.hasFullSet(player) && Util.getHalfSetIDs(player).contains(11)) return;
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (!near.getFaction().equals(self.getFaction())) {
                    int chance = 7;
                    int hash = random.nextInt(100);
                    if (hash <= chance) {
                        this.displayParticles();
                        ItemStack itemStack = getRandomArmorPiece(player);
                        if (itemStack == null) return;
                        this.removeArmorPiece(player, itemStack);
                        player.sendMessage("§6§l➢ §7" + this.player.getName() + "'s §5Tectonic Shift §7has disarmed you, placing an armor piece back into your inventory!");
                        player.getInventory().addItem(itemStack);
                    }
                }
            }
        });

    }

    public void displayParticles() {
        for (int x = this.player.getLocation().getBlockX() - 2; x <= this.player.getLocation().getBlockX() + 3; x++) {
            for (int z = this.player.getLocation().getBlockZ() - 2; z <= this.player.getLocation().getBlockZ() + 3; z++) {
                Location location = new Location(this.player.getWorld(), x, this.player.getLocation().getBlockY(), z);
                this.player.spawnParticle(XParticle.getParticle(Particle.SMOKE_LARGE.name()), location, 5);
                Bukkit.getOnlinePlayers().forEach(p -> p.spawnParticle(XParticle.getParticle(Particle.SMOKE_LARGE.name()), location, 2));
            }
        }
    }

    public void removeArmorPiece(Player player, ItemStack itemStack) {
        if (itemStack.getType().toString().toUpperCase().contains("HELMET"))
            player.getInventory().setHelmet(null);
        else if (itemStack.getType().toString().toUpperCase().contains("CHESTPLATE"))
            player.getInventory().setChestplate(null);
        else if (itemStack.getType().toString().toUpperCase().contains("LEGGINGS"))
            player.getInventory().setLeggings(null);
        else if (itemStack.getType().toString().toUpperCase().contains("BOOTS"))
            player.getInventory().setBoots(null);
    }

    public ItemStack getRandomArmorPiece(Player player) {
        Random random = ThreadLocalRandom.current();
        int hash = random.nextInt(4);
        switch (hash) {
            case 0:
                return player.getInventory().getHelmet();
            case 1:
                return player.getInventory().getChestplate();
            case 2:
                return player.getInventory().getLeggings();
            case 3:
            default:
                return player.getInventory().getBoots();
        }
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
