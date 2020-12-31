package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.AzaleaArmor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TectonicShift implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(e.getPlayer());
        if (Util.hasFullSet(e.getPlayer())) {
            AzaleaArmor armor = Util.getArmor(e.getPlayer().getInventory().getHelmet());
            assert armor != null;
            if (armor.getObjectID().equals(3)) {
                if (!itemizedPlayer.getHasTectonicShift()) {
                    armor.fullSetBonus(e.getPlayer(), e);
                    itemizedPlayer.setHasTectonicShift(true);
                }
            }
        } else {
            if (itemizedPlayer.getHasTectonicShift()) {
                itemizedPlayer.getTectonicShift().clear();
                itemizedPlayer.setHasTectonicShift(false);
            }
            itemizedPlayer.setTectonicShift(null);
        }
    }

}
