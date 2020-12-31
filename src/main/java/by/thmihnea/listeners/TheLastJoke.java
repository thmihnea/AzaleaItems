package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class TheLastJoke implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(22)) return;
        LivingEntity killer = player.getKiller();
        if (killer == null) return;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your &cThe Last Joke &7triggered, dealing damage to your killer."));
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        killer.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c" + player.getName() + "'s &cThe Last Joke &7damaged you for &c" + (int) (attributedPlayer.getMaxHealth() * 0.5) + " DMG&7!"));
        killer.damage(attributedPlayer.getMaxHealth() * 0.5);
    }
}
