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

public class BumblebeeArmor extends AzaleaArmor {
    public BumblebeeArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
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
        if (itemizedPlayer.getBoostedAmountBySetBonus(SetBonus.ASSEMBLE_THE_BEES) > 0) return;
        else {
            int buffedRegen = attributedPlayer.getRegen() / 10;
            itemizedPlayer.setBoostedStat(SetBonus.ASSEMBLE_THE_BEES, buffedRegen);
            attributedPlayer.setRegen(attributedPlayer.getRegen() + buffedRegen);
        }
    }
}
