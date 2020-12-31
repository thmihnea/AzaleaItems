package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.Util;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ForTheKing implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(16)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() > attributedPlayer.getMaxHealth() * 0.5) return;
        e.setDamage(e.getDamage() * 0.7);
    }
}
