package by.thmihnea.item.armors;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.AzaleaArmor;
import by.thmihnea.item.SetBonus;
import by.thmihnea.rarity.Rarity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class NaturesArmor extends AzaleaArmor {


    public NaturesArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
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
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(paramPlayer);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(paramPlayer);
        if (itemizedPlayer.hasNaturesGuardian()) return;
        else {
            itemizedPlayer.setNaturesGuardian(true);
            int buffedMana = attributedPlayer.getMaxMana() / 4;
            attributedPlayer.setMaxMana(attributedPlayer.getMaxMana() + buffedMana);
            itemizedPlayer.setNaturesGuardianManaAmount(buffedMana);
        }
    }
}
