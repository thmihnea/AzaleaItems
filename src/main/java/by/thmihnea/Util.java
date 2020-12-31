package by.thmihnea;

import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.items.AzaleaArmor;
import by.thmihnea.listeners.*;
import by.thmihnea.runnables.ResoluteStanceTask;
import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Util {

    public static void setupFiles() {
        File dir = new File("plugins", "AzaleaSkills");
        if (!dir.exists()) dir.mkdir();
        if (!AzaleaItems.config.exists()) AzaleaItems.getInstance().saveDefaultConfig();
        try {
            AzaleaItems.cfg.load(AzaleaItems.config);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static void initObjects() {
        for (Player player : Bukkit.getOnlinePlayers())
            new ItemizedPlayer(player);
    }

    public static void registerEvents() {
        AzaleaItems.getInstance().events = Arrays.asList(
                new PlayerJoinListener(),
                new PlayerLeaveListener(),
                new NaturesGuardianListener(),
                new NaturesWrathListener(),
                new GentleRipple(),
                new CrashingWaves(),
                new Tremor(),
                new TectonicShift(),
                new GaleValley(),
                new FourWinds(),
                new FromTheAshes(),
                new Reincarnation(),
                new NaturesDefense(),
                new Powerlifter(),
                new FlutterListener(),
                new AssembleTheBees(),
                new LastSting(),
                new Cocoon(),
                new CondescendingGaze(),
                new SultryGaze(),
                new GluttonyListener(),
                new Ignition(),
                new DontDisturbMe(),
                new ForTheKing(),
                new TacticalPin(),
                new QuickStrike(),
                new MightyStallion(),
                new ResoluteStance(),
                new Stalemate(),
                new RisingTempo(),
                new QueensGambit(),
                new TheLastJoke(),
                new SandVeil(),
                new Smokescreen(),
                new RegretfulVoyage(),
                new Sturdy(),
                new Reflection(),
                new Shuffle(),
                new FinalGambit(),
                new Drain()
        );
        AzaleaItems.getInstance().events.forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, AzaleaItems.getInstance());
        });
    }

    public static AzaleaArmor getArmor(ItemStack itemStack) {
        if (itemStack == null) return null;
        if (itemStack.getType() == XMaterial.AIR.parseMaterial()) return null;
        NBTItem nbt = new NBTItem(itemStack);
        if (!nbt.hasKey("ID")) return null;
        int id = nbt.getInteger("ID");
        return AzaleaItems.armors.get(id);
    }

    public static boolean hasFullSet(Player player) {
        for (ItemStack is : player.getInventory().getArmorContents())
            if (is == null) return false;
        AzaleaArmor helm = getArmor(player.getInventory().getHelmet());
        AzaleaArmor chestplate = getArmor(player.getInventory().getChestplate());
        AzaleaArmor leggings = getArmor(player.getInventory().getLeggings());
        AzaleaArmor boots = getArmor(player.getInventory().getBoots());
        if (helm == null || helm.getItemStack().getType() == XMaterial.AIR.parseMaterial() ||
                chestplate == null || chestplate.getItemStack().getType() == XMaterial.AIR.parseMaterial() ||
                leggings == null || leggings.getItemStack().getType() == XMaterial.AIR.parseMaterial() ||
                boots == null || boots.getItemStack().getType() == XMaterial.AIR.parseMaterial()) return false;

        //  System.out.println(helm.getObjectID() + " " + chestplate.getObjectID() + " " + leggings.getObjectID() + " " + boots.getObjectID());
        if (helm.getObjectID().equals(chestplate.getObjectID()) && chestplate.getObjectID().equals(leggings.getObjectID()) && leggings.getObjectID().equals(boots.getObjectID()) && boots.getObjectID().equals(helm.getObjectID())) return true;
        else return false;
    }

    public static List<Integer> getHalfSetIDs(Player player) {
        Map<Integer, Integer> ap = new HashMap<>();
        List<Integer> halfSetIDs = new ArrayList<>();
        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack == null) continue;
            AzaleaArmor armor = getArmor(itemStack);
            if (armor == null) continue;
            if (!ap.keySet().contains(armor.getObjectID()))
                ap.put(armor.getObjectID(), 1);
            else ap.put(armor.getObjectID(), ap.get(armor.getObjectID()) + 1);
        }
        for (Integer i : ap.keySet())
            if (ap.get(i) >= 2) halfSetIDs.add(i);
        return halfSetIDs;
    }
}
