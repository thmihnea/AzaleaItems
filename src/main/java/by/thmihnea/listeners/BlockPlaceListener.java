package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.item.AzaleaItem;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        ItemStack itemStack = e.getItemInHand();
        AzaleaItem azaleaItem = Util.getItem(itemStack);
        if (azaleaItem == null) return;
        if (!azaleaItem.isPlaceable()) e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are not allowed to place this item!"));
    }
}
