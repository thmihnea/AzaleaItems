package by.thmihnea.item;

import java.util.Arrays;
import java.util.List;

public enum Ability {

    GOLIATH("&6Passive Ability: &fGoliath", "&7Gain a 0.1% chance to K.O. your opponent", "&7in one shot."),
    HOMERUN("&6Item Ability: &aHomerun &e&l(RIGHT-CLICK)", "&7Right-clicking on an enemy launches them", "&7into the air &6&nREALLY &7high up.", "&7This ability costs &b30 &7mana and has", "&7a &630 &7second cooldown."),
    PICKPOCKET("&6Item Ability: &aPickpocket &e&l(RIGHT-CLICK)", "&7Right-clicking will make you enter in &6Thief Mode&7,", "&7where you next attack will steal a random amount", "&7of money from your opponent. ($1-1000)", "&7This ability costs &b100 &7mana and", "&7has a &63 &7minute cooldown!"),
    INEXHAUSTABILITY("&6Passive Ability: &9Inexhaustability", "&7Mana is used at &670% &7of its", "&7usual rate."),
    BLOODLUST("&6Item Ability: &9Bloodlust &e&l(RIGHT-CLICK)", "&7Grants &cStrength I &7for &c30 &7seconds.", "&7This ability costs &b75 &7mana."),
    HEAL("&6Item Ability: &5Heal! &e&l(RIGHT-CLICK)", "&7Instantly regain &c15% &7of your health.", "&7This ability has a &c20 &7second cooldown."),
    GREATER_GOOD("&6Item Ability: &5Greater Good &e&l(RIGHT-CLICK)", "&7Lose &c10% &7of your maximum health in exchange", "&7for increasing your total damage by &c15%&7.", "&7This effect resets after &c20 &7seconds."),
    SHURIKEN("&6Item Ability: &5Shuriken &e&l(RIGHT-CLICK)", "&7Inflicts &cBlindness &7and &cWeakness II &7to all enemies", "&7in a &c5 &7block range for &c30 &7seconds.", "&7This ability has a &c90 &7second cooldown."),
    RIDER("&6Item Ability: &5Rider &e&l(RIGHT-CLICK)", "&7Summon a horse that lasts for &c10 &7seconds.", "&7In this time, you also gain &cRegeneration II", "&7for &c5 &7seconds.", "&7This ability has a &c120 &7second cooldown."),
    BLIND("&6Item Ability: &5Blind! &e&l(RIGHT-CLICK)", "&7Blinds enemies in a &c5 &7block radius around", "&7you for &c3 &7seconds.", "&7This ability has a &c1 &7minute cooldown."),
    SNEAK("&6Item Ability: &5Sneak &e&l(RIGHT-CLICK)", "&7Right-click to get &cInvisibility II &7for &c5", "&7seconds, as well as &cSpeed II &7for the same", "&7amount of time.", "&7This ability has a &c45 &7second cooldown."),
    HIDDEN_SHADOWS("&6Item Ability: Hidden Shadows &e&l(RIGHT-CLICK)", "&7Summon &c5 &7hostile ghouls around you.", "&7If it's night-time, their stats get doubled.", "&7This ability has a &c45 &7second cooldown."),
    METEOR_SHOWER("&6Item Ability: Meteor Shower &e&l(RIGHT-CLICK)", "&7Right-clicking on a block causes a meteor shower.", "&7Three meteors fall from the sky, causing", "&7an explosion upon impact, that deals &c150% &7of your", "&7damage as &eTrue Damage&7 to all enemies caught inside.", "&7This ability has a &c5 &7second cooldown and costs", "&b50 MANA&7!"),
    JUSTICE("&6Item Ability: Justice &e&l(RIGHT-CLICK)", "&7Leap into the air and create an explosion", "&7when dropping on the ground. Enemies caught", "&7in the explosion are stunned for &c5 &7seconds.", "&7This ability has a &c1 &7minute cooldown."),
    CYCLICAL_CUTS("&6Item Ability: Cyclical Cuts &e&l(RIGHT-CLICK)", "&7Inflicts a bleeding efect upon all enemies in", "&7a &c5 &7block radius. For &c10 &7seconds,", "&7opponents rapidly lose &c25% &7of your damage as health", "&7per second.", "&7This ability has a &c45 &7second cooldown.");

    private String[] lore;

    Ability(String... lore) {
        this.lore = lore;
    }

    public List<String> getAbilityLore() {
        return Arrays.asList(this.lore);
    }
}
