package by.thmihnea;

import by.thmihnea.api.ItemizedPlayer;
import by.thmihnea.item.Ability;
import by.thmihnea.item.AzaleaArmor;
import by.thmihnea.item.AzaleaItem;
import by.thmihnea.item.SetBonus;
import by.thmihnea.item.armors.*;
import by.thmihnea.item.items.*;
import by.thmihnea.listeners.*;
import by.thmihnea.rarity.Rarity;
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
                new Drain(),
                new EntityDamageByEntity(),
                new BlockPlaceListener(),
                new PlayerInteractEntityListener(),
                new RightClickAirListener(),
                new RightClickBlockAction(),
                new ShiftRightClickAirAction(),
                new ShiftRightClickBlockAction()
        );
        AzaleaItems.getInstance().events.forEach(listener -> {
            Bukkit.getPluginManager().registerEvents(listener, AzaleaItems.getInstance());
        });
    }

    public static AzaleaItem getItem(ItemStack itemStack) {
        if (itemStack == null) return null;
        if (itemStack.getType() == XMaterial.AIR.parseMaterial()) return null;
        NBTItem nbt = new NBTItem(itemStack);
        int id = nbt.getInteger("ID");
        return AzaleaItems.items.get(id);
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

    public static void addArmor(int id, AzaleaArmor azaleaArmor) {
        AzaleaItems.getInstance().addArmor(id, azaleaArmor);
    }

    public static void addItem(int id, AzaleaItem azaleaItem) {
        AzaleaItems.getInstance().addItem(id, azaleaItem);
    }

    public static void registerItems() {
        addItem(1, new Rock(XMaterial.COBBLESTONE.parseMaterial(), "Rock", "SWORD", 1, Rarity.COMMON, 0, 0, 20, 0, 5, 0, 0, Ability.GOLIATH));
        addItem(2, new TreeBranch(XMaterial.STICK.parseMaterial(), "Tree Branch", "SWORD", 2, Rarity.COMMON, 0, 0, 15, 0, 0, 0, 0, null, false));
        addItem(3, new KitchenKnife(XMaterial.IRON_SWORD.parseMaterial(), "Kitchen Knife", "SWORD", 3, Rarity.COMMON, 0, 0, 0, 20, 0, 0, 0, null, false));
        addItem(4, new FlameStaff(XMaterial.BLAZE_ROD.parseMaterial(), "Flame Staff", "MAGIC WEAPON", 4, Rarity.COMMON, 0, 0, 0, 15, 0, 40, 0, null, false, false));
        addItem(5, new IceWand(XMaterial.DIAMOND_HOE.parseMaterial(), "Ice Wand", "MAGIC WEAPON", 5, Rarity.COMMON, 0, 0, 0, 0, 5, 35, 0, null, false, false));
        addItem(6, new ThunderBattleaxe(XMaterial.GOLDEN_AXE.parseMaterial(), "Thunder Battleaxe", "SWORD", 6, Rarity.COMMON, 0, 20, 30, 0, 0, 0, 0, null, false, false));
        addItem(7, new DulledKnife(XMaterial.STONE_SWORD.parseMaterial(), "Dulled Knife", "SWORD", 7, Rarity.COMMON, 0, 0, 30, 20, 0, 0, 0, null, false ,false));
        addItem(8, new OreClub(XMaterial.STONE_SWORD.parseMaterial(), "Ore Club", "SWORD", 8, Rarity.UNCOMMON, 100, 0, 50, 0, 0, 0, 0, null, false, false));
        addItem(9, new BladeOfDusk(XMaterial.WOODEN_SWORD.parseMaterial(), "Blade of Dusk", "SWORD", 9, Rarity.UNCOMMON, 0, 0, 45, 25, 0, 0, 0, null, false, false));
        addItem(10, new RapierOfDawn(XMaterial.GOLDEN_SWORD.parseMaterial(), "Rapier of Dawn", "SWORD", 10, Rarity.UNCOMMON, 0, 0, 50, 20, 0, 0, 0, null, false, false));
        addItem(11, new MetalBat(XMaterial.IRON_SWORD.parseMaterial(), "Metal Bat", "SWORD", 11, Rarity.UNCOMMON, 0, 0, 50, 35, 5, 0, 30, Ability.HOMERUN, false, false));
        addItem(12, new Lockpick(XMaterial.BLAZE_ROD.parseMaterial(), "Lockpick", "ITEM", 12, Rarity.UNCOMMON, 0, 0, 15, 20, 5, 0, 100, Ability.PICKPOCKET, false, false));
        addItem(13, new IcePick(XMaterial.ICE.parseMaterial(), "Ice Pick", "SWORD", 13, Rarity.UNCOMMON, 30, 10, 60, 0, 0, 0, 0, null, false, false));
        addItem(14, new FreezingWarhammer(XMaterial.DIAMOND_AXE.parseMaterial(), "Freezing Warhammer", "SWORD", 14, Rarity.UNCOMMON, 0, 0, 70, 0, 0, 0, 0, null, false, false));
        addItem(15, new RaptorClaws(XMaterial.DIAMOND_SWORD.parseMaterial(), "Raptor Claws", "SWORD", 15, Rarity.UNCOMMON, 0, 0, 30, 50, 15, 0, 0, null, false, false));
        /* ACCESSORY */ addItem(16, new EspersTome(XMaterial.BOOK.parseMaterial(), "Esper's Tome", "ACCESSORY", 16, Rarity.UNCOMMON, 40, 20, 0, 0, 0, 35, 0, null, false, false));
        addItem(17, new ScorchedBranch(XMaterial.BLAZE_ROD.parseMaterial(), "Scorched Branch", "SWORD", 17, Rarity.RARE, 35, 0, 75, 20, 5, 20, 0, null, false, false));
        addItem(18, new BladeOfTheFlowingRiver(XMaterial.IRON_SWORD.parseMaterial(), "Blade of the Flowing River", "SWORD", 18, Rarity.RARE, 0, 0, 75, 15, 10, 0, 0, null, false, false));
        addItem(19, new GuardianLongblade(XMaterial.DIAMOND_SWORD.parseMaterial(), "Guardian Longblade", "SWORD", 19, Rarity.RARE, 0, 0, 80, 30, 15, 0, 0, null, false, false));
        addItem(20, new ZeroKLance(XMaterial.DIAMOND_SWORD.parseMaterial(), "Zero K Lance", "SWORD", 20, Rarity.RARE, 0, 0, 50, 50, 15, 0, 0, null, false, false));
        /* ACCESSORY */addItem(21, new TomeOfInexhaustability(XMaterial.BOOK.parseMaterial(), "Tome of Inexhaustability", "ACCESSORY", 21, Rarity.RARE, 100, 30, 0, 0, 0, 40, 0, Ability.INEXHAUSTABILITY, false, false));
        addItem(22, new TungstenOrb(XMaterial.IRON_SWORD.parseMaterial(), "Tungsten Orb", "SWORD", 22, Rarity.RARE, 200, 50, 100, 0, 0, 0, 0, null, false ,false));
        addItem(23, new FleurRouge(XMaterial.RED_TULIP.parseMaterial(), "Fleur Rouge", "ITEM", 23, Rarity.RARE, 0, 0, 0, 0, 0, 0, 75, Ability.BLOODLUST, false, false));
        addItem(24, new MagnumOpus(XMaterial.NOTE_BLOCK.parseMaterial(), "Magnum Opus", "ITEM", 24, Rarity.RARE, 0, 0, 0, 45, 15, 50, 0, null, false, false));
        addItem(25, new ImminentPiercer(XMaterial.IRON_SWORD.parseMaterial(), "Imminent Piercer", "SWORD", 25, Rarity.EPIC, 0, 0, 120, 50, 15, 0, 0, null, false, false));
        addItem(26, new SolarBlade(XMaterial.BLAZE_ROD.parseMaterial(), "Solar Blade", "MAGIC WEAPON", 26, Rarity.EPIC, 0, 0, 0, 50, 25, 75, 0, null, false, false));
        addItem(27, new DawnOfAmaterasu(XMaterial.ENCHANTED_BOOK.parseMaterial(), "Dawn of Amaterasu", "ITEM", 27, Rarity.EPIC, 150, 40, 0, 0, 0, 0, 0, Ability.HEAL, false, false));
        addItem(28, new FifthNirvana(XMaterial.ENCHANTED_BOOK.parseMaterial(), "Fifth Nirvana", "ITEM", 28, Rarity.EPIC, 150, 40, 0, 0, 0, 0, 0, Ability.GREATER_GOOD, false, false));
        addItem(29, new LostCity(XMaterial.PRISMARINE.parseMaterial(), "Lost City", "ITEM", 29, Rarity.EPIC, 125, 30, 0, 0, 20, 0, 0, Ability.SHURIKEN, false, false));
        addItem(30, new Bellerophon(XMaterial.LEAD.parseMaterial(), "Bellerophon", "ITEM", 30, Rarity.EPIC, 100, 30, 0, 0, 0, 0, 0, Ability.RIDER, false, false));
        addItem(31, new Nova(XMaterial.FIREWORK_ROCKET.parseMaterial(), "Nova", "ITEM", 31, Rarity.EPIC, 0, 0, 0, 0, 0, 0, 0, Ability.BLIND, false, false));
        addItem(32, new HiddenCrane(XMaterial.ENCHANTED_BOOK.parseMaterial(), "Hidden Crane", "ITEM", 32, Rarity.EPIC, 150, 0, 0, 0, 0, 0, 0, Ability.SNEAK, false, false));
        addItem(33, new BehemothBlade(XMaterial.IRON_SWORD.parseMaterial(), "Behemoth Blade", "SWORD", 33, Rarity.EPIC, 0, 0, 150, 30, 0, 0, 0, null, false, false));
        addItem(34, new MoonlightScythe(XMaterial.IRON_HOE.parseMaterial(), "Moonlight Scythe", "SWORD", 34, Rarity.LEGENDARY, 0, 150, 50, 40, 0, 0, 0, Ability.HIDDEN_SHADOWS, false, false));
        addItem(35, new MeteorClub(XMaterial.DIAMOND_SWORD.parseMaterial(), "Meteor Club", "SWORD", 35, Rarity.LEGENDARY, 100, 0, 175, 0, 0, 0, 50, Ability.METEOR_SHOWER, false, false));
        addItem(36, new AzaleanHammer(XMaterial.GOLDEN_PICKAXE.parseMaterial(), "Azalean Hammer", "SWORD", 36, Rarity.LEGENDARY, 0, 0, 200, 0, 0, 0, 75, Ability.JUSTICE, false, false));
        addItem(37, new CrescentChakram(XMaterial.CLOCK.parseMaterial(), "Crescent Chakram", "SWORD", 37, Rarity.LEGENDARY, 0, 0, 150, 50, 30, 0, 0, Ability.CYCLICAL_CUTS, false, false));
    }

    public static void registerArmors() {
        addArmor(1, new NaturesArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Nature's Helmet", 1, 1, Rarity.UNCOMMON, 450, 15, 75, 0, 0, 0, SetBonus.NATURES_GUARDIAN, SetBonus.NATURES_WRATH));
        addArmor(2, new NaturesArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Nature's Chestplate", 1, 2, Rarity.UNCOMMON, 1200, 40, 200, 0, 0, 0, SetBonus.NATURES_GUARDIAN, SetBonus.NATURES_WRATH));
        addArmor(3, new NaturesArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Nature's Leggings", 1, 3, Rarity.UNCOMMON, 900, 30, 150, 0, 0, 0, SetBonus.NATURES_GUARDIAN, SetBonus.NATURES_WRATH));
        addArmor(4, new NaturesArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Nature's Chestplate", 1, 4, Rarity.UNCOMMON, 450, 15, 75, 0, 0, 0, SetBonus.NATURES_GUARDIAN, SetBonus.NATURES_WRATH));
        addArmor(5, new UndinesArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Undine's Helmet", 2, 5, Rarity.RARE, 675, 15, 90, 0, 0, 0, SetBonus.GENTLE_RIPPLE, SetBonus.CRASHING_WAVES));
        addArmor(6, new UndinesArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Undine's Chestplate", 2, 6, Rarity.RARE, 1800, 40, 240, 0, 0, 0, SetBonus.GENTLE_RIPPLE, SetBonus.CRASHING_WAVES));
        addArmor(7, new UndinesArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Undine's Leggings", 2, 7, Rarity.RARE, 1350, 30, 180, 0, 0, 0, SetBonus.GENTLE_RIPPLE, SetBonus.CRASHING_WAVES));
        addArmor(8, new UndinesArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Undine's Chestplate", 2, 8, Rarity.RARE, 675, 15, 90, 0, 0, 0, SetBonus.GENTLE_RIPPLE, SetBonus.CRASHING_WAVES));
        addArmor(9, new OureasGuardArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Ourea's Guard Helmet", 3, 9, Rarity.EPIC, 1125, 90, 0, 45, 0, 0, SetBonus.TREMOR, SetBonus.TECTONIC_SHIFT));
        addArmor(10, new OureasGuardArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Ourea's Guard Chestplate", 3, 10, Rarity.EPIC, 3000, 240, 0, 120, 0, 0, SetBonus.TREMOR, SetBonus.TECTONIC_SHIFT));
        addArmor(11, new OureasGuardArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Ourea's Guard Leggings", 3, 11, Rarity.EPIC, 2250, 180, 0, 90, 0, 0, SetBonus.TREMOR, SetBonus.TECTONIC_SHIFT));
        addArmor(12, new OureasGuardArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Ourea's Guard Boots", 3, 12, Rarity.EPIC, 1125, 90, 0, 45, 0, 0, SetBonus.TREMOR, SetBonus.TECTONIC_SHIFT));
        addArmor(13, new ZephyrsArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Zephyr's Helmet", 4, 13, Rarity.RARE, 115, 10, 0, 70, 15, 5, SetBonus.GALE_VALLEY, SetBonus.FOUR_WINDS));
        addArmor(14, new ZephyrsArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Zephyr's Chestplate", 4, 14, Rarity.RARE, 300, 20, 0, 180, 40, 15, SetBonus.GALE_VALLEY, SetBonus.FOUR_WINDS));
        addArmor(15, new ZephyrsArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Zephyr's Leggings", 4, 15, Rarity.RARE, 220, 10, 0, 130, 30, 10, SetBonus.GALE_VALLEY, SetBonus.FOUR_WINDS));
        addArmor(16, new ZephyrsArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Zephyr's Boots", 4, 16, Rarity.RARE, 115, 10, 0, 70, 15, 5, SetBonus.GALE_VALLEY, SetBonus.FOUR_WINDS));
        addArmor(17, new InfernalArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Infernal Helmet", 5, 17, Rarity.EPIC, 900, 25, 0, 105, 0, 0, SetBonus.FROM_THE_ASHES, SetBonus.REINCARNATION));
        addArmor(18, new InfernalArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Infernal Chestplate", 5, 18, Rarity.EPIC, 2400, 60, 0, 280, 0, 0, SetBonus.FROM_THE_ASHES, SetBonus.REINCARNATION));
        addArmor(19, new InfernalArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Infernal Leggings", 5, 19, Rarity.EPIC, 1800, 40, 0, 210, 0, 0, SetBonus.FROM_THE_ASHES, SetBonus.REINCARNATION));
        addArmor(20, new InfernalArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Infernal Boots", 5, 20, Rarity.EPIC, 900, 25, 0, 105, 0, 0, SetBonus.FROM_THE_ASHES, SetBonus.REINCARNATION));
        addArmor(21, new BeetleguardArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Beetleguard Helmet", 6, 21, Rarity.UNCOMMON, 525, 75, 0, 0, 15, 5, SetBonus.NATURAL_DEFENSE, SetBonus.POWERLIFTER));
        addArmor(22, new BeetleguardArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Beetleguard Chestplate", 6, 22, Rarity.UNCOMMON, 1400, 200, 0, 0, 40, 14, SetBonus.NATURAL_DEFENSE, SetBonus.POWERLIFTER));
        addArmor(23, new BeetleguardArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Beetleguard Leggings", 6, 23, Rarity.UNCOMMON, 1050, 150, 0, 0, 30, 11, SetBonus.NATURAL_DEFENSE, SetBonus.POWERLIFTER));
        addArmor(24, new BeetleguardArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Beetleguard Boots", 6, 24, Rarity.UNCOMMON, 525, 75 ,0, 0, 15, 5, SetBonus.NATURAL_DEFENSE, SetBonus.POWERLIFTER));
        addArmor(25, new ButterflyCloak(XMaterial.LEATHER_HELMET.parseMaterial(), "Butterfly Cloak Helmet", 7, 25, Rarity.RARE, 135, 5, 0, 0, 27, 3, SetBonus.FLUTTER, SetBonus.MIMICRY));
        addArmor(26, new ButterflyCloak(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Butterfly Cloak Chestplate", 7, 26, Rarity.RARE, 360, 12, 0, 0, 72, 8, SetBonus.FLUTTER, SetBonus.MIMICRY));
        addArmor(27, new ButterflyCloak(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Butterfly Cloak Leggings", 7, 27, Rarity.RARE, 270, 8, 0, 0, 54, 6, SetBonus.FLUTTER, SetBonus.MIMICRY));
        addArmor(28, new ButterflyCloak(XMaterial.LEATHER_BOOTS.parseMaterial(), "Butterfly Cloak Boots", 7, 28, Rarity.RARE, 135, 5 ,0, 0, 27, 3, SetBonus.FLUTTER, SetBonus.MIMICRY));
        addArmor(29, new BumblebeeArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Bumblebee Helmet", 8, 29, Rarity.UNCOMMON, 375, 30, 0, 40, 0, 0, SetBonus.ASSEMBLE_THE_BEES, SetBonus.LAST_STING));
        addArmor(30, new BumblebeeArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Bumblebee Chestplate", 8, 30, Rarity.UNCOMMON, 1000, 80, 0, 80, 0, 0, SetBonus.ASSEMBLE_THE_BEES, SetBonus.LAST_STING));
        addArmor(31, new BumblebeeArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Bumblebee Leggings", 8, 31, Rarity.UNCOMMON, 750, 60, 0, 70, 0, 0, SetBonus.ASSEMBLE_THE_BEES, SetBonus.LAST_STING));
        addArmor(32, new BumblebeeArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Bumblebee Boots", 8, 32, Rarity.UNCOMMON, 375, 30 ,0, 40, 0, 0, SetBonus.ASSEMBLE_THE_BEES, SetBonus.LAST_STING));
        addArmor(33, new SpiderVeil(XMaterial.LEATHER_HELMET.parseMaterial(), "Spider Veil Helmet", 9, 33, Rarity.RARE, 450, 60, 0, 30, 0, 0, SetBonus.COCOON, SetBonus.SNARE));
        addArmor(34, new SpiderVeil(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Spider Veil Chestplate", 9, 34, Rarity.RARE, 1200, 160, 0, 80, 0, 0, SetBonus.COCOON, SetBonus.SNARE));
        addArmor(35, new SpiderVeil(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Spider Veil Leggings", 9, 35, Rarity.RARE, 900, 120, 0, 60, 0, 0, SetBonus.COCOON, SetBonus.SNARE));
        addArmor(36, new SpiderVeil(XMaterial.LEATHER_BOOTS.parseMaterial(), "Spider Veil Boots", 9, 36, Rarity.RARE, 450, 60 ,0, 30, 0, 0, SetBonus.COCOON, SetBonus.SNARE));
        addArmor(37, new MantisArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Mantis Helmet", 10, 37, Rarity.UNCOMMON, 120, 15, 90, 0, 0, 0, SetBonus.PRAYER, SetBonus.PERFECT_TIMING));
        addArmor(38, new MantisArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Mantis Chestplate", 10, 38, Rarity.UNCOMMON, 320, 40, 240, 0, 0, 0, SetBonus.PRAYER, SetBonus.PERFECT_TIMING));
        addArmor(39, new MantisArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Mantis Leggings", 10, 39, Rarity.UNCOMMON, 240, 30, 180, 0, 0, 0, SetBonus.PRAYER, SetBonus.PERFECT_TIMING));
        addArmor(40, new MantisArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Mantis Boots", 10, 40, Rarity.UNCOMMON, 120, 15 ,90, 0, 0, 0, SetBonus.PRAYER, SetBonus.PERFECT_TIMING));
        addArmor(41, new PrideArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Pride Helmet", 11, 41, Rarity.RARE, 225, 30, 0, 105, 0, 0, SetBonus.CONDESCENDING_GAZE, SetBonus.BLIND_PRIDE));
        addArmor(42, new PrideArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Pride Chestplate", 11, 42, Rarity.RARE, 600, 80, 0, 280, 0, 0, SetBonus.CONDESCENDING_GAZE, SetBonus.BLIND_PRIDE));
        addArmor(43, new PrideArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Pride Leggings", 11, 43, Rarity.RARE, 450, 60, 0, 210, 0, 0, SetBonus.CONDESCENDING_GAZE, SetBonus.BLIND_PRIDE));
        addArmor(44, new PrideArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Pride Boots", 11, 44, Rarity.RARE, 225, 30 ,0, 105, 0, 0, SetBonus.CONDESCENDING_GAZE, SetBonus.BLIND_PRIDE));
        addArmor(45, new LustArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Lust Helmet", 12, 45, Rarity.EPIC, 600, 75, 60, 0, 0, 0, SetBonus.SULTRY_GAZE, SetBonus.DIE_FOR_ME));
        addArmor(46, new LustArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Lust Chestplate", 12, 46, Rarity.EPIC, 1600, 200, 160, 0, 0, 0, SetBonus.SULTRY_GAZE, SetBonus.DIE_FOR_ME));
        addArmor(47, new LustArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Lust Leggings", 12, 47, Rarity.EPIC, 1200, 150, 120, 0, 0, 0, SetBonus.SULTRY_GAZE, SetBonus.DIE_FOR_ME));
        addArmor(48, new LustArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Lust Boots", 12, 48, Rarity.EPIC, 600, 75,60, 0, 0, 0, SetBonus.SULTRY_GAZE, SetBonus.DIE_FOR_ME));
        addArmor(49, new GluttonyArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Gluttony Helmet", 13, 49, Rarity.LEGENDARY, 1050, 60, 0, 45, 0, 0, SetBonus.PIG_OUT, SetBonus.CONVERSION));
        addArmor(50, new GluttonyArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Gluttony Chestplate", 13, 50, Rarity.LEGENDARY, 2800, 160, 0, 120, 0, 0, SetBonus.PIG_OUT, SetBonus.CONVERSION));
        addArmor(51, new GluttonyArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Gluttony Leggings", 13, 51, Rarity.LEGENDARY, 2100, 120, 0, 90, 0, 0, SetBonus.PIG_OUT, SetBonus.CONVERSION));
        addArmor(52, new GluttonyArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Gluttony Boots", 13, 52, Rarity.LEGENDARY, 1050, 60,0, 45, 0, 0, SetBonus.PIG_OUT, SetBonus.CONVERSION));
        addArmor(53, new WrathArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Wrath Helmet", 14, 53, Rarity.RARE, 525, 45, 0, 55, 20, 5, SetBonus.IGNITION, SetBonus.CALM_IN_THE_STORM));
        addArmor(54, new WrathArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Wrath Chestplate", 14, 54, Rarity.RARE, 1400, 120, 0, 140, 52, 12, SetBonus.IGNITION, SetBonus.CALM_IN_THE_STORM));
        addArmor(55, new WrathArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Wrath Leggings", 14, 55, Rarity.RARE, 1050, 90, 0, 100, 38, 8, SetBonus.IGNITION, SetBonus.CALM_IN_THE_STORM));
        addArmor(56, new WrathArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Wrath Boots", 14, 56, Rarity.RARE, 525, 45,0, 55, 20, 5, SetBonus.IGNITION, SetBonus.CALM_IN_THE_STORM));
        addArmor(57, new SlothArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Sloth Helmet", 15, 57, Rarity.EPIC, 750, 60, 0, 0, 0, 0, SetBonus.BEAUTIFUL_DAY, SetBonus.DONT_DISTURB_ME));
        addArmor(58, new SlothArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Sloth Chestplate", 15, 58, Rarity.EPIC, 2000, 160, 0, 0, 0, 0, SetBonus.BEAUTIFUL_DAY, SetBonus.DONT_DISTURB_ME));
        addArmor(59, new SlothArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Sloth Leggings", 15, 59, Rarity.EPIC, 1500, 120, 0, 0, 0, 0, SetBonus.BEAUTIFUL_DAY, SetBonus.DONT_DISTURB_ME));
        addArmor(60, new SlothArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Sloth Boots", 15, 60, Rarity.EPIC, 750, 60,0, 0, 0, 0, SetBonus.BEAUTIFUL_DAY, SetBonus.DONT_DISTURB_ME));
        addArmor(61, new PawnArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Pawn Helmet", 16, 61, Rarity.COMMON, 100, 11, 0, 15, 0, 0, SetBonus.FOR_THE_KING, SetBonus.PROMOTION));
        addArmor(62, new PawnArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Pawn Chestplate", 16, 62, Rarity.COMMON, 260, 31, 0, 40, 0, 0, SetBonus.FOR_THE_KING, SetBonus.PROMOTION));
        addArmor(63, new PawnArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Pawn Leggings", 16, 63, Rarity.COMMON, 190, 22, 0, 30, 0, 0, SetBonus.FOR_THE_KING, SetBonus.PROMOTION));
        addArmor(64, new PawnArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Pawn Boots", 16, 64, Rarity.COMMON, 100, 11,0, 15, 0, 0, SetBonus.FOR_THE_KING, SetBonus.PROMOTION));
        addArmor(65, new BishopArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Bishop Helmet", 17, 65, Rarity.UNCOMMON, 75, 15, 45, 0, 0, 0, SetBonus.TACTICAL_PIN, SetBonus.QUICK_STRIKE));
        addArmor(66, new BishopArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Bishop Chestplate", 17, 66, Rarity.UNCOMMON, 200, 40, 120, 0, 0, 0, SetBonus.TACTICAL_PIN, SetBonus.QUICK_STRIKE));
        addArmor(67, new BishopArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Bishop Leggings", 17, 67, Rarity.UNCOMMON, 150, 30, 90, 0, 0, 0, SetBonus.TACTICAL_PIN, SetBonus.QUICK_STRIKE));
        addArmor(68, new BishopArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Bishop Boots", 17, 68, Rarity.UNCOMMON, 75, 15,45, 0, 0, 0, SetBonus.TACTICAL_PIN, SetBonus.QUICK_STRIKE));
        addArmor(69, new KnightArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Knight Helmet", 18, 69, Rarity.RARE, 375, 55, 0, 55, 0, 0, SetBonus.THE_POWER_OF_TWO, SetBonus.MIGHTY_STALLION));
        addArmor(70, new KnightArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Knight Chestplate", 18, 70, Rarity.RARE, 1000, 140, 0, 140, 0, 0, SetBonus.THE_POWER_OF_TWO, SetBonus.MIGHTY_STALLION));
        addArmor(71, new KnightArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Knight Leggings", 18, 71, Rarity.RARE, 750, 110, 0, 110, 0, 0, SetBonus.THE_POWER_OF_TWO, SetBonus.MIGHTY_STALLION));
        addArmor(72, new KnightArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Knight Boots", 18, 72, Rarity.RARE, 375, 55,0, 55, 0, 0, SetBonus.THE_POWER_OF_TWO, SetBonus.MIGHTY_STALLION));
        addArmor(73, new RookArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Rook Helmet", 19, 73, Rarity.EPIC, 900, 135, 0, 38, 0, 0, SetBonus.RESOLUTE_STANCE, SetBonus.ASSEMBLE_THE_CASTLE));
        addArmor(74, new RookArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Rook Chestplate", 19, 74, Rarity.EPIC, 2400, 360, 0, 100, 0, 0, SetBonus.RESOLUTE_STANCE, SetBonus.ASSEMBLE_THE_CASTLE));
        addArmor(75, new RookArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Rook Leggings", 19, 75, Rarity.EPIC, 1800, 270, 0, 74, 0, 0, SetBonus.RESOLUTE_STANCE, SetBonus.ASSEMBLE_THE_CASTLE));
        addArmor(76, new RookArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Rook Boots", 19, 76, Rarity.EPIC, 900, 135,0, 38, 0, 0, SetBonus.RESOLUTE_STANCE, SetBonus.ASSEMBLE_THE_CASTLE));
        addArmor(77, new KingsArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "King's Helmet", 20, 77, Rarity.LEGENDARY, 600, 45, 0, 60, 0, 0, SetBonus.STALEMATE, SetBonus.CHECKMATE));
        addArmor(78, new KingsArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "King's Chestplate", 20, 78, Rarity.LEGENDARY, 1600, 120, 0, 160, 0, 0, SetBonus.STALEMATE, SetBonus.CHECKMATE));
        addArmor(79, new KingsArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "King's Leggings", 20, 79, Rarity.LEGENDARY, 1200, 90, 0, 120, 0, 0, SetBonus.STALEMATE, SetBonus.CHECKMATE));
        addArmor(80, new KingsArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "King's Boots", 20, 80, Rarity.LEGENDARY, 600, 45,0, 60, 0, 0, SetBonus.STALEMATE, SetBonus.CHECKMATE));
        addArmor(81, new QueensArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Queen's Helmet", 21, 81, Rarity.LEGENDARY, 525, 40, 0, 60, 0, 0, SetBonus.RISING_TEMPO, SetBonus.QUEENS_GAMBIT));
        addArmor(82, new QueensArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Queen's Chestplate", 21, 82, Rarity.LEGENDARY, 1400, 100, 0, 160, 0, 0, SetBonus.RISING_TEMPO, SetBonus.QUEENS_GAMBIT));
        addArmor(83, new QueensArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Queen's Leggings", 21, 83, Rarity.LEGENDARY, 1050, 70, 0, 120, 0, 0, SetBonus.RISING_TEMPO, SetBonus.QUEENS_GAMBIT));
        addArmor(84, new QueensArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Queen's Boots", 21, 84, Rarity.LEGENDARY, 525, 40,0, 60, 0, 0, SetBonus.RISING_TEMPO, SetBonus.QUEENS_GAMBIT));
        addArmor(85, new JesterArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Jester Helmet", 22, 85, Rarity.UNCOMMON, 180, 25, 0, 15, 25, 1, SetBonus.PICK_A_CARD, SetBonus.THE_LAST_JOKE));
        addArmor(86, new JesterArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Jester Chestplate", 22, 86, Rarity.UNCOMMON, 480, 60, 0, 40, 60, 4, SetBonus.PICK_A_CARD, SetBonus.THE_LAST_JOKE));
        addArmor(87, new JesterArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Jester Leggings", 22, 87, Rarity.UNCOMMON, 360, 40, 0, 30, 40, 3, SetBonus.PICK_A_CARD, SetBonus.THE_LAST_JOKE));
        addArmor(88, new JesterArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Jester Boots", 22, 88, Rarity.UNCOMMON, 180, 25,0, 15, 25, 2, SetBonus.PICK_A_CARD, SetBonus.THE_LAST_JOKE));
        addArmor(89, new ChronoArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Chrono Helmet", 23, 89, Rarity.RARE, 150, 45, 30, 45, 0, 0, SetBonus.SUNDIAL, SetBonus.MOONLIT_GAZE));
        addArmor(90, new ChronoArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Chrono Chestplate", 23, 90, Rarity.RARE, 400, 120, 80, 120, 0, 0, SetBonus.SUNDIAL, SetBonus.MOONLIT_GAZE));
        addArmor(91, new ChronoArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Chrono Leggings", 23, 91, Rarity.RARE, 300, 90, 60, 90, 0, 0, SetBonus.SUNDIAL, SetBonus.MOONLIT_GAZE));
        addArmor(92, new ChronoArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Chrono Boots", 23, 92, Rarity.RARE, 150, 45,30, 45, 0, 0, SetBonus.SUNDIAL, SetBonus.MOONLIT_GAZE));
        addArmor(93, new SandArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Sand Helmet", 24, 93, Rarity.UNCOMMON, 90, 18, 0, 30, 0, 0, SetBonus.SANDSTORM, SetBonus.SAND_VEIL));
        addArmor(94, new SandArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Sand Chestplate", 24, 94, Rarity.UNCOMMON, 240, 48, 0, 80, 0, 0, SetBonus.SANDSTORM, SetBonus.SAND_VEIL));
        addArmor(95, new SandArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Sand Leggings", 24, 95, Rarity.UNCOMMON, 180, 36, 0, 60, 0, 0, SetBonus.SANDSTORM, SetBonus.SAND_VEIL));
        addArmor(96, new SandArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Sand Boots", 24, 96, Rarity.UNCOMMON, 90, 18,0, 30, 0, 0, SetBonus.SANDSTORM, SetBonus.SAND_VEIL));
        addArmor(97, new SmokeArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Smoke Helmet", 25, 97, Rarity.EPIC, 375, 30, 0, 45, 0, 0, SetBonus.SMOKESCREEN, SetBonus.ASPHIXIATION));
        addArmor(98, new SmokeArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Smoke Chestplate", 25, 98, Rarity.EPIC, 1000, 80, 0, 120, 0, 0, SetBonus.SMOKESCREEN, SetBonus.ASPHIXIATION));
        addArmor(99, new SmokeArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Smoke Leggings", 25, 99, Rarity.EPIC, 750, 60, 0, 90, 0, 0, SetBonus.SMOKESCREEN, SetBonus.ASPHIXIATION));
        addArmor(100, new SmokeArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Smoke Boots", 25, 100, Rarity.EPIC, 375, 30,0, 45, 0, 0, SetBonus.SMOKESCREEN, SetBonus.ASPHIXIATION));
        addArmor(101, new ElectricArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Electric Helmet", 26, 101, Rarity.RARE, 265, 25, 0, 40, 0, 0, SetBonus.REGRETFUL_VOLTAGE, SetBonus.OVERDRIVE));
        addArmor(102, new ElectricArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Electric Chestplate", 26, 102, Rarity.RARE, 700, 60, 0, 100, 0, 0, SetBonus.REGRETFUL_VOLTAGE, SetBonus.OVERDRIVE));
        addArmor(103, new ElectricArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Electric Leggings", 26, 103, Rarity.RARE, 520, 40, 0, 70, 0, 0, SetBonus.REGRETFUL_VOLTAGE, SetBonus.OVERDRIVE));
        addArmor(104, new ElectricArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Electric Boots", 26, 104, Rarity.RARE, 265, 25,0, 40, 0, 0, SetBonus.REGRETFUL_VOLTAGE, SetBonus.OVERDRIVE));
        addArmor(105, new RookieArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Rookie Helmet", 27, 105, Rarity.COMMON, 115, 15, 0, 25, 10, 3, SetBonus.SPRING_BURST, SetBonus.STURDY));
        addArmor(106, new RookieArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Rookie Chestplate", 27, 106, Rarity.COMMON, 300, 40, 0, 60, 20, 8, SetBonus.SPRING_BURST, SetBonus.STURDY));
        addArmor(107, new RookieArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Rookie Leggings", 27, 107, Rarity.COMMON, 225, 30, 0, 45, 15, 6, SetBonus.SPRING_BURST, SetBonus.STURDY));
        addArmor(108, new RookieArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Rookie Boots", 27, 108, Rarity.COMMON, 115, 15,0, 25, 10, 3, SetBonus.SPRING_BURST, SetBonus.STURDY));
        addArmor(109, new GhostArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Ghost Helmet", 28, 109, Rarity.RARE, 450, 75, 0, 45, 0, 0, SetBonus.PHANTOM_PLAYTIME, SetBonus.GASTLY_GARDEN));
        addArmor(110, new GhostArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Ghost Chestplate", 28, 110, Rarity.RARE, 1200, 200, 0, 120, 0, 0, SetBonus.PHANTOM_PLAYTIME, SetBonus.GASTLY_GARDEN));
        addArmor(111, new GhostArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Ghost Leggings", 28, 111, Rarity.RARE, 900, 150, 0, 90, 0, 0, SetBonus.PHANTOM_PLAYTIME, SetBonus.GASTLY_GARDEN));
        addArmor(112, new GhostArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Ghost Boots", 28, 112, Rarity.RARE, 450, 75,0, 45, 0, 0, SetBonus.PHANTOM_PLAYTIME, SetBonus.GASTLY_GARDEN));
        addArmor(113, new MirrorArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Mirror Helmet", 29, 113, Rarity.EPIC, 300, 45, 0, 45, 0, 0, SetBonus.REFLECTION, SetBonus.BROKEN_GLASS));
        addArmor(114, new MirrorArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Mirror Chestplate", 29, 114, Rarity.EPIC, 800, 120, 0, 120, 0, 0, SetBonus.REFLECTION, SetBonus.BROKEN_GLASS));
        addArmor(115, new MirrorArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Mirror Leggings", 29, 115, Rarity.EPIC, 600, 90, 0, 90, 0, 0, SetBonus.REFLECTION, SetBonus.BROKEN_GLASS));
        addArmor(116, new MirrorArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Mirror Boots", 29, 116, Rarity.EPIC, 300, 45,0, 45, 0, 0, SetBonus.REFLECTION, SetBonus.BROKEN_GLASS));
        addArmor(117, new GamblersArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Gambler's Helmet", 30, 117, Rarity.UNCOMMON, 150, 30, 30, 0, 10, 2, SetBonus.SHUFFLE, SetBonus.FINAL_GAMBIT));
        addArmor(118, new GamblersArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Gambler's Chestplate", 30, 118, Rarity.UNCOMMON, 400, 80, 80, 0, 20, 4, SetBonus.SHUFFLE, SetBonus.FINAL_GAMBIT));
        addArmor(119, new GamblersArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Gambler's Leggings", 30, 119, Rarity.UNCOMMON, 300, 60, 60, 0, 15, 3, SetBonus.SHUFFLE, SetBonus.FINAL_GAMBIT));
        addArmor(120, new GamblersArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Gambler's Boots", 30, 120, Rarity.UNCOMMON, 150, 30,30, 0, 10, 2, SetBonus.SHUFFLE, SetBonus.FINAL_GAMBIT));
        addArmor(121, new SynthesisArmor(XMaterial.LEATHER_HELMET.parseMaterial(), "Synthesis Helmet", 31, 121, Rarity.COMMON, 30, 0, 0, 0, 0, 0, SetBonus.OVERGROWTH, SetBonus.DRAIN));
        addArmor(122, new SynthesisArmor(XMaterial.LEATHER_CHESTPLATE.parseMaterial(), "Synthesis Chestplate", 31, 122, Rarity.COMMON, 80, 0, 0, 0, 0, 0, SetBonus.OVERGROWTH, SetBonus.DRAIN));
        addArmor(123, new SynthesisArmor(XMaterial.LEATHER_LEGGINGS.parseMaterial(), "Synthesis Leggings", 31, 123, Rarity.COMMON, 60, 0, 0, 0, 0, 0, SetBonus.OVERGROWTH, SetBonus.DRAIN));
        addArmor(124, new SynthesisArmor(XMaterial.LEATHER_BOOTS.parseMaterial(), "Synthesis Boots", 31, 124, Rarity.COMMON, 30, 0,0, 0, 0, 0, SetBonus.OVERGROWTH, SetBonus.DRAIN));
    }
}
