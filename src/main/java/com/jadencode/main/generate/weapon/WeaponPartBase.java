package com.jadencode.main.generate.weapon;

import com.jadencode.main.TimeKeeper;
import com.jadencode.main.stat.StatBase;
import com.jadencode.main.stat.StatSet;
import com.jadencode.main.material.MaterialLibrary;
import com.jadencode.main.material.MaterialResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Jaden on 5/28/2015.
 */
public class WeaponPartBase {

    private static final List<WeaponPartBase> WEAPON_PARTS = new ArrayList<>();

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

    //Common Grips
    private static final StatSet        heavyGripStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 10F)
            .addVal(StatBase.DAMAGE_PIERCE, 4F)
            .addVal(StatBase.DAMAGE_BLUNT, 1F);
    private static final StatSet        basicGripStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 5F)
            .addVal(StatBase.DAMAGE_PIERCE, 2F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);
    private static final StatSet        lightGripStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 0F)
            .addVal(StatBase.DAMAGE_PIERCE, 0F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);

    public static final  WeaponPartBase heavyGrip      = new WeaponPartSword("Heavy Grip", "Double", heavyGripStats, WeaponGenerator.SWORD_GRIPS, MaterialLibrary.getMetalLibrary());
    public static final  WeaponPartBase basicGrip      = new WeaponPartSword("Basic Grip", "Single", basicGripStats, WeaponGenerator.SWORD_GRIPS, MaterialLibrary.getMetalLibrary());
    public static final  WeaponPartBase lightGrip      = new WeaponPartSword("Light Grip", "Half", lightGripStats, WeaponGenerator.SWORD_GRIPS, MaterialLibrary.getMetalLibrary());

    //Common Hilts
    private static final StatSet heavyHiltStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 16F)
            .addVal(StatBase.DAMAGE_PIERCE, 6F)
            .addVal(StatBase.DAMAGE_BLUNT, 2F);
    private static final StatSet basicHiltStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 8F)
            .addVal(StatBase.DAMAGE_PIERCE, 3F)
            .addVal(StatBase.DAMAGE_BLUNT, 1F);
    private static final StatSet lightHiltStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 0F)
            .addVal(StatBase.DAMAGE_PIERCE, 0F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);

    public static final WeaponPartBase heavyHilt  = new WeaponPartSword("Heavy Hilt", "Heavy", heavyHiltStats, WeaponGenerator.SWORD_HILTS, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase mediumHilt = new WeaponPartSword("Balanced Hilt", "Balanced", basicHiltStats, WeaponGenerator.SWORD_HILTS, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase lightHilt  = new WeaponPartSword("Light Hilt", "Agile", lightHiltStats, WeaponGenerator.SWORD_HILTS, MaterialLibrary.getMetalLibrary());

    //Common Blades
    private static final StatSet broadBladeStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 15F)
            .addVal(StatBase.DAMAGE_PIERCE, 15F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);
    private static final StatSet longBladeStats  = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 20F)
            .addVal(StatBase.DAMAGE_PIERCE, 10F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);
    private static final StatSet shortBladeStats = new StatSet()
            .addVal(StatBase.DAMAGE_SLASH, 10F)
            .addVal(StatBase.DAMAGE_PIERCE, 5F)
            .addVal(StatBase.DAMAGE_BLUNT, 0F);

    public static final WeaponPartBase broadBlade = new WeaponPartSword("Broad Blade", "Broadsword", broadBladeStats, WeaponGenerator.SWORD_BLADES, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase longBlade  = new WeaponPartSword("Long Blade", "Longsword", longBladeStats, WeaponGenerator.SWORD_BLADES, MaterialLibrary.getMetalLibrary());
    public static final WeaponPartBase shortBlade = new WeaponPartSword("Short Blade", "Shortsword", shortBladeStats, WeaponGenerator.SWORD_BLADES, MaterialLibrary.getMetalLibrary());

//    //Common Axe Handles
//    public static final WeaponPartBase warHandleAxe     = new WeaponPartBase("War Handle", "Waraxe", WeaponGenerator.AXE_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase longHandleAxe    = new WeaponPartBase("Long Handle", "Longaxe", WeaponGenerator.AXE_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase battleHandleAxe  = new WeaponPartBase("Battle Handle", "Battleaxe", WeaponGenerator.AXE_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase halberdHandleAxe = new WeaponPartBase("Halberd Handle", "Halberd", WeaponGenerator.AXE_HANDLES, MaterialLibrary.getWoodLibrary());
//
//    //Common Axe Heads
//    public static final WeaponPartBase doubleHead = new WeaponPartBase("Double Axehead", "Double-Headed", WeaponGenerator.AXE_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase singleHead = new WeaponPartBase("Single Axehead", "Single-Headed", WeaponGenerator.AXE_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase broadHead  = new WeaponPartBase("Broad Axehead", "Broad-Headed", WeaponGenerator.AXE_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase spikeHead  = new WeaponPartBase("Spiked Axehead", "Spike-Headed", WeaponGenerator.AXE_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase scytheHead = new WeaponPartBase("Scythe Axehead", "Scythe-Headed", WeaponGenerator.AXE_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase roundHead  = new WeaponPartBase("Round Axehead", "Round-Headed", WeaponGenerator.AXE_HEADS, MaterialLibrary.getMetalLibrary());
//
//    //Common Hammer Handles
//    public static final WeaponPartBase warHandleHammer    = new WeaponPartBase("War Handle", "Warhammer", WeaponGenerator.HAMMER_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase battleHandleHammer = new WeaponPartBase("Battle Handle", "Battlehammer", WeaponGenerator.HAMMER_HANDLES, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase maulHandleHammer   = new WeaponPartBase("Maul Handle", "Maul", WeaponGenerator.HAMMER_HANDLES, MaterialLibrary.getWoodLibrary());
//
//    //Common Hammer Heads
//    public static final WeaponPartBase doubleHeadHammer = new WeaponPartBase("Double Hammerhead", "Double-Headed", WeaponGenerator.HAMMER_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase singleHeadHammer = new WeaponPartBase("Single Hammerhead", "Single-Headed", WeaponGenerator.HAMMER_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase heavyHeadHammer  = new WeaponPartBase("Heavy Hammerhead", "Heavy", WeaponGenerator.HAMMER_HEADS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase spikedHeadHammer = new WeaponPartBase("Spiked Hammerhead", "Spike-Headed", WeaponGenerator.HAMMER_HEADS, MaterialLibrary.getMetalLibrary());
//
//    //Common Bow Strings
//    public static final WeaponPartBase lightString = new WeaponPartBase("Light Bowstring", "Light", WeaponGenerator.BOW_STRINGS, MaterialLibrary.getPlantLibrary());
//    public static final WeaponPartBase heavyString = new WeaponPartBase("Heavy Bowstring", "Heavy", WeaponGenerator.BOW_STRINGS, MaterialLibrary.getPlantLibrary());
//
//    //Common Bow Limbs
//    public static final WeaponPartBase shortLimbs  = new WeaponPartBase("Shortbow Limbs", "Shortbow", WeaponGenerator.BOW_LIMBS, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase longLimbs   = new WeaponPartBase("Longbow Limbs", "Longbow", WeaponGenerator.BOW_LIMBS, MaterialLibrary.getWoodLibrary());
//    public static final WeaponPartBase mediumLimbs = new WeaponPartBase("Recurve Limbs", "Recurve Bow", WeaponGenerator.BOW_LIMBS, MaterialLibrary.getWoodLibrary());
//
//    //Common Bow Grips
//    public static final WeaponPartBase quickGrip    = new WeaponPartBase("Slick Bow Grip", "Rapid", WeaponGenerator.BOW_GRIPS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase steadyGrip   = new WeaponPartBase("Steady Bow Grip", "Accurate", WeaponGenerator.BOW_GRIPS, MaterialLibrary.getMetalLibrary());
//    public static final WeaponPartBase balancedGrip = new WeaponPartBase("Balanced Bow Grip", "Balanced", WeaponGenerator.BOW_GRIPS, MaterialLibrary.getMetalLibrary());
//
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


    private final String           partName;
    private final List<WeaponPart> partList;
    private final String           nameMod;
    private final List<MaterialResource> materials = new ArrayList<>();
    private final boolean usesMaterials;
    private final float   weight;
    private final StatSet statSet;

//    public WeaponPartBase(String name, String mod, List<WeaponPart> list, MaterialLibrary... mats) {
//        this(name, mod, StatSet.DEFAULT, list, mats);
//    }

    public WeaponPartBase(String name, String mod, StatSet stats, List<WeaponPart> list, MaterialLibrary... mats) {
        this(name, mod, 1F, stats, list, mats);
    }

    public WeaponPartBase(String name, String mod, float weight, StatSet stats, List<WeaponPart> list, MaterialLibrary... mats) {
        this.partName = name;
        this.nameMod = mod;
        this.weight = weight;
        this.partList = list;

        this.statSet = stats;

        for (MaterialLibrary lib : mats) {
            this.materials.addAll(lib.getMaterialResources().values());
        }
        this.usesMaterials = !this.materials.isEmpty();

        WEAPON_PARTS.add(this);
    }

    public boolean usesMaterials() {
        return usesMaterials;
    }

    public float getWeight() {
        return weight;
    }

    public String getPartName() {
        return partName;
    }

    public List<WeaponPart> getPartList() {
        return partList;
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

    //Returns a StatSet for this part modified using the provided resource.
    public StatSet modifyStats(MaterialResource resource) {
        return this.statSet;
    }
    public StatSet scaleStats(WeaponPartInstance partInstance) {
        return this.statSet;
    }

    public static List<WeaponPartBase> getBaseParts() {
        return WEAPON_PARTS;
    }

    public static void enumerateParts(boolean storeFile) {
        TimeKeeper timer = new TimeKeeper();
        timer.start("Enumeration all weapon parts. . .");

        Comparator<WeaponPart> comparator = (WeaponPart a, WeaponPart b) -> a.getPartName().compareTo(b.getPartName());
        List<WeaponPart> parts = WeaponGenerator.getAll();
        parts.sort(comparator);

        PrintStream writer = System.out;

        if(storeFile) {
            File out = new File("./enum.txt");

            try {
                out.createNewFile();
                writer = new PrintStream(new FileOutputStream(out));

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        for(WeaponPart part : parts) {
            String msg = String.format("%s, %s\n\t", part.getPartName(), part.getPartInfo());
            if(part.hasBaseResource()) {
                msg += part.getBaseResource().getName();
            } else {
                msg += "No Resource";
            }
            writer.println(msg);
        }
        writer.close();
        timer.stopAndDisplay();
    }
}