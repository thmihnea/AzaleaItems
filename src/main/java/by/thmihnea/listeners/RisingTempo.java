package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.SetBonus;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class RisingTempo implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (!(damager instanceof Player)) return;
        Player player = ((Player) damager).getPlayer();
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (!(Util.getHalfSetIDs(player)).contains(21)) {
            itemizedPlayer.setBoostedStat(SetBonus.RISING_TEMPO, 0);
            return;
        }
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        itemizedPlayer.setBoostedStat(SetBonus.RISING_TEMPO, itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RISING_TEMPO) + 1);
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RISING_TEMPO) == 3) {
            itemizedPlayer.setBoostedStat(SetBonus.RISING_TEMPO, 0);
            int amount = attributedPlayer.getMaxHealth() / 10;
            if (player.getHealth() + amount > attributedPlayer.getMaxHealth()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Rising Tempo &7granted you full health."));
                player.setHealth(attributedPlayer.getMaxHealth());
            }
            else {
                player.setHealth(player.getHealth() + amount);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Rising Tempo &7granted you &c" + amount + " HP &7back."));
            }
        }
    }
}
