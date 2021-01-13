package by.thmihnea.item.armors;

import by.thmdev.api.AttributeManager;
import by.thmdev.api.AttributedPlayer;
import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.item.AzaleaArmor;
import by.thmihnea.item.SetBonus;
import by.thmihnea.rarity.Rarity;
import by.thmihnea.runnables.FourWindsTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ZephyrsArmor extends AzaleaArmor {

    public ZephyrsArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
        super(material, name, objectID, id, rarity, hp, defense, mana, damage, critDamage, critChance, halfSetBonus, fullSetBonus);
    }

    @Override
    public void fullSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void fullSetBonus(Player paramPlayer, Event event) {
        FourWindsTask fourWindsTask = new FourWindsTask(paramPlayer);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(paramPlayer);
        itemizedPlayer.setFourWinds(fourWindsTask);
    }

    @Override
    public void halfSetBonusNoEvent(Player paramPlayer) {
    }

    @Override
    public void halfSetBonus(Player paramPlayer, Event event) {
        AttributedPlayer attributedPlayer = AttributeManager.getAttributedPlayer(paramPlayer);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(paramPlayer);
        if (itemizedPlayer.hasGaleValley()) return;
        else {
            itemizedPlayer.setGaleValley(true);
            attributedPlayer.setCritChance(attributedPlayer.getCritChance() + 10);
            itemizedPlayer.setGaleValleyCritChanceAmount(10);
        }
    }
}
