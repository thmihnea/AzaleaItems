package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Stalemate implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = ((Player) e.getEntity()).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(20)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() > attributedPlayer.getMaxHealth() * 0.1) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.STALEMATE)) return;
        FPlayer self = FPlayers.getInstance().getByPlayer(player);
        player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (!(entity instanceof Player)) return;
            Player toRoot = ((Player) entity).getPlayer();
            FPlayer toRootF = FPlayers.getInstance().getByPlayer(toRoot);
            if (toRootF.getFaction().equals(self.getFaction())) return;
            ItemizedPlayer toRootItemized = ItemizedPlayerManager.getItemizedPlayer(toRoot);
            toRootItemized.setRooted(true);
            Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                toRootItemized.setRooted(false);
            }, 100L);
        });
        Cooldown cooldown = new Cooldown(player, CooldownType.STALEMATE, 60);
        itemizedPlayer.addCooldown(cooldown);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cStalemate &7has frozen all enemies around you for &c5 seconds&7."));
    }

    @EventHandler
    public void onJump(PlayerMoveEvent e) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(e.getPlayer());
        if (itemizedPlayer.isRooted()) e.setCancelled(true);
    }
}
