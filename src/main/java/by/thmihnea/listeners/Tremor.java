package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Tremor implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        if (Util.getHalfSetIDs(e.getPlayer()).contains(3)) {
            AzaleaItems.armors.get(9).halfSetBonus(e.getPlayer(), e);
        }
    }
}
