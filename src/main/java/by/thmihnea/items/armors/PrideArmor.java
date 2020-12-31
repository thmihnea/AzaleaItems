package by.thmihnea.items.armors;

import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.api.ItemizedPlayerManager;
import by.thmihnea.items.AzaleaArmor;
import by.thmihnea.items.SetBonus;
import by.thmihnea.rarity.Rarity;
import by.thmihnea.runnables.CondescendingGazeTask;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class PrideArmor extends AzaleaArmor {
    public PrideArmor(Material material, String name, Integer objectID, Integer id, Rarity rarity, Integer hp, Integer defense, Integer mana, Integer damage, Integer critDamage, Integer critChance, SetBonus halfSetBonus, SetBonus fullSetBonus) {
        super(material, name, objectID, id, rarity, hp, defense, mana, damage, critDamage, critChance, halfSetBonus, fullSetBonus);
    }

    @Override
    public void fullSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void fullSetBonus(Player paramPlayer, Event event) {
        CondescendingGazeTask condescendingGazeTask = new CondescendingGazeTask(paramPlayer);
        ItemizedPlayer itemizedPlayer = ItemizedPlayerManager.getItemizedPlayer(paramPlayer);
        itemizedPlayer.setCondescendingGaze(condescendingGazeTask);
    }

    @Override
    public void halfSetBonusNoEvent(Player paramPlayer) {

    }

    @Override
    public void halfSetBonus(Player paramPlayer, Event event) {

    }
}
