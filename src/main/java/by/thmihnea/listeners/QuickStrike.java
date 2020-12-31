package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class QuickStrike implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) return;
        Player player = e.getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(17)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.QUICK_STRIKE)) {
            player.sendMessage(ChatColor.RED + "This ability is on cooldown for another " + itemizedPlayer.getCooldownByType(CooldownType.QUICK_STRIKE).timeLeftInSeconds() + " seconds.");
            return;
        }
        Location location = player.getLocation();
        player.sendMessage(ChatColor.GRAY + "You have used " + ChatColor.RED + "Quick Strike" + ChatColor.GRAY + "!");
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 1));
        Cooldown cooldown = new Cooldown(player, CooldownType.QUICK_STRIKE, 20);
        itemizedPlayer.addCooldown(cooldown);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            player.teleport(location);
            player.sendMessage(ChatColor.RED + "Quick Strike" + ChatColor.GRAY + " has teleported you back to Warp Point!");
        }, 100L);
    }
}
