package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Cocoon implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(9)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() - e.getDamage() > attributedPlayer.getMaxHealth() * 0.3) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.COCOON)) return;
        e.setCancelled(false);
        Cooldown cooldown = new Cooldown(player, CooldownType.COCOON, 60);
        itemizedPlayer.addCooldown(cooldown);
        player.sendMessage("§6§l➤ §9Cocoon §7covered you in cobwebs for §c3 §7seconds.");
        spawnWebs(player);
        int buffedDef = (int) (attributedPlayer.getDefense() * 0.3);
        int buffedRegen = (int) (attributedPlayer.getRegen() * 4.0);
        attributedPlayer.setDefense(attributedPlayer.getDefense() + buffedDef);
        attributedPlayer.setRegen(attributedPlayer.getRegen() + buffedRegen);
        Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
            attributedPlayer.setRegen(attributedPlayer.getRegen() - buffedRegen);
            attributedPlayer.setDefense(attributedPlayer.getDefense() - buffedDef);
        }, 60L);
        player.playSound(player.getLocation(), XSound.ENTITY_SPIDER_DEATH.parseSound(), 1.0f, 1.0f);
    }

    public void spawnWebs(Player player) {
        for (int x = player.getLocation().getBlockX() - 1; x <= player.getLocation().getBlockX() + 2; x++)
            for (int y = player.getLocation().getBlockY() - 2; y <= player.getLocation().getBlockY() + 2; y++)
                for (int z = player.getLocation().getBlockZ() - 1; z <= player.getLocation().getBlockZ() + 2; z++) {
                    Location location = new Location(player.getWorld(), x, y, z);
                    Block block = location.getBlock();
                    Material material = block.getType();
                    block.setType(XMaterial.COBWEB.parseMaterial());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(AzaleaItems.getInstance(), () -> {
                        block.setType(material);
                    }, 60L);
                }
    }
}
