package by.thmihnea.items.armors;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.items.AzaleaArmor;
import by.thmihnea.items.SetBonus;
import by.thmihnea.rarity.Rarity;
import com.cryptomorin.xseries.XSound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class InfernalArmor extends AzaleaArmor {
    public InfernalArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
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

    }
}
