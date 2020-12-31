package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.items.SetBonus;
import com.cryptomorin.xseries.XSound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LastSting implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(8)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() - e.getDamage() > attributedPlayer.getMaxHealth() * 0.5) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.LAST_STING)) return;
        e.setCancelled(false);
        Cooldown cooldown = new Cooldown(player, CooldownType.LAST_STING, 60);
        itemizedPlayer.addCooldown(cooldown);
        player.sendMessage("§6§l➤ §aLast Sting §7detected that your Health fell below §c50%§7. You have §c+50% Crit Damage §7and §c+25% Crit Chance §7for 3 seconds!");
        attributedPlayer.setCritDmg(attributedPlayer.getCritDmg() + 50);
        attributedPlayer.setCritChance(attributedPlayer.getCritChance() + 25);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            attributedPlayer.setCritDmg(attributedPlayer.getCritDmg() - 50);
            attributedPlayer.setCritChance(attributedPlayer.getCritChance() - 25);
        }, 60L);
        player.playSound(player.getLocation(), XSound.ENTITY_WITHER_BREAK_BLOCK.parseSound(), 1.0f, 1.0f);
    }
}
