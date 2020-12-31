package by.thmihnea.items.armors;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.AzaleaItems;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.AzaleaArmor;
import by.thmihnea.items.SetBonus;
import by.thmihnea.rarity.Rarity;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LustArmor extends AzaleaArmor {
    public LustArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
        super(material, name, objectID, id, rarity, hp, defense, mana, damage, critDamage, critChance, halfSetBonus, fullSetBonus);
    }

    @Override
    public void fullSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void fullSetBonus(Player paramPlayer, Event event) {

    }

    @Override
    public void halfSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void halfSetBonus(Player paramPlayer, Event event) {
        Random random = ThreadLocalRandom.current();
        paramPlayer.getNearbyEntities(5, 5, 5).forEach(entity -> {
            FPlayer self = FPlayers.getInstance().getByPlayer(paramPlayer);
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (!self.getFaction().equals(near.getFaction())) {
                    int hash = random.nextInt(100);
                    int chance = 15;
                    if (hash <= chance)
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
                }
            }
        });
    }
}
