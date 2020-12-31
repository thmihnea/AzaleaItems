package by.thmihnea.listeners;

import by.thmihnea.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class GluttonyListener implements Listener {

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        if (!Util.getHalfSetIDs(player).contains(13)) return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 60, 0));
    }
}
