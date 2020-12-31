package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
public class QueensGambit implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) return;
        Player player = e.getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(21)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.QUEENS_GAMBIT)) {
            player.sendMessage(ChatColor.RED + "This ability is on cooldown for another " + itemizedPlayer.getCooldownByType(CooldownType.QUEENS_GAMBIT).timeLeftInSeconds() + " seconds.");
            return;
        }
        player.sendMessage(ChatColor.GRAY + "You have used " + ChatColor.GOLD + "Queen's Gambit" + ChatColor.GRAY + "!");
        Cooldown cooldown = new Cooldown(player, CooldownType.QUEENS_GAMBIT, 300);
        itemizedPlayer.addCooldown(cooldown);
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() > attributedPlayer.getMaxHealth() * 0.15)
            player.setHealth(attributedPlayer.getMaxHealth() * 0.15);
        int defAmt = (int) (attributedPlayer.getDefense() * 0.5);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            if (!player.isDead()) {
                player.setHealth(attributedPlayer.getMaxHealth());
                attributedPlayer.setDefense(attributedPlayer.getDefense() + defAmt);
                attributedPlayer.setDamage(attributedPlayer.getDamage() + 150);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Queen's Gambit &7granted you &3" + defAmt + " DEF &7and &c150 DMG&7."));
            }
        }, 100L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            attributedPlayer.setDefense(attributedPlayer.getDefense() - defAmt);
            attributedPlayer.setDamage(attributedPlayer.getDamage() - 150);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Queen's Gambit &7has expired. You're now back to your normal form."));
        }, 200L);
    }

}
