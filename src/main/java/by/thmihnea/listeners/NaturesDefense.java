package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import com.cryptomorin.xseries.XSound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class NaturesDefense implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.isCancelled()) return;
        checkForNaturesDefense(e.getPlayer());
        if (!Util.getHalfSetIDs(e.getPlayer()).contains(6)) return;
        AzaleaItems.armors.get(24).halfSetBonus(e.getPlayer(), e);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(6)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() - e.getDamage() > attributedPlayer.getMaxHealth() * 0.03) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.NATURES_DEFENSE)) return;
        e.setCancelled(true);
        Cooldown cooldown = new Cooldown(player, CooldownType.NATURES_DEFENSE, 60);
        itemizedPlayer.addCooldown(cooldown);
        player.sendMessage("§6§l➤ §aNature's Defense §7propelled you backwards, potentially saving your life.");
        player.setVelocity(player.getLocation().getDirection().multiply(-1.2).setY(1));
        player.playSound(player.getLocation(), XSound.ENTITY_WITHER_BREAK_BLOCK.parseSound(), 1.0f, 1.0f);
    }

    public void checkForNaturesDefense(Player player) {
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasNaturesDefense()) {
            itemizedPlayer.setNaturesDefense(false);
            AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
            attributedPlayer.setDefense(attributedPlayer.getDefense() - itemizedPlayer.getNaturesDefenseAmount());
            itemizedPlayer.setNaturesDefenseAmount(0);
        }
    }
}
