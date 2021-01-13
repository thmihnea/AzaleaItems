package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.item.AzaleaArmor;
import com.cryptomorin.xseries.XSound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Reincarnation implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.hasFullSet(player)) return;
        AzaleaArmor armor = Util.getArmor(player.getInventory().getHelmet());
        if (!armor.getObjectID().equals(5)) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.REINCARNATION)) return;
        if (player.getHealth() - e.getDamage() <= 0) {
            player.sendMessage("§6§l➤ §5Reincarnation §7triggered and saved your life. Healed you for §a§l" + attributedPlayer.getMaxHealth() + " HP§7!");
            e.setCancelled(true);
            player.setHealth(attributedPlayer.getMaxHealth() - 1);
            player.playSound(player.getLocation(), XSound.ENTITY_EXPERIENCE_ORB_PICKUP.parseSound(), 1.0f, 1.0f);
            Cooldown cooldown = new Cooldown(player, CooldownType.REINCARNATION, 300);
            itemizedPlayer.addCooldown(cooldown);
        }
    }
}
