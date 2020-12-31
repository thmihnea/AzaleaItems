package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
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

public class FromTheAshes implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if (!(entity instanceof Player)) return;
        Player player = ((Player) entity).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(5)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() > attributedPlayer.getMaxHealth() * 0.3) return;
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.hasCooldown(CooldownType.FROM_THE_ASHES)) return;
        Cooldown cooldown = new Cooldown(player, CooldownType.FROM_THE_ASHES, 60);
        itemizedPlayer.addCooldown(cooldown);
        player.setHealth(player.getHealth() + (attributedPlayer.getMaxHealth() * 0.15));
        attributedPlayer.setCurrentMana(attributedPlayer.getMaxMana() + attributedPlayer.getMaxMana()/10);
        player.sendMessage("§6§l➤ §5From the Ashes §7saved your life! Granted §c+15% HP §7& §b+10% MANA§7 out of your total stats!");
        player.playSound(player.getLocation(), XSound.ENTITY_BLAZE_DEATH.parseSound(), 1.0f, 1.0f);
        if (attributedPlayer.getCurrentMana() > attributedPlayer.getMaxMana()) attributedPlayer.setCurrentMana(attributedPlayer.getMaxMana());
    }
}
