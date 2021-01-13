package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.AzaleaArmor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CondescendingGaze implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(e.getPlayer());
        if (Util.getHalfSetIDs(e.getPlayer()).contains(11)) {
            if (!itemizedPlayer.getHasCondescendingGaze()) {
                AzaleaArmor armor = AzaleaItems.armors.get(44);
                armor.fullSetBonus(e.getPlayer(), e);
                itemizedPlayer.setHasCondescendingGaze(true);
            }
        } else {
            if (itemizedPlayer.getHasCondescendingGaze()) {
                itemizedPlayer.getCondescendingGaze().clear();
                itemizedPlayer.setHasCondescendingGaze(false);
            }
            itemizedPlayer.setCondescendingGaze(null);
        }
    }
}
