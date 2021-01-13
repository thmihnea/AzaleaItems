package by.thmihnea.api;

import by.thmihnea.cooldown.Cooldown;
import by.thmihnea.cooldown.CooldownType;
import by.thmihnea.item.SetBonus;
import by.thmihnea.runnables.*;
import org.bukkit.entity.Player;

import java.util.*;

public class ItemizedPlayer {

    private Player player;
    private boolean naturesGuardian;
    private boolean galeValley;
    private boolean hasCrashingWaves;
    private boolean hasCondescendingGaze;
    private boolean hasTectonicShift;
    private boolean hasFourWinds;
    private boolean naturesDefense;
    private boolean isGazed;
    private boolean rooted;
    private boolean pickpocket;
    private CrashingWavesTask crashingWaves;
    private TectonicShiftTask tectonicShift;
    private FourWindsTask fourWinds;
    private CondescendingGazeTask condescendingGaze;
    private Integer naturesGuardianManaAmount;
    private Integer galeValleyCritChanceAmount;
    private Integer naturesDefenseAmount;
    private Set<Cooldown> cooldowns = new HashSet<>();
    private Map<SetBonus, Integer> boostedStats = new HashMap<>();

    {
        boostedStats.put(SetBonus.ASSEMBLE_THE_BEES, 0);
        boostedStats.put(SetBonus.CONVERSION, 0);
        boostedStats.put(SetBonus.CALM_IN_THE_STORM, 0);
        boostedStats.put(SetBonus.RESOLUTE_STANCE, 0);
        boostedStats.put(SetBonus.RISING_TEMPO, 0); // 0 -> 1 -> 2 -> 3
    }

    public ItemizedPlayer(Player player) {
        this.player = player;
        this.naturesGuardian = false;
        this.galeValley = false;
        this.crashingWaves = null;
        this.tectonicShift = null;
        this.fourWinds = null;
        this.hasCrashingWaves = false;
        this.hasTectonicShift = false;
        this.hasFourWinds = false;
        this.isGazed = false;
        this.rooted = false;
        this.pickpocket = false;
        this.naturesDefense = false;
        this.naturesGuardianManaAmount = 0;
        this.galeValleyCritChanceAmount = 0;
        this.naturesDefenseAmount = 0;
        ItemizedPlayerManager.addItemizedPlayer(player, this);
        new DoubleJumpTask(player);
        new StandingStillTask(player);
        new GluttonyTask(player);
        new CalmInTheStormTask(player);
        new BeautifulDayTask(player);
        new ThePowerOfTwoTask(player);
        new ResoluteStanceTask(player);
        new ChronoTask(player);
        new SandstormTask(player);
        new SpringBurstTask(player);
    }

    public Integer getBoostedAmountBySetBonus(SetBonus setBonus) {
        return this.boostedStats.get(setBonus);
    }

    public Map<SetBonus, Integer> getBoostedStats() {
        return this.boostedStats;
    }

    public void setBoostedStat(SetBonus setBonus, Integer value) {
        if (this.hasStat(setBonus))
            removeBoostedStat(setBonus);
        addBoostedStat(setBonus, value);
    }

    public boolean isRooted() {
        return this.rooted;
    }

    public void setRooted(boolean isRooted) {
        this.rooted = isRooted;
    }

    public void removeBoostedStat(SetBonus setBonus) {
        this.boostedStats.remove(setBonus);
    }

    public void addBoostedStat(SetBonus setBonus, Integer value) {
        this.boostedStats.put(setBonus, value);
    }

    public boolean hasStat(SetBonus setBonus) {
        return this.boostedStats.containsKey(setBonus);
    }

    public boolean getHasTectonicShift() {
        return this.hasTectonicShift;
    }

    public void setHasTectonicShift(boolean hasTectonicShift) {
        this.hasTectonicShift = hasTectonicShift;
    }

    public TectonicShiftTask getTectonicShift() {
        return this.tectonicShift;
    }

    public void setTectonicShift(TectonicShiftTask tectonicShift) {
        this.tectonicShift = tectonicShift;
    }

    public boolean getHasFourWinds() {
        return this.hasFourWinds;
    }

    public void setHasFourWinds(boolean hasFourWinds) {
        this.hasFourWinds = hasFourWinds;
    }

    public FourWindsTask getFourWinds() {
        return this.fourWinds;
    }

    public void setFourWinds(FourWindsTask fourWinds) {
        this.fourWinds = fourWinds;
    }

    public boolean getHasCrashingWaves() {
        return this.hasCrashingWaves;
    }

    public void setHasCrashingWaves(Boolean hasCrashingWaves) {
        this.hasCrashingWaves = hasCrashingWaves;
    }

    public CrashingWavesTask getCrashingWaves() {
        return this.crashingWaves;
    }

    public void setCrashingWaves(CrashingWavesTask crashingWaves) {
        this.crashingWaves = crashingWaves;
    }

    public boolean getHasCondescendingGaze() {
        return this.hasCondescendingGaze;
    }

    public void setHasCondescendingGaze(Boolean condescendingGaze) {
        this.hasCondescendingGaze = condescendingGaze;
    }

    public CondescendingGazeTask getCondescendingGaze() {
        return this.condescendingGaze;
    }

    public void setCondescendingGaze(CondescendingGazeTask condescendingGaze) {
        this.condescendingGaze = condescendingGaze;
    }

    public boolean hasNaturesGuardian() {
        return this.naturesGuardian;
    }

    public void setNaturesGuardian(boolean naturesGuardian) {
        this.naturesGuardian = naturesGuardian;
    }

    public Integer getNaturesGuardianManaAmount() {
        return this.naturesGuardianManaAmount;
    }

    public void setNaturesGuardianManaAmount(Integer value) {
        this.naturesGuardianManaAmount = value;
    }

    public boolean hasNaturesDefense() {
        return this.naturesDefense;
    }

    public void setNaturesDefense(boolean naturesDefense) {
        this.naturesDefense = naturesDefense;
    }

    public Integer getNaturesDefenseAmount() {
        return this.naturesDefenseAmount;
    }

    public void setNaturesDefenseAmount(Integer value) {
        this.naturesDefenseAmount = value;
    }

    public boolean hasGaleValley() {
        return this.galeValley;
    }

    public void setGaleValley(boolean galeValley) {
        this.galeValley = galeValley;
    }

    public Integer getGaleValleyCritChanceAmount() {
        return this.galeValleyCritChanceAmount;
    }

    public void setGaleValleyCritChanceAmount(Integer value) {
        this.galeValleyCritChanceAmount = value;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean hasPickpocketActive() {
        return this.pickpocket;
    }

    public void setPickpocket(boolean pickpocket) {
        this.pickpocket = pickpocket;
    }

    public Set<Cooldown> getCooldowns() {
        return this.cooldowns;
    }

    public boolean hasCooldown(CooldownType cooldownType) {
        for (Cooldown cooldown : this.cooldowns)
            if (cooldown.getCooldownType().equals(cooldownType)) return true;
        return false;
    }

    public Cooldown getCooldownByType(CooldownType cooldownType) {
        for (Cooldown cooldown : this.cooldowns)
            if (cooldown.getCooldownType().equals(cooldownType))
                return cooldown;
        return null;
    }

    public void addCooldown(Cooldown cooldown) {
        this.cooldowns.add(cooldown);
    }

    public void removeCooldown(Cooldown cooldown) {
        this.cooldowns.remove(cooldown);
    }

    public void setIsGazed(boolean isGazed) {
        this.isGazed = isGazed;
    }

    public boolean getIsGazed() {
        return this.isGazed;
    }
}
