package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.item.AzaleaItem;
import com.cryptomorin.xseries.XMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntityListener implements Listener {

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        ItemStack itemStack = player.getItemInHand();
        if (itemStack == null || itemStack.getType() == XMaterial.AIR.parseMaterial()) return;
        AzaleaItem azaleaItem = Util.getItem(itemStack);
        azaleaItem.rightClickEntityAction(player, e);
    }
}
