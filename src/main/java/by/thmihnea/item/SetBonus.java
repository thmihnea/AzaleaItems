package by.thmihnea.item;

import java.util.Arrays;
import java.util.List;

public enum SetBonus {

    NATURES_GUARDIAN("&aNature's Guardian: &7Mother Nature is", "&7your friend. Standing on grass blocks, grass,", "&7and flowers increases your &bMANA &7by &b25%&7."),
    NATURES_WRATH("&aNature's Wrath: &7Mistreatment of Mother Nature", "&7fills you with rage. Your next Mana-based attack", "&7will have a &a20% &7chance to deal True Damage."),
    GENTLE_RIPPLE("&9Gentle Ripple: &7Your mere presence soothes away", "&7the weariness and pain of your allies.", "&7Allies within 3 blocks of you gain &cRegeneration I", "&7for &c2 seconds&7."),
    CRASHING_WAVES("&9Crashing Waves: &7Enemies fear your mastery over water. Random", "&7blocks in a &c5 block &7radius sprout water. If", "&7the enemy finds themselves caught in your waves,", "&7receive &cNausea &7and &cSlowness I &7for &c2 seconds&7."),
    TREMOR("&5Tremor: &7With each step you take, your enemies", "&7shake in their boots. Enemies within", "&c2 blocks &7receive &cWeakness I&7."),
    TECTONIC_SHIFT("&5Tectonic Shift: &7The unsteady ground starts", "&7stumbling your enemies. Every &c10 seconds&7, a wave", "&7is emitted withing &c3 blocks&7 that can place", "&7an enemy's armor piece back into their inventory", "&7with a &c7% &7chance."),
    GALE_VALLEY("&9Gale Valley: &7As you take damage, your", "&7strikes increase in precision. When you", "&7aren't at full health, increase &cCrit Chance&7", "&7by &c10%&7."),
    FOUR_WINDS("&9Four Winds: &7Your fellow Wind Gods come to your aid.", "&7Every &c10 seconds&7, emit a wave that knocks back", "&7opponents by &c5 blocks&7."),
    FROM_THE_ASHES("&5From the Ashes: &7Just when your enemies think they're", "&7closing in on the kill, you prove them wrong. When", "&7your health drops below &c30%&7, instantly gain", "&c15% HP &7and &b10% MANA&7. (Cooldown: &c60 seconds&7)"),
    REINCARNATION("&5Reincarnation: &7Whenever you take lethal damage, you", "&7regenerate to full health, and lose all debuffs", "&7and active potion effects. (Cooldown: &c5 minutes&7)"),
    NATURAL_DEFENSE("&aNatural Defense: &7Increases total &3DEF &7by &a20%&7.", "&7Dropping below &a3% &7of your total health", "&7will propel you backwards. (Cooldown: &a60 seconds&7)"),
    POWERLIFTER("&aPowerlifter: &7Increases damage by &c25% &7when in", "&7the presence of multiple enemies."),
    FLUTTER("&9Flutter: &7Allows the player to Double-Jump.", "&7(Cooldown: &c5 seconds&7)"),
    MIMICRY("&9Mimicry: &7Shapeshift into the player that hit you last.", "&7Effect lasts for &c5 seconds &7and has a &c30 second&7 cooldown.", "&7(Cooldown beings after shapeshift ends)"),
    ASSEMBLE_THE_BEES("&aAssemble the Bees!: &7Worker bees work hard to produce honey.", "&7Increases &dHP Regen &7by &c10%&7."),
    LAST_STING("&aLast Sting: &7When your Health falls below &c50%&7, your", "&cCrit Damage&7 increases by &c50% &7and your &cCrit Chance", "&7is increased by &c25% &7for &c1 second&7."),
    COCOON("&9Cocoon: &7When your Health falls below &c30%&7,", "&7cover yourself in cobwebs for &c3 seconds&7.", "&7During this time, you take &c30% &7reduced damage", "&7and have &c50% &7increased health regen. (Cooldown: &a60 seconds&7)"),
    SNARE("&9Snare: &7Randomly send out cobwebs in each direction that", "&7snare enemy players for &c3 seconds&7. (Cooldown: &a60 seconds&7)"),
    PRAYER("&aPrayer: &7Grants &b30% &7Mana Regen when standing still."),
    PERFECT_TIMING("&aPerfect Timing: &7Standing still regenerates the", "&7cooldowns of your active abilities by &c15%&7."),
    CONDESCENDING_GAZE("&9Condescending Gaze: &7Looking at the weaker opponent in front", "&7of you ignites your pride. Enemies within", "&c3 blocks &7gain &cNausea &7while you gain &cStrength I&7."),
    BLIND_PRIDE("&9Blind Pride: &7Your pride blinds you from", "&7your enemies' attempts at mocking you.", "&7You are &c&nIMMUNE&7 to all enemy debuffs."),
    SULTRY_GAZE("&5Sultry Gaze: &7As you stare into your enemies' eyes,", "&7their anger and hatred for you wanes. Decrease", "&7opponents' attack by &c30% &7when they are within", "&c5 blocks&7, and every time they hit you, they", "&7have a chance of getting &cWithered&7."),
    DIE_FOR_ME("&5Die for Me!: &7Even your allies are under your charm.", "&7Every &c10 seconds&7, there is a &c30% &7chance", "&7the enemy's field of vision gets super-scoped,", "&7becoming &cWithered &7for &c3 seconds&7."),
    PIG_OUT("&6Pig Out: &7Your desire for food supercedes", "&7how it tastes. Upon eating any consumable item,", "&7attain &cRegeneration I &7for &c2 seconds&7."),
    CONVERSION("&6Conversion: &7Your enemies' constant barrage of", "&7attacks start distracting you from eating. As you", "&7lose your health, gain the proportional", "&7amount of &cCrit Damage&7."),
    IGNITION("&9Ignition: &7Your burning anger incinerates projectiles", "&7shot at you, rendering them useless. Have a", "&c25% chance &7to null any projectile damage."),
    CALM_IN_THE_STORM("&9Calm in the Storm: &7You suddenly quiet yourself,", "&7and allow the wrath to burst with increased energy", "&7only at certain times. When below &c25% HP&7,", "&7gain &c100% Crit Damage &7and &c50% Crit Chance&7."),
    BEAUTIFUL_DAY("&5Beautiful Day: &7You're enjoying the beautiful weather", "&7outside. Standing still during favorable weather", "&7conditions grants &cRegeneration II &7and clears debuffs."),
    DONT_DISTURB_ME("&5Don't Disturb Me: &7Taking breaks is essential", "&7for your health! Crouching reduces damage intake", "&7by &c50%&7, and any enemy that attacks you gets &cWeakness I", "&7for &c1 second&7."),
    FOR_THE_KING("&fFor the King!: &7Your desire to protect the King", "&7strengthens your resolve. Reduce any damage", "&7taken by &c30% &7when below &c50% HP&7."),
    PROMOTION("&fPromotion!: &7Promoting your piece gives you confidence.", "&7After killing an enemy, gain access to a weaker", "&7version of the &aBishop&7/&9Knight&7/&5Rook&7 full set ability", "&7for &c1 minute&7. (2 minute cooldown)"),
    TACTICAL_PIN("&aTactical Pin: &7Your attacks have a &c20% &7chance", "&7of rooting the enemy to their position for &c1 second&7."),
    QUICK_STRIKE("&aQuick Strike: &7When shifting, create a warp point.", "&7You have &c5 seconds &7to pursue your enemies.", "&7After the time ends, you will be prompted back to", "&7your original location. (Cooldown: &c20 seconds&7)"),
    THE_POWER_OF_TWO("&9The Power of Two: &7Your presence threatens the enemy", "&7from two different angles. Gives you &cStrength I", "&7and gives the enemy &cWeakness I&7."),
    MIGHTY_STALLION("&9Mighty Stallion: &7Sneaking allows you to jump into", "&7the air, while being mounted to a slime.", "&7Deal AOE damage equal to &c50% &7of your total &cDamage", "&7when hitting the ground. (&c10 second &7cooldown)"),
    RESOLUTE_STANCE("&5Resolute Stance: &7Gain a shield that", "&7amounts to &c30% &7of your maximum &7HP&7.", "&7When the shiled breaks, knockback all enemies who are around you", "&7in a &c5 block &7radius. (&c2 minute &7cooldown)"),
    ASSEMBLE_THE_CASTLE("&5Assemble the Castle!: &7Crouching allows you to recharge", "&7the shield with &c0.25 seconds&7 per crouch."),
    STALEMATE("&6Stalemate: &7When your health falls below &c10%", "&7all enemies around you in a &c5 block &7radius freeze.", "&7This effect lasta for &c5 seconds."),
    CHECKMATE("&6CHECKMATE!: &7The king is angry. When your health falls below", "&c20%&7, summon a &cPawn&7, &cQueen&7 and &cKnight &7to defend you.", "&7Each piece targets nearby enemies."),
    RISING_TEMPO("&6Rising Tempo: &7Your efficient moves build up tempo.", "&7Every three hits, you recover &c10% &7of your &cHP&7."),
    QUEENS_GAMBIT("&6Queen's Gambit: &7You sacrifice your pawn to gain control.", "&7Crouching instantly reduces your &cHP &7to &c15%", "&7for &c5 seconds&7. After this period, if you are alive,", "&7you gain &c100% HP&7, and your &3DEF &7and &cDMG &7get increased by", "&c50% &7and &c150 &7respectively, for &c5 seconds&7. (&c1 minute &7cooldown)"),
    PICK_A_CARD("&aPick a Card: &7When you engage in combat, every", "&c10 seconds&7 a random stat is boosted by &c10%&7."),
    THE_LAST_JOKE("&aThe Last Joke: &7When a player kills you, they", "&7take &eTRUE DAMAGE &7equivalent to &c50% &7of your &cMAX HP&7."),
    SUNDIAL("&9Sundial: &7Gain increased &cDMG &7depending", "&7on the position of the sun."),
    MOONLIT_GAZE("&9Moonlit Gaze: &7Gain increased &bMANA &7depending", "&7on the position of the moon."),
    SANDSTORM("&aSandstorm: &7Wearing this armor activates a sandstorm.", "&7The storm forms around you in a &c5 block &7radius", "&7and follows you. Enemies caught in the sandstorm", "&7take damage over time. (Equal to &c10% &7of your &cDMG&7)"),
    SAND_VEIL("&aSand Veil: &7In the presence of a sandstorm, have a", "&c30% &7chance of dodgin enemy attacks."),
    SMOKESCREEN("&5Smokescreen: &7Entering combat triggeres a smokescreen", "&7for your enemies. Players caught within &c3", "&cblocks &7are blinded."),
    ASPHIXIATION("&5Asphixiation: &7Being caught in your smokescreen", "&7prevents opponents' armors' abilities from activating."),
    REGRETFUL_VOLTAGE("&9Regretful Voltage: &7Enemies who physically hit", "&7you, have a &c20% &7chance of being rooted."),
    OVERDRIVE("&9Overdrive: &7Attacking you with a direct physical", "&7attack leaves the enemy in shock. They receive", "&7slowness, poison and a shaking screen for &c2 seconds&7."),
    SPRING_BURST("&fSpring Burst: &7As you run away from battle, you", "&7begin to regenerate your &cHP&7. While lower", "&7than &c50% MAX HP&7, you gain &cRegeneration I", "&7while sprinting."),
    STURDY("&fSturdy: &7A fatal strike leaves you clinging onto", "&7your health by &c1%&7. When this ability is triggered,", "&7you get teleported &c10 blocks &7away."),
    PHANTOM_PLAYTIME("&9Phantom Playtime: &7Your sinister presence instills", "&7fear into the enemy. When you land a hit on the opponent,", "&7their health bar becomes invisible for &c5 seconds&7."),
    GASTLY_GARDEN("&9Gastly Garden: &7When you engage in combat, you throw", "&7up a &c5 block &7bubble. Any enemies caught in this", "&7bubble aren't allowed to leave the premises until", "&7you are no longer in combat."),
    REFLECTION("&5Reflection: &7Your enemy never gets away unscathed.", "&7Always deal &c10% &7of the damage you take per hit", "&7back to your opponent as &eTRUE DMG&7."),
    BROKEN_GLASS("&5Broken Glass: &7Every blow received drops shards of glass.", "&7Enemies who step on these pieces start bleeding,", "&7granting them &cWeakness I&7, &cWither I&7, and &cBlindness I", "&7for &c2 seconds&7."),
    SHUFFLE("&aShuffle: &7When getting damaged, roll a die (&c1 &7to &c3&7).", "&7Reduces your damage by &c10% &7per each number rolled."),
    FINAL_GAMBIT("&aFinal Gambit: &7When your health drops below &c10%&7,", "&7each time you hit an enemy, you roll another die from", "&7(&c1 &7to &c3&7). Multiplies your total &cDMG &7by the rolled number."),
    OVERGROWTH("&fOvergrowth: &7Walking near saplings and crops", "&7makes them grow faster."),
    DRAIN("&fDrain: &7Anything that has life around you instantly", "&7dies and adds to your maximum &cHP&7 by &c20 &7each.", "&7This &cHP &7will be reverted back in &c30 &7seconds.");


    private String[] lore;

    SetBonus(String... lore) {
        this.lore = lore;
    }

    public List<String> getSetLore() {
        return Arrays.asList(this.lore);
    }
}
