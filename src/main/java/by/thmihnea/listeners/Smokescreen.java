package by.thmihnea.listeners;

import by.thmihnea.Util;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Smokescreen implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (!(damager instanceof Player)) return;
        Player player = ((Player) damager).getPlayer();
        if (!Util.getHalfSetIDs(player).contains(25)) return;
        FPlayer self = FPlayers.getInstance().getByPlayer(player);
        player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            if (!(entity instanceof Player)) return;
            Player near = ((Player) entity).getPlayer();
            FPlayer fnear = FPlayers.getInstance().getByPlayer(near);
            if (!self.getFaction().equals(fnear.getFaction()))
                near.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0));
        });
    }
}
