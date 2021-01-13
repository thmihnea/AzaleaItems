package by.thmihnea.listeners;

import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.AzaleaItem;
import com.cryptomorin.xseries.XMaterial;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class EntityDamageByEntity implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        Player player = (Player) e.getDamager();
        if (player.getItemInHand() == null || player.getItemInHand().getType() == XMaterial.AIR.parseMaterial()) return;
        AzaleaItem azaleaItem = Util.getItem(player.getItemInHand());
        if (azaleaItem == null) return;
        azaleaItem.hitEntityAction(player, e);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        Player player = (Player) e.getDamager();
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (!itemizedPlayer.hasPickpocketActive()) return;
        if (!(e.getEntity() instanceof Player)) return;
        Player damaged = (Player) e.getEntity();
        FPlayer self = FPlayers.getInstance().getByPlayer(player);
        FPlayer target = FPlayers.getInstance().getByPlayer(damaged);
        if (self.getFaction().equals(target.getFaction())) return;
        if (e.isCancelled()) return;
        int amount = ThreadLocalRandom.current().nextInt(1001);
        EconomyResponse r1 = AzaleaItems.getEconomy().depositPlayer(player, amount);
        EconomyResponse r2 = AzaleaItems.getEconomy().withdrawPlayer(damaged, amount);
        itemizedPlayer.setPickpocket(false);
    }
}
