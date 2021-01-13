package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.AzaleaArmor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CrashingWaves implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(e.getPlayer());
        if (Util.hasFullSet(e.getPlayer())) {
            AzaleaArmor armor = Util.getArmor(e.getPlayer().getInventory().getHelmet());
            if (armor.getObjectID().equals(2)) {
                if (!itemizedPlayer.getHasCrashingWaves()) {
                    armor.fullSetBonus(e.getPlayer(), e);
                    itemizedPlayer.setHasCrashingWaves(true);
                }
            }
        } else {
            if (itemizedPlayer.getHasCrashingWaves()) {
                itemizedPlayer.getCrashingWaves().clear();
                itemizedPlayer.setHasCrashingWaves(false);
            }
            itemizedPlayer.setCrashingWaves(null);
        }
    }
}
