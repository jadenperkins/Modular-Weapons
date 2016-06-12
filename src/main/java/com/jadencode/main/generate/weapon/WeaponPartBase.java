package com.jadencode.main.generate.weapon;

import com.jadencode.main.constants.WeaponParts;
import com.jadencode.main.constants.StatSets;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.material.MaterialLibrary;
import com.jadencode.main.material.MaterialResource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponPartBase {
    //Legendary Hilts
//    public static final WeaponPartBase ascendentHilt    = new WeaponPartBase("Hilt of Ascension", "Ascendent", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase chosenHilt       = new WeaponPartBase("Hilt of the Chosen", "Chosen", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase cynicalHilt      = new WeaponPartBase("Hilt of the Cynics", "Cynical", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase defiantHilt      = new WeaponPartBase("Hilt of Defiance", "Defiant", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase direHilt         = new WeaponPartBase("Dire Hilt", "Dire", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase draconicHilt     = new WeaponPartBase("Hilt of Dragons", "Draconic", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase etherealHilt     = new WeaponPartBase("Hilt of Ghosts", "Ethereal", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase grotesqueHilt    = new WeaponPartBase("Hilt of Monsters", "Grotesque", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase hallowedHilt     = new WeaponPartBase("Hilt of the Reverent", "Hallowed", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase incandescentHilt = new WeaponPartBase("Hilt of Incandescence", "Incandescent", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase judiciousHilt    = new WeaponPartBase("Hilt of Judgement", "Judicious", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase obsequiousHilt   = new WeaponPartBase("Hilt of the Slaves", "Obsequious", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase omniscientHilt   = new WeaponPartBase("Hilt of the All-seeing", "Omniscient", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase penitentHilt     = new WeaponPartBase("Hilt of Remorse", "Penitent", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase radiantHilt      = new WeaponPartBase("Hilt of Radiance", "Radiant", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase ubiquitousHilt   = new WeaponPartBase("Hilt of the Ever-present", "Ubiquitous", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase unyieldingHilt   = new WeaponPartBase("Hilt of Strength", "Unyielding", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase voraciousHilt    = new WeaponPartBase("Hilt of Voracity", "Voracious", WeaponGenerator.SWORD_HILTS);
//    public static final WeaponPartBase zealousHilt      = new WeaponPartBase("Hilt of the Zealot", "Zealous", WeaponGenerator.SWORD_HILTS);

    //Legendary Grips
//    public static final WeaponPartBase darkGrip   = new WeaponPartBase("Grip of Darkness", "Dark", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase dayGrip    = new WeaponPartBase("Grip of Daylight", "Day", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase deathGrip  = new WeaponPartBase("Grip of Death", "Death", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase dreamGrip  = new WeaponPartBase("Grip of Dreams", "Dream", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase fateGrip   = new WeaponPartBase("Grip of Fate", "Fate", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase ghostGrip  = new WeaponPartBase("Grip of Ghosts", "Ghost", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase graveGrip  = new WeaponPartBase("Grip of the Grave", "Grave", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase lifeGrip   = new WeaponPartBase("Grip of Life", "Life", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase moonGrip   = new WeaponPartBase("Grip of the Moon", "Moon", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase nightGrip  = new WeaponPartBase("Grip of Night", "Night", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase soulGrip   = new WeaponPartBase("Grip of Souls", "Soul", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase spiritGrip = new WeaponPartBase("Grip of Spirits", "Spirit", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase sunGrip    = new WeaponPartBase("Grip of the Sun", "Sun", WeaponGenerator.MELEE_HELMET_BASES);
//    public static final WeaponPartBase willGrip   = new WeaponPartBase("Grip of Will", "Will", WeaponGenerator.MELEE_HELMET_BASES);

    //Legendary Blades
//    public static final WeaponPartBase breakerBlade  = new WeaponPartBase("Breaker Blade", "Breaker", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase bringerBlade  = new WeaponPartBase("Bringer Blade", "Bringer", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase chillerBlade  = new WeaponPartBase("Chiller Blade", "Chiller", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase crawlerBlade  = new WeaponPartBase("Crawler Blade", "Crawler", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase dancerBlade   = new WeaponPartBase("Dancer Blade", "Dancer", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase delverBlade   = new WeaponPartBase("Delver Blade", "Delver", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase drainerBlade  = new WeaponPartBase("Drainer Blade", "Drainer", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase eaterBlade    = new WeaponPartBase("Eater Blade", "Eater", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase haterBlade    = new WeaponPartBase("Hater Blade", "Hater", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase marcherBlade  = new WeaponPartBase("Marcher Blade", "Marcher", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase reaperBlade   = new WeaponPartBase("Reaper Blade", "Reaper", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase ripperBlade   = new WeaponPartBase("Ripper Blade", "Ripper", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase runnerBlade   = new WeaponPartBase("Runner Blade", "Runner", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase screamerBlade = new WeaponPartBase("Screamer Blade", "Screamer", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase seekerBlade   = new WeaponPartBase("Seeker Blade", "Seeker", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase shockerBlade  = new WeaponPartBase("Shocker Blade", "Shocker", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase shredderBlade = new WeaponPartBase("Shredder Blade", "Shredder", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase singerBlade   = new WeaponPartBase("Singer Blade", "Singer", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase striderBlade  = new WeaponPartBase("Strider Blade", "Strider", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase wakerBlade    = new WeaponPartBase("Waker Blade", "Waker", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase walkerBlade   = new WeaponPartBase("Walker Blade", "Walker", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase wasterBlade   = new WeaponPartBase("Waster Blade", "Waster", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase watcherBlade  = new WeaponPartBase("Watcher Blade", "Watcher", WeaponGenerator.SWORD_BLADES);
//    public static final WeaponPartBase wringerBlade  = new WeaponPartBase("Wringer Blade", "Wringer", WeaponGenerator.SWORD_BLADES);

//    //Common Arrow Heads
//    public static final WeaponPartBase basicTips = new WeaponPartBase("Basic Arrowheads", "Simple", WeaponGenerator.ARROW_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase heavyTips = new WeaponPartBase("Heavy Arrowheads", "Heavy", WeaponGenerator.ARROW_HEADS, MaterialLibrary.getMetalLibrary());
//
//    //Common Arrow Shafts
//    public static final WeaponPartBase shortShaft = new WeaponPartBase("Short Arrow Shaft", "Short Arrow", WeaponGenerator.ARROW_SHAFTS, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase longShaft  = new WeaponPartBase("Long Arrow Shaft", "Long Arrow", WeaponGenerator.ARROW_SHAFTS, MaterialLibrary.getWoodLibrary());
//
//    //Common Arrow Fletchings
//    public static final WeaponPartBase fastFletching  = new WeaponPartBase("Fast Fletchings", "Swift", WeaponGenerator.ARROW_FLETCHINGS, MaterialLibrary.getPlantLibrary());
//    public static final WeaponPartBase sharpFletching = new WeaponPartBase("Accurate Fletchings", "Precision", WeaponGenerator.ARROW_FLETCHINGS, MaterialLibrary.getPlantLibrary());
//
//    //Common Staff Handles
//    public static final WeaponPartBase longHandle    = new WeaponPartBase("Long Handle", "Longstaff", WeaponGenerator.STAFF_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase shortHandle   = new WeaponPartBase("Short Handle", "Shortstaff", WeaponGenerator.STAFF_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase quarterHandle = new WeaponPartBase("Quarter Handle", "Quarterstaff", WeaponGenerator.STAFF_HANDLES, MaterialLibrary.getWoodLibrary());
//
//    //Common Staff Heads
//    public static final WeaponPartBase focusingCrystal = new WeaponPartBase("Focusing Crystal", "Magic", WeaponGenerator.STAFF_HEADS, MaterialLibrary.getGemLibrary());
//
//    //Common Crossbow Stocks
//    public static final WeaponPartBase accurateStock = new WeaponPartBase("Accurate Stock", "Accurate", WeaponGenerator.CROSSBOW_STOCKS, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase balancedStock = new WeaponPartBase("Balanced Stock", "Balanced", WeaponGenerator.CROSSBOW_STOCKS, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase rapidStock    = new WeaponPartBase("Rapid Stock", "Rapid", WeaponGenerator.CROSSBOW_STOCKS, MaterialLibrary.getWoodLibrary());
//
//    //Common Crossbow Limbs
//    public static final WeaponPartBase shortCrossbowLimbs = new WeaponPartBase("Short Limbs", "Short Crossbow", WeaponGenerator.CROSSBOW_LIMBS, MaterialLibrary.getWoodLibrary(), MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase longCrossbowLimbs  = new WeaponPartBase("Long Limbs", "Long Crossbow", WeaponGenerator.CROSSBOW_LIMBS, MaterialLibrary.getWoodLibrary(), MaterialLibrary.getMetalLibrary());
//
//    //Common Crossbow Strings
//    public static final WeaponPartBase heavyCrossbowString = new WeaponPartBase("Heavy Crossbow String", "Heavy", WeaponGenerator.CROSSBOW_STRINGS, MaterialLibrary.getPlantLibrary());
//    public static final WeaponPartBase lightCrossbowString = new WeaponPartBase("Light Crossbow String", "Light", WeaponGenerator.CROSSBOW_STRINGS, MaterialLibrary.getPlantLibrary());
//
//    //Common Bolt Heads
//    public static final WeaponPartBase heavyBoltTips = new WeaponPartBase("Heavy Bolt Tips", "Heavy", WeaponGenerator.BOLT_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase lightBoltTips = new WeaponPartBase("Light Bolt Tips", "Light", WeaponGenerator.BOLT_HEADS, MaterialLibrary.getMetalLibrary());
//
//    //Common Bolt Shafts
//    public static final WeaponPartBase longBoltShaft  = new WeaponPartBase("Long Bolt Shaft", "Long Bolt", WeaponGenerator.BOLT_SHAFTS, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase shortBoltShaft = new WeaponPartBase("Short Bolt Shaft", "Short Bolt", WeaponGenerator.BOLT_SHAFTS, MaterialLibrary.getWoodLibrary());
//
//    //Common Bolt Fletchings
//    public static final WeaponPartBase fastBoltFlight  = new WeaponPartBase("Rapid Bolt Flight", "Rapid", WeaponGenerator.BOLT_FLETCHINGS, MaterialLibrary.getPlantLibrary());
//    public static final WeaponPartBase sharpBoltFlight = new WeaponPartBase("Accurate Bolt Flight", "Accurate", WeaponGenerator.BOLT_FLETCHINGS, MaterialLibrary.getPlantLibrary());

    private final WeaponPartType   partType;
    private final String           partName;
    private final String           nameMod;
    private final List<MaterialResource> materials = new ArrayList<>();
    private final float   weight;
    private final StatSet statSet;

    public WeaponPartBase(String name, String mod, float weight, StatSet stats, WeaponPartType type, MaterialLibrary... mats) {
        this.partName = name;
        this.nameMod = mod;
        this.weight = weight;
        this.partType = type;

        this.statSet = stats;
        Arrays.stream(mats).forEach(lib -> this.materials.addAll(lib.getMaterialResources().values()));
        WeaponParts.addBasePart(this);
    }
    public WeaponPartBase(String name, String mod, StatSet stats, WeaponPartType type, MaterialLibrary... mats) {
        this(name, mod, 1F, stats, type, mats);
    }
    public float getWeight() {
        return weight;
    }

    public String getPartName() {
        return partName;
    }

    public WeaponPartType getPartType() {
        return partType;
    }

    public String getNameMod() {
        return nameMod;
    }

    public List<MaterialResource> getMaterials() {
        return materials;
    }

    public StatSet getStatSet() {
        return statSet;
    }
    public StatSet modifyStats(MaterialResource resource) {
        return this.getStatSet().modified(resource);
    }
    public StatSet scaleStats(WeaponPartInstance partInstance) {
        return this.statSet.scaled(partInstance.getLevel());
    }
}