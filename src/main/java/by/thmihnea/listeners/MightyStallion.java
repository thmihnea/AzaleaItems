package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.runnables.VehicleTask;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class MightyStallion implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) return;
        Player player = e.getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(18)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.MIGHTY_STALLION)) {
            player.sendMessage(ChatColor.RED + "This ability is on cooldown for another " + itemizedPlayer.getCooldownByType(CooldownType.MIGHTY_STALLION).timeLeftInSeconds() + " seconds.");
            return;
        }
        Location location = player.getLocation();
        player.sendMessage(ChatColor.GRAY + "You have used " + ChatColor.RED + "Mighty Stallion" + ChatColor.GRAY + "!");
        Horse horse = (Horse) player.getWorld().spawnEntity(location, EntityType.HORSE);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            horse.setAdult();
            horse.getInventory().setSaddle(new ItemStack(XMaterial.SADDLE.parseMaterial(), 1));
            horse.setOwner(player);
            horse.setCustomName("MIGHTY_STALLION");
            horse.setCustomNameVisible(false);
            horse.setPassenger(player);
            horse.setVelocity(new Vector(player.getVelocity().multiply(3).getX(), 1.5, player.getVelocity().multiply(2).getZ()));
        }, 4L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            new VehicleTask(horse, player);
        }, 5L);
        Cooldown cooldown = new Cooldown(player, CooldownType.MIGHTY_STALLION, 15);
        itemizedPlayer.addCooldown(cooldown);
    }

    @EventHandler
    public void onDeath(VehicleExitEvent e) {
        if (!(e.getVehicle() instanceof Horse)) return;
        if (!e.getVehicle().getCustomName().equalsIgnoreCase("MIGHTY_STALLION")) return;
        e.setCancelled(true);
    }

}
