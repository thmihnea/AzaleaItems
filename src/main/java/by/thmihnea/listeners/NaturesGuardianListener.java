package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.AzaleaArmor;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class NaturesGuardianListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        checkForNaturesGuardian(e.getPlayer());
        if (Util.getHalfSetIDs(e.getPlayer()).contains(1)) {
            if (!getClosestBlockUnderneath(e.getPlayer()).equals(XMaterial.GRASS.parseMaterial()) &&
                    !getClosestBlockUnderneath(e.getPlayer()).equals(XMaterial.GRASS_BLOCK.parseMaterial()) &&
                    !getClosestBlockUnderneath(e.getPlayer()).equals(XMaterial.TALL_GRASS.parseMaterial()) &&
                    !getClosestBlockUnderneath(e.getPlayer()).toString().toUpperCase().contains("FLOWER") &&
                    !getClosestBlockUnderneath(e.getPlayer()).toString().toUpperCase().contains("TULIP") &&
                    !getClosestBlockUnderneath(e.getPlayer()).toString().toUpperCase().contains("POPPY") &&
                    !getClosestBlockUnderneath(e.getPlayer()).toString().toUpperCase().contains("ALLIUM") &&
                    !getClosestBlockUnderneath(e.getPlayer()).toString().toUpperCase().contains("DANDELION")) {
                checkForNaturesGuardian(e.getPlayer());
                return;
            }
            AzaleaItems.armors.get(1).halfSetBonus(e.getPlayer(), e);
        }
    }

    public void checkForNaturesGuardian(Player player) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasNaturesGuardian()) {
            itemizedPlayer.setNaturesGuardian(false);
            AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
            attributedPlayer.setMaxMana(attributedPlayer.getMaxMana() - itemizedPlayer.getNaturesGuardianManaAmount());
            itemizedPlayer.setNaturesGuardianManaAmount(0);
        }
    }

    public Material getClosestBlockUnderneath(Player player) {
        Location location = player.getLocation();
        for (int y = location.getBlockY(); y > 0; y--) {
            if (location.getBlock().getType() != XMaterial.AIR.parseMaterial()) return location.getBlock().getType();
            location = location.subtract(0, 1, 0);
        }
        return XMaterial.AIR.parseMaterial();
    }
}
