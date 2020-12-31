package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class SultryGaze implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(e.getPlayer());
        if (!Util.getHalfSetIDs(e.getPlayer()).contains(12)) {
            itemizedPlayer.setIsGazed(false);
            return;
        }
        AzaleaItems.armors.get(48).halfSetBonus(e.getPlayer(), e);
        itemizedPlayer.setIsGazed(true);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity damaged = e.getEntity();
        if (!(damaged instanceof Player)) return;
        Player player = ((Player) damaged).getPlayer();
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.getIsGazed()) e.setDamage(0.7 * e.getDamage());
    }
}
