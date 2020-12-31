package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.SetBonus;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AssembleTheBees implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(e.getPlayer());
        checkForAssembleTheBees(e.getPlayer());
        if (Util.getHalfSetIDs(e.getPlayer()).contains(8))
            AzaleaItems.armors.get(30).halfSetBonus(e.getPlayer(), e);
    }

    public void checkForAssembleTheBees(Player player) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasStat(SetBonus.ASSEMBLE_THE_BEES)) {
            AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
            attributedPlayer.setRegen(attributedPlayer.getRegen() - itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.ASSEMBLE_THE_BEES));
            if (attributedPlayer.getRegen() < 0) attributedPlayer.setRegen(attributedPlayer.getMaxMana() / 20);
            itemizedPlayer.setBoostedStat(SetBonus.ASSEMBLE_THE_BEES, 0);
        } else return;
    }
}
