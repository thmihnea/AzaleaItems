package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.AzaleaArmor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FourWinds implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(e.getPlayer());
        if (Util.hasFullSet(e.getPlayer())) {
            AzaleaArmor armor = Util.getArmor(e.getPlayer().getInventory().getHelmet());
            assert armor != null;
            if (armor.getObjectID().equals(4)) {
                if (!itemizedPlayer.getHasFourWinds()) {
                    armor.fullSetBonus(e.getPlayer(), e);
                    itemizedPlayer.setHasFourWinds(true);
                }
            }
        } else {
            if (itemizedPlayer.getHasFourWinds()) {
                itemizedPlayer.getFourWinds().clear();
                itemizedPlayer.setHasFourWinds(false);
            }
            itemizedPlayer.setFourWinds(null);
        }
    }
}
