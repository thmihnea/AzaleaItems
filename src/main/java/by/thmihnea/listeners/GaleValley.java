package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class GaleValley implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        checkGaleValley(e.getPlayer());
        if (Util.getHalfSetIDs(e.getPlayer()).contains(4)) {
            AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(e.getPlayer());
            if (e.getPlayer().getHealth() > attributedPlayer.getMaxHealth() - 1) {
                checkGaleValley(e.getPlayer());
                return;
            }
            AzaleaItems.armors.get(13).halfSetBonus(e.getPlayer(), e);
        }
    }

    public void checkGaleValley(Player player) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasGaleValley()) {
            itemizedPlayer.setGaleValley(false);
            AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
            attributedPlayer.setCritChance(attributedPlayer.getCritChance() - itemizedPlayer.getGaleValleyCritChanceAmount());
            itemizedPlayer.setGaleValleyCritChanceAmount(0);
        }
    }
}
