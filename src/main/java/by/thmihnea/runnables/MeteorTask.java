package by.thmihnea.runnables;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import org.inventivetalent.particle.ParticleEffect;

public class MeteorTask implements Runnable {

    private FallingBlock block;
    private Player player;
    private BukkitTask task;

    public MeteorTask(FallingBlock block, Player player) {
        task = Bukkit.getScheduler().runTaskTimer(AzaleaItems.getInstance(), this, 0L, 1L);
        this.block = block;
        this.player = player;
    }

    @Override
    public void run() {
        ParticleEffect.FLAME.send(Bukkit.getOnlinePlayers(), this.block.getLocation(), 0, 0, 0, 1, 0);
        double y = this.block.getVelocity().getY();
        this.block.setVelocity(genVec(this.block.getLocation(), player.getLocation()).setY(y));
        if (!this.block.isOnGround()) return;
        ParticleEffect.EXPLOSION_HUGE.send(Bukkit.getOnlinePlayers(), this.block.getLocation(), 0, 0, 0, 1, 0);
        XSound.play(this.block.getLocation(), "EXPLODE");
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(this.player);
        FPlayer self = FPlayers.getInstance().getByPlayer(this.player);
        this.block.getNearbyEntities(10, 10, 10).forEach(entity -> {
            if (!(entity instanceof LivingEntity)) return;
            if (entity instanceof Player) {
                Player player = (Player) entity;
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (self.getFaction().equals(near.getFaction())) return;
                player.damage(attributedPlayer.getDamage() * 1.5);
            } else ((LivingEntity) entity).damage(attributedPlayer.getDamage() * 1.5);
        });
        Block block = this.block.getLocation().getBlock();
        block.setType(XMaterial.AIR.parseMaterial());
        this.clear();
    }

    public static Vector genVec(Location a, Location b) {
        double dX = a.getX() - b.getX();
        double dY = a.getY() - b.getY();
        double dZ = a.getZ() - b.getZ();
        double yaw = Math.atan2(dZ, dX);
        double pitch = Math.atan2(Math.sqrt(dZ * dZ + dX * dX), dY) + Math.PI;
        double x = Math.sin(pitch) * Math.cos(yaw);
        double y = Math.sin(pitch) * Math.sin(yaw);
        double z = Math.cos(pitch);

        Vector vector = new Vector(x, z, y);
        vector = vector.normalize();
        //If you want to: vector = vector.normalize();

        return vector;
    }

    public void clear() {
        if (this.task != null) {
            Bukkit.getScheduler().cancelTask(task.getTaskId());
            task = null;
        }
    }
}
