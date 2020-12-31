package by.thmihnea.items.armors;

import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.AzaleaArmor;
import by.thmihnea.items.SetBonus;
import by.thmihnea.rarity.Rarity;
import by.thmihnea.runnables.CrashingWavesTask;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UndinesArmor extends AzaleaArmor {


    public UndinesArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
        super(material, name, objectID, id, rarity, hp, defense, mana, damage, critDamage, critChance, halfSetBonus, fullSetBonus);
    }

    @Override
    public void fullSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void fullSetBonus(Player paramPlayer, Event event) {
        CrashingWavesTask crashingWavesTask = new CrashingWavesTask(paramPlayer);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(paramPlayer);
        itemizedPlayer.setCrashingWaves(crashingWavesTask);
    }

    @Override
    public void halfSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void halfSetBonus(Player paramPlayer, Event event) {
        FPlayer self = FPlayers.getInstance().getByPlayer(paramPlayer);
        paramPlayer.getNearbyEntities(3, 3, 3).forEach(entity -> {
            if (entity instanceof Player) {
                Player player = ((Player) entity).getPlayer();
                FPlayer near = FPlayers.getInstance().getByPlayer(player);
                if (near.getFaction().equals(self.getFaction())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0, false, false));
                }
            }
        });
    }
}
