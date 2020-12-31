package by.thmihnea.listeners;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

public class FinalGambit implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        Player player = ((Player) e.getDamager()).getPlayer();
        if (!Util.hasFullSet(player)) return;
        if (!Util.getHalfSetIDs(player).contains(30)) return;
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(player);
        if (player.getHealth() > attributedPlayer.getMaxHealth() * 0.1) return;
        int roll = ThreadLocalRandom.current().nextInt(3) + 1;
        e.setDamage(e.getDamage() * roll);
    }
}
