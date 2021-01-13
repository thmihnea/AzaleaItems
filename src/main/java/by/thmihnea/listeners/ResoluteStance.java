package by.thmihnea.listeners;

import by.thmihnea.Util;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.item.SetBonus;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ResoluteStance implements Listener {


    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = ((Player) e.getEntity()).getPlayer();
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RESOLUTE_STANCE) == 0) return;
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RESOLUTE_STANCE) - e.getDamage() < 0) {
            itemizedPlayer.setBoostedStat(SetBonus.RESOLUTE_STANCE, 0);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cResolute Stance's &7shield just broke. You are no longer protected from enemy attacks."));
            player.removePotionEffect(PotionEffectType.ABSORPTION);
            e.setDamage(Math.abs(itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RESOLUTE_STANCE) - e.getDamage()));
            emitWave(player);
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 2));
            itemizedPlayer.setBoostedStat(SetBonus.RESOLUTE_STANCE, (int) (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.RESOLUTE_STANCE) - e.getDamage()));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cResolute Stance &7absored &c" + Math.floor(e.getDamage()) + " &7damage."));
            e.setDamage(0);
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (!e.isSneaking()) return;
        if (!Util.hasFullSet(e.getPlayer())) return;
        if (!Util.getHalfSetIDs(e.getPlayer()).contains(19)) return;
        Player player = e.getPlayer();
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(player);
        if (!itemizedPlayer.hasCooldown(CooldownType.RESOLUTE_STANCE)) return;
        else itemizedPlayer.getCooldownByType(CooldownType.RESOLUTE_STANCE).subtractEndTime(250L);
    }

    public void emitWave(Player player) {
        FPlayer self = FPlayers.getInstance().getByPlayer(player);
        player.getNearbyEntities(5, 5, 5).forEach(entity -> {
            boolean emitWave = false;
            if (entity instanceof Player) {
                Player toKb = ((Player) entity).getPlayer();
                FPlayer near = FPlayers.getInstance().getByPlayer(toKb);
                if (!near.getFaction().equals(self.getFaction()))
                    emitWave = true;
            } else emitWave = true;
            if (emitWave) {
                entity.setVelocity(entity.getLocation().getDirection().multiply(-1.2).setY(1));
            }
        });
    }

}
