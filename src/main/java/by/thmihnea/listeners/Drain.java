package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import de.tr7zw.nbtapi.NBTEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class Drain implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!(Util.hasFullSet(e.getPlayer()))) return;
        if (!(Util.getHalfSetIDs(e.getPlayer()).contains(31))) return;
        Player player = e.getPlayer();
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (!(entity instanceof LivingEntity)) return;
            NBTEntity comp = new NBTEntity(entity);
            if (comp.hasKey("special")) return;
            entity.remove();
            attributedPlayer.setMaxHealth(attributedPlayer.getMaxHealth() + 20);
            player.setMaxHealth(player.getMaxHealth() + 20);
            Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                attributedPlayer.setMaxHealth(attributedPlayer.getMaxHealth() - 20);
                if (player.getHealth() > attributedPlayer.getMaxHealth()) player.setHealth(attributedPlayer.getMaxHealth());
                player.setMaxHealth(player.getMaxHealth() - 20);
            }, 600L);
        });
    }
}
